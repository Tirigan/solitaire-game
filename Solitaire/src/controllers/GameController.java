package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Card;
import models.Deck;
import models.Stock;
import models.Tableau;

public class GameController {
    private final int tableauCount = 7;
	private final List<Tableau> tableaus = new ArrayList<>(tableauCount);
	
	private Stock stock;

	public void startGame() {
        // Add a deck to the game and shuffle it
        Deck deck = new Deck();
        deck.shuffle();
        System.out.println("Deck size: " + deck.size());

        // prepare the tableaus
        for(int i=1;i <= tableauCount; i++) {
        	tableaus.add(new Tableau());
        }
        for(int i=0;i < tableaus.size(); i++) {
        	for(int j= 0; j<tableaus.size();j++) {	
        		if(j < i) {
        			// don't add a card
        		} else if (j==i) {
        			// add a card face up
            		Card card =deck.takeTopCard();
            		card.flip(); // put the card in face up position
            		tableaus.get(j).addCard(card);
        		} else {
        			// add a card face down
            		Card card =deck.takeTopCard();
            		tableaus.get(j).addCard(card);
        		}
        	}
        }
        for(int i=0;i < tableaus.size(); i++) {
            System.out.println("Tableau #"+i+" size: " + tableaus.get(i).size());
        }
        
        // add the leftover deck to the stock
        stock = new Stock(deck.getCards());
        System.out.println("Stock size: " + stock.size());
	}

	public List<Tableau> getTableaus() {
		return tableaus;
	}

	public Stock getStock() {
		return stock;
	}
	
}
