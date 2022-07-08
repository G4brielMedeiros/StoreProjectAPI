package dev.gabriel.storeproject;

import dev.gabriel.storeproject.domain.Category;
import dev.gabriel.storeproject.domain.Product;
import dev.gabriel.storeproject.repository.CategoryRepository;
import dev.gabriel.storeproject.repository.ProductRepository;
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
	public static void main(String[] args) {
		SpringApplication.run(StoreProjectApplication.class, args);
	}

	public void run(String... args) throws Exception {
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

		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));
	}

}
