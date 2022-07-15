package dev.gabriel.storeproject.service;

import dev.gabriel.storeproject.domain.InvoicePayment;
import dev.gabriel.storeproject.domain.Purchase;
import dev.gabriel.storeproject.domain.enums.PaymentStatus;
import dev.gabriel.storeproject.repository.PaymentRepository;
import dev.gabriel.storeproject.repository.PurchaseItemRepository;
import dev.gabriel.storeproject.repository.PurchaseRepository;
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

    public Purchase findById(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("" + Purchase.class.getSimpleName() + " not found. Id: " + id));
    }

    public Purchase add(Purchase purchase) {
        purchase.setId(null);
        purchase.setInstant(new Date());
        purchase.getPayment().setStatus(PaymentStatus.PENDING);
        purchase.getPayment().setPurchase(purchase);

        if (purchase.getPayment() instanceof InvoicePayment payment) {
            invoiceService.fillInvoicePayment(payment, purchase.getInstant());
        }



        purchase.getItems().forEach(purchaseItem -> {
            purchaseItem.setDiscount(0.0);
            purchaseItem.setPrice(productService.findById(purchaseItem.getProduct().getId()).getPrice());
            purchaseItem.setPurchase(purchase);
        });

        paymentRepository.save(purchase.getPayment());
        purchaseItemRepository.saveAll(purchase.getItems());
        return repository.save(purchase);
    }


}
