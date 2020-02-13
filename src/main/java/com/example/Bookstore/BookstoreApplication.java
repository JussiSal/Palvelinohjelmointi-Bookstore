package com.example.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;


@SpringBootApplication
public class BookstoreApplication {
	//Tällä voidaan logittaa omatekoisia logeja
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	//Tämä ajetaan kun ohjelma ajetaan. Tänne voidaan tunkea kaikke mitä halutaan ohjelman pitävän alkuksi sisällään esim. testidataa
	@Bean
	public CommandLineRunner bookstoreDemo(BookRepository repo) {
		return (args) -> {
			log.info("Toimii ennen addaamista.");
			repo.save(new Book("Sanakirja", "Joku Jokunen", 1987, "951-98548-9-1", 59.99));
			repo.save(new Book("Hieno kirja", "Kalle Hieno", 2010, "951-98548-9-2", 79.99));
			repo.save(new Book("Huono kirja", "Matti Huono", 2020, "951-98548-9-3", 2.99));
			log.info("Toimii sen jälkeenkin.");
			
			for (Book book : repo.findAll()) {
				log.info(book.toString());
			}
			log.info("Ja pystytään näköjään näyttämäänkin kaikki jee.");
		};
	}

}
