package dev.gabriel.storeproject.service.database;

import dev.gabriel.storeproject.domain.*;
import dev.gabriel.storeproject.domain.enums.ClientType;
import dev.gabriel.storeproject.domain.enums.PaymentStatus;
import dev.gabriel.storeproject.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DBService {

    final PurchaseItemRepository purchaseItemRepository;
    final PurchaseRepository purchaseRepository;
    final CategoryRepository categoryRepository;
    final AddressRepository addressRepository;
    final PaymentRepository paymentRepository;
    final ProductRepository productRepository;
    final ClientRepository clientRepository;
    final StateRepository stateRepository;
    final CityRepository cityRepository;
    final BCryptPasswordEncoder passwordEncoder;

    public void instantiateTestDatabase() throws ParseException {

        Category cat1 = new Category("Informatics");
        Category cat2 = new Category("Office");
        Category cat3 = new Category("House");
        Category cat4 = new Category("Electronics");
        Category cat5 = new Category("Gardening");
        Category cat6 = new Category("Misc");
        Category cat7 = new Category("Perfumes");

        Product p1 = new Product("Computer", 2000);
        Product p2 = new Product("Printer", 800);
        Product p3 = new Product("Mouse", 80);
        Product p4 = new Product("Desk", 80);
        Product p5 = new Product("Towel", 80);
        Product p6 = new Product("Mattress", 80);
        Product p7 = new Product("TV", 80);
        Product p8 = new Product("Mower", 80);
        Product p9 = new Product("Nightlight", 80);
        Product p11 = new Product("Necklace", 80);
        Product p10 = new Product("Shampoo", 80);


        cat1.getProducts().addAll(List.of(p1, p2, p3));
        cat2.getProducts().addAll(List.of(p2, p4));
        cat3.getProducts().addAll(List.of(p5, p6));
        cat4.getProducts().addAll(List.of(p1, p2, p3, p7));
        cat5.getProducts().add(p8);
        cat6.getProducts().addAll(List.of(p9, p10));
        cat7.getProducts().add(p11);

        p1.getCategories().addAll(List.of(cat1, cat4));
        p2.getCategories().addAll(List.of(cat1, cat2, cat4));
        p3.getCategories().addAll(List.of(cat1, cat4));
        p4.getCategories().add(cat2);
        p5.getCategories().add(cat3);
        p6.getCategories().add(cat3);
        p7.getCategories().add(cat4);
        p8.getCategories().add(cat5);
        p9.getCategories().add(cat6);
        p10.getCategories().add(cat6);
        p11.getCategories().add(cat7);

        State st1 = new State("Florida");
        State st2 = new State("Texas");

        City c1 = new City("Winter Springs", st1);
        City c2 = new City("Orlando", st1);
        City c3 = new City("Houston", st2);

        st1.getCities().add(c1);
        st2.getCities().add(c2);
        st2.getCities().add(c3);

        Client cli1 = new Client("Mary Jane", "gabriellinsmedeiros@gmail.com", "12332132", ClientType.PERSON, passwordEncoder.encode("123"));
        Client cli2 = new Client("John Dilly", "johnny@mail.com", "12758492", ClientType.PERSON, passwordEncoder.encode("123"));

        cli1.getPhoneNumbers().add("1234567890");
        cli1.getPhoneNumbers().add("0987654321");


        Address e1 = new Address("Main Street", "300", "Apt 123", "51030450", cli1, c1);
        Address e2 = new Address("Seen Street", "660", "", "99999999", cli1, c2);

        cli1.getAddresses().add(e1);
        cli1.getAddresses().add(e2);


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Purchase pur1 = new Purchase(sdf.parse("30/09/2017 10:32"), cli1, e1);
        Purchase pur2 = new Purchase(sdf.parse("10/10/2017 19:35"), cli1, e2);

        Payment pay1 = new CardPayment(PaymentStatus.PAID, pur1, 6);
        Payment pay2 = new InvoicePayment(PaymentStatus.PENDING, pur2, sdf.parse("20/10/2012 00:00"), null);

        pur1.setPayment(pay1);
        pur2.setPayment(pay2);

        cli1.getPurchases().addAll(List.of(pur1, pur2));

        PurchaseItem pi1 = new PurchaseItem(pur1, p1, 0.00, 1, 2000.00);
        PurchaseItem pi2 = new PurchaseItem(pur1, p3, 0.00, 2, 80.00);
        PurchaseItem pi3 = new PurchaseItem(pur2, p2, 100.00, 1, 800.00);

        pur1.getItems().add(pi1);
        pur1.getItems().add(pi2);
        pur2.getItems().add(pi3);

        p1.getItems().add(pi1);
        p2.getItems().add(pi3);
        p3.getItems().add(pi2);

        categoryRepository.saveAll(List.of(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
        productRepository.saveAll(List.of(p1, p2, p3, p4, p5, p6, p7 ,p8, p9 ,p10, p11));
        stateRepository.saveAll(List.of(st1, st2));
        cityRepository.saveAll(List.of(c1, c2, c3));
        clientRepository.saveAll(List.of(cli1, cli2));
        addressRepository.saveAll(List.of(e1, e2));
        purchaseRepository.saveAll((List.of(pur1, pur2)));
        paymentRepository.saveAll(List.of(pay1, pay2));
        purchaseItemRepository.saveAll(List.of(pi1, pi2, pi3));
    }
}
