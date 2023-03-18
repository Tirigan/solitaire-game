package controllers;

import java.util.ArrayList;
import java.util.List;

import application.TableauView;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import models.Card;
import models.Deck;
import models.Stock;
import models.Tableau;

public class GameController {
	// Deck to play with
	private final Deck deck = new Deck();
	// Amount of Columns in the Tableau
    private final int tableauColumnCount = 7;
	// The Tableau View
	private TableauView tableauView;

	private Stock stock;
	
	// Scene to display the game in
    private Scene scene;
    
    public Scene getScene() {
    	return scene;
    }

	
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
        stock = new Stock(deck.getCards());
        System.out.println("Stock size: " + stock.size());
	}
	
	public TableauView getTableauView() {
		return tableauView;
	}


	public Stock getStock() {
		return stock;
	}
	
}
