package com.example.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.Category;
import com.example.Bookstore.domain.CategoryRepository;
import com.example.Bookstore.domain.User;
import com.example.Bookstore.domain.UserRepository;


@SpringBootApplication
public class BookstoreApplication {
	//Tällä voidaan logittaa omatekoisia logeja
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	//Tämä ajetaan kun ohjelma ajetaan. Tänne voidaan tunkea kaikke mitä halutaan ohjelman pitävän alkuksi sisällään esim. testidataa
	@Bean
	public CommandLineRunner bookstoreDemo(BookRepository brepo, CategoryRepository crepo, UserRepository urepo) {
		return (args) -> {
			log.info("Laitetas pari käyttäjää");
			urepo.save(new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "user@bookstore.com", "USER"));
			urepo.save(new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "admin@bookstore.com", "ADMIN"));
			
			log.info("Toimii ennen addaamista.");
			crepo.save(new Category("Tiede"));
			crepo.save(new Category("Fiktio"));
			
			brepo.save(new Book("Sanakirja", "Joku Jokunen", 1987, "951-98548-9-1", 59.99, crepo.findByName("Tiede").get(0)));
			brepo.save(new Book("Hieno kirja", "Kalle Hieno", 2010, "951-98548-9-2", 79.99, crepo.findByName("Fiktio").get(0)));
			brepo.save(new Book("Huono kirja", "Matti Huono", 2020, "951-98548-9-3", 2.99, crepo.findByName("Fiktio").get(0)));
			log.info("Toimii sen jälkeenkin.");
			
			for (Book book : brepo.findAll()) {
				log.info(book.toString());
			}
			log.info("Ja pystytään näköjään näyttämäänkin kaikki jee.");
		};
	}

}
