package dev.gabriel.storeproject;

import dev.gabriel.storeproject.domain.*;
import dev.gabriel.storeproject.domain.enums.ClientType;
import dev.gabriel.storeproject.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
@RequiredArgsConstructor
public class StoreProjectApplication implements CommandLineRunner {

	final CategoryRepository categoryRepository;
	final AddressRepository addressRepository;
	final ProductRepository productRepository;
	final ClientRepository clientRepository;
	final StateRepository stateRepository;
	final CityRepository cityRepository;
	public static void main(String[] args) {
		SpringApplication.run(StoreProjectApplication.class, args);
	}

	public void run(String... args) {
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

		cli1.getPhoneNumbers().addAll(Arrays.asList("1234567890", "0987654321"));
		Client cli2 = new Client("John Dilly", "johnny@mail.com", "12758492", ClientType.PERSON);

		Address e1 = new Address("Main Street", "300", "Apt 123", "51030450", cli1, c1);

		Address e2 = new Address("Seen Street", "660", "", "99999999", cli1, c2);

		cli1.getAddresses().addAll(Arrays.asList(e1, e2));


		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));
		stateRepository.saveAll(Arrays.asList(st1, st2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));
		clientRepository.saveAll(Arrays.asList(cli1, cli2));
		addressRepository.saveAll(Arrays.asList(e1, e2));


	}

}
