package dev.gabriel.storeproject;

import dev.gabriel.storeproject.domain.Category;
import dev.gabriel.storeproject.domain.City;
import dev.gabriel.storeproject.domain.Product;
import dev.gabriel.storeproject.domain.State;
import dev.gabriel.storeproject.repository.CategoryRepository;
import dev.gabriel.storeproject.repository.CityRepository;
import dev.gabriel.storeproject.repository.ProductRepository;
import dev.gabriel.storeproject.repository.StateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
@RequiredArgsConstructor
public class StoreProjectApplication implements CommandLineRunner {

	final CategoryRepository categoryRepository;
	final ProductRepository productRepository;
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

		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));
		stateRepository.saveAll(Arrays.asList(st1, st2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));
	}

}
