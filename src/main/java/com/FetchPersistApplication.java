package com;

import com.entity.Card;
import com.repository.JdbcRepository;
import com.service.CardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FetchPersistApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CardService cardService;

	public static void main(String[] args) {
		SpringApplication.run(FetchPersistApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Uncomment out to have some rows inserted

//		System.out.println("\n>> -----------------------------------------------------------------");
//		System.out.println("\n>> Initial cards in DB: " + cardService.findAll());
//
//		System.out.println("\n>> -----------------------------------------------------------------");
//		System.out.println(">> Inserting several cards");
//		Card card1 = new Card("Somename1", "+38096...");
//		Card card2 = new Card("Somename2", "+38063...");
//		Card card4 = new Card("Somename4", "+38067...");
//		Card card3 = new Card("Somename3", "+38050...");
//		cardService.insert(card1);
//		cardService.insert(card2);
//		cardService.insert(card3);
//		cardService.insert(card4);
//		System.out.println(">> All cards: " + cardService.findAll());

	}
}

