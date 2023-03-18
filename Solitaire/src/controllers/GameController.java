package controllers;

import java.util.ArrayList;
import java.util.List;

import application.StockView;
import application.TableauView;
import models.Card;
import models.Deck;
import models.Stock;
import models.Tableau;

public class GameController {
	// Deck to play with
	private final Deck deck = new Deck();
	// Amount of Columns in the Tableau
    private final int tableauColumnCount = 7;
	// The Game View Components
	private TableauView tableauView;
	private StockView stockView;

	
	public void prepareGameComponents() {
        // fill the deck and shuffle it
		deck.reset();
        deck.shuffle();


        // prepare the Tableau
    	final List<Tableau> tableaus = new ArrayList<>(tableauColumnCount);
        for(int i=1;i <= tableauColumnCount; i++) {
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
        tableauView = new TableauView(tableaus);
        
        // add the leftover deck to the stock
        Stock stock = new Stock(deck.getCards());
        stockView = new StockView(stock);
	}
	
	public TableauView getTableauView() {
		return tableauView;
	}


	public StockView getStockView() {
		return stockView;
	}
	
}
