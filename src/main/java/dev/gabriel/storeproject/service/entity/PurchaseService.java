package dev.gabriel.storeproject.service.entity;

import dev.gabriel.storeproject.domain.InvoicePayment;
import dev.gabriel.storeproject.domain.Purchase;
import dev.gabriel.storeproject.domain.enums.PaymentStatus;
import dev.gabriel.storeproject.repository.PaymentRepository;
import dev.gabriel.storeproject.repository.PurchaseItemRepository;
import dev.gabriel.storeproject.repository.PurchaseRepository;
import dev.gabriel.storeproject.service.email.EmailService;
import dev.gabriel.storeproject.service.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class PurchaseService implements EntityService<Purchase>{

    final PurchaseRepository repository;
    final PaymentRepository paymentRepository;
    final InvoiceService invoiceService;
    final ProductService productService;
    final PurchaseItemRepository purchaseItemRepository;
    final ClientService clientService;

    final EmailService emailService;

    public Purchase findById(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("" + Purchase.class.getSimpleName() + " not found. Id: " + id));
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


}
