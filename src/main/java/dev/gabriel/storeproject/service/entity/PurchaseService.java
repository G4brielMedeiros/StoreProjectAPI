package dev.gabriel.storeproject.service.entity;

import dev.gabriel.storeproject.domain.Client;
import dev.gabriel.storeproject.domain.InvoicePayment;
import dev.gabriel.storeproject.domain.Purchase;
import dev.gabriel.storeproject.domain.enums.PaymentStatus;
import dev.gabriel.storeproject.domain.enums.Profile;
import dev.gabriel.storeproject.repository.PaymentRepository;
import dev.gabriel.storeproject.repository.PurchaseItemRepository;
import dev.gabriel.storeproject.repository.PurchaseRepository;
import dev.gabriel.storeproject.security.UserSS;
import dev.gabriel.storeproject.service.UserService;
import dev.gabriel.storeproject.service.email.EmailService;
import dev.gabriel.storeproject.service.exception.AuthorizationException;
import dev.gabriel.storeproject.service.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class PurchaseService implements EntityService<Purchase> {

    final PurchaseRepository repository;
    final PaymentRepository paymentRepository;
    final InvoiceService invoiceService;
    final ProductService productService;
    final PurchaseItemRepository purchaseItemRepository;
    final ClientService clientService;
    final EmailService emailService;

    public Purchase findById(Integer id) {
        Purchase purchase = repository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("" + Purchase.class.getSimpleName() + " not found. Id: " + id));

        UserSS user = UserService.authenticated();
        if (user == null || !user.hasRole(Profile.ADMIN) && !purchase.getClient().getId().equals(user.getId())) {
            throw new AuthorizationException("Access denied.");
        }

        return purchase;
    }

    public Purchase add(Purchase purchase) {
        purchase.setId(null);
        purchase.setInstant(new Date());
        purchase.getPayment().setStatus(PaymentStatus.PENDING);
        purchase.getPayment().setPurchase(purchase);
        purchase.setClient(clientService.findById(purchase.getClient().getId()));

        if (purchase.getPayment() instanceof InvoicePayment payment) {
            invoiceService.fillInvoicePayment(payment, purchase.getInstant());
        }

        purchase.getItems().forEach(purchaseItem -> {
            purchaseItem.setDiscount(0.0);
            purchaseItem.setProduct(productService.findById(purchaseItem.getProduct().getId()));
            purchaseItem.setPrice(purchaseItem.getProduct().getPrice());
            purchaseItem.setPurchase(purchase);
        });

        paymentRepository.save(purchase.getPayment());
        purchaseItemRepository.saveAll(purchase.getItems());
        emailService.sendOrderConfirmationHtmlEmail(purchase);
        return repository.save(purchase);
    }

    public Page<Purchase> findPage(Integer page, Integer size, String orderBy, String direction) {

        UserSS user = UserService.authenticated();
        if (user == null) throw new AuthorizationException("Access denied.");

        PageRequest pageRequest = PageRequest
                .of(page, size, Sort.Direction.valueOf(StringUtils.capitalize(direction)), orderBy);

        Client client = clientService.findById(user.getId());
        return repository.findByClient(client, pageRequest);
    }
}
