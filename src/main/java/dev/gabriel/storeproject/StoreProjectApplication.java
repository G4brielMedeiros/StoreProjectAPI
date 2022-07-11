package dev.gabriel.storeproject;

import dev.gabriel.storeproject.domain.*;
import dev.gabriel.storeproject.domain.enums.ClientType;
import dev.gabriel.storeproject.domain.enums.PaymentStatus;
import dev.gabriel.storeproject.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
@RequiredArgsConstructor
public class StoreProjectApplication implements CommandLineRunner {

	final PurchaseItemRepository purchaseItemRepository;
	final PurchaseRepository purchaseRepository;
	final CategoryRepository categoryRepository;
	final AddressRepository addressRepository;
	final PaymentRepository paymentRepository;
	final ProductRepository productRepository;
	final ClientRepository clientRepository;
	final StateRepository stateRepository;
	final CityRepository cityRepository;

	public static void main(String[] args) {
		SpringApplication.run(StoreProjectApplication.class, args);
	}

	public void run(String... args) throws ParseException {
		Category cat1 = new Category("Technology");
		Category cat2 = new Category("Office");

		Product p1 = new Product("Computer", 2000);
		Product p2 = new Product("Printer", 800);
		Product p3 = new Product("Mouse", 80);

		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().add(p2);

		p1.getCategories().add(cat1);
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().add(cat1);

		State st1 = new State("Florida");
		State st2 = new State("Texas");

		City c1 = new City("Winter Springs", st1);
		City c2 = new City("Orlando", st1);
		City c3 = new City("Houston", st2);

		st1.getCities().add(c1);
		st2.getCities().addAll(Arrays.asList(c2, c3));

		Client cli1 = new Client("Mary Jane", "mary@jane.com", "12332132", ClientType.PERSON);
		Client cli2 = new Client("John Dilly", "johnny@mail.com", "12758492", ClientType.PERSON);

		cli1.getPhoneNumbers().addAll(Arrays.asList("1234567890", "0987654321"));

		Address e1 = new Address("Main Street", "300", "Apt 123", "51030450", cli1, c1);
		Address e2 = new Address("Seen Street", "660", "", "99999999", cli1, c2);

		cli1.getAddresses().addAll(Arrays.asList(e1, e2));


		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Purchase pur1 = new Purchase(sdf.parse("30/09/2017 10:32"), cli1, e1);
		Purchase pur2 = new Purchase(sdf.parse("10/10/2017 19:35"), cli1, e2);

		Payment pay1 = new CreditPayment(PaymentStatus.PAID, pur1, 6);
		Payment pay2 = new InvoicePayment(PaymentStatus.PENDING, pur2, sdf.parse("20/10/2012 00:00"), null);

		pur1.setPayment(pay1);
		pur2.setPayment(pay2);

		cli1.getPurchases().addAll(Arrays.asList(pur1, pur2));

		PurchaseItem pi1 = new PurchaseItem(pur1, p1, 0.00, 1, 2000.00);
		PurchaseItem pi2 = new PurchaseItem(pur1, p3, 0.00, 2, 80.00);
		PurchaseItem pi3 = new PurchaseItem(pur2, p2, 100.00, 1, 800.00);

		pur1.getItems().addAll(Arrays.asList(pi1, pi2));
		pur2.getItems().add(pi3);

		p1.getItems().add(pi1);
		p2.getItems().add(pi3);
		p3.getItems().add(pi2);

		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));
		stateRepository.saveAll(Arrays.asList(st1, st2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));
		clientRepository.saveAll(Arrays.asList(cli1, cli2));
		addressRepository.saveAll(Arrays.asList(e1, e2));
		purchaseRepository.saveAll((Arrays.asList(pur1, pur2)));
		paymentRepository.saveAll(Arrays.asList(pay1, pay2));
		purchaseItemRepository.saveAll(Arrays.asList(pi1, pi2, pi3));


	}

}
