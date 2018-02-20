package com.controller;

import com.entity.Card;
import com.service.CardService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;


@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
public class FetchPersistApplicationTests {

	@Autowired
	private CardService cardService;

	@Test
	public void whenStartingApplication_thenNoCards() throws Exception {
		assertTrue(cardService.findAll().size()==0);
	}

	@Test
	public void whenInsertingSeveralCards_thenAllCardsAreInDb() throws Exception {
		Card card1 = new Card("Somename1", "+38096...");
		Card card2 = new Card("Somename2", "+38063...");
		Card card4 = new Card("Somename4", "+38067...");
		Card card3 = new Card("Somename3", "+38050...");
		cardService.insert(card1);
		cardService.insert(card2);
		cardService.insert(card3);
		cardService.insert(card4);
		assertTrue(cardService.findAll().size()==4);
	}

	@Test
	public void whenAddingMorePhonesToCard_thenPhoneAreAdded() throws Exception {
		assertTrue(cardService.findByName("Somename3").getPhones().equals("+38050..."));

		Card card = cardService.findByName("Somename3");

		// Add 2 phones to the same person
		card.setPhones("+38068...");
		cardService.update(card);
		card.setPhones("+38098...");
		cardService.update(card);

		assertTrue(cardService.findByName("Somename3").getPhones().equals("+38050...; +38068...; +38098..."));
	}

	@Test
	public void whenDeletingCard_thenItIsDeleted() throws Exception {
		Card card = new Card("Somename1", "+38096...");
		cardService.insert(card);

		assertTrue(cardService.findByName("Somename1") != null);
		int res = cardService.deleteByName("Somename1");
		assertTrue(res==1);
		assertTrue(cardService.findByName("Somename1") == null);
	}

}
