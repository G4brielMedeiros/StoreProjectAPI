package dev.gabriel.storeproject;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class StoreProjectApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(StoreProjectApplication.class, args);
	}

	public void run(String... args) {
	}

}
