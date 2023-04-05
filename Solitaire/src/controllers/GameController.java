package controllers;

import java.util.ArrayList;
import java.util.List;

import application.FoundationGroupView;
import application.FoundationView;
import application.StockView;
import application.TableauGroupView;
import application.WasteView;
import models.Card;
import models.Deck;
import models.Foundation;
import models.Stock;
import models.Tableau;
import models.Waste;

public class GameController {
	// Deck to play with
	private final Deck deck = new Deck();
	// Amount of Columns in the Tableau
    private final int tableauColumnCount = 7;
	// Amount of Columns in the Foundation
    private final int foundationColumnCount = 4;
	// The Game View Components
	private TableauGroupView tableauView;
	private StockView stockView;
	private WasteView wasteView;
	private FoundationGroupView foundationGroupView;

	
	Card cardSelected;
	
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
        tableauView = new TableauGroupView(tableaus);
        
        // prepare the waste 
        Waste waste = Waste.getInstance();
        wasteView = new WasteView(waste);
        
        // add the leftover deck to the stock
        Stock stock = new Stock(deck.getCards());
        stockView = new StockView(stock, wasteView);
	
        stockView.setOnMouseClicked(event -> {
        	if(cardSelected != null) return;
        	
			// if empty, restart the cycle
			if(stock.getCards().isEmpty()) {
				// grab the cards from the waste and add them back to the stock
				List<Card> cards = wasteView.getWaste().getCards();
				stock.addCards(cards);
				stockView.addCardsToView();
				// reset the waste to zero cards
				wasteView.getWaste().removeCards(0, cards.size());
				wasteView.resetView();
				return;
			}
			
			// if not empty, take the top card and move it to the waste
			Card card = stock.takeTopCard();
			stockView.getChildren().remove(stockView.getChildren().size()-1);
			card.flip();
			wasteView.addCard(card);
		});

        // prepare foundation groups
    	final List<FoundationView> foundations = new ArrayList<>(foundationColumnCount);
    	foundations.add(new FoundationView(new Foundation(Card.Suit.HEARTS)));
    	foundations.add(new FoundationView(new Foundation(Card.Suit.DIAMONDS)));
    	foundations.add(new FoundationView(new Foundation(Card.Suit.CLUBS)));
    	foundations.add(new FoundationView(new Foundation(Card.Suit.SPADES)));
        foundationGroupView = new FoundationGroupView(foundations);
        
        
        wasteView.setOnMouseClicked(event -> {
			// if empty, restart the cycle
			if(!waste.getCards().isEmpty()) {
				Card card = wasteView.getWaste().selectTopCard();
				if(card.getSelected()) {
					card.setSelected(false);
					cardSelected = null;
					resetAllCardEffects();
				} else {
					resetAllCardEffects();
					cardSelected = card;
					card.setSelected(true);
					highlightDestinations(card);
				}
			}
		});
	}
	
	public void highlightDestinations(Card card) {
		tableauView.getTableaus().forEach((tableau) -> {
			if(tableau.canAdd(card)) {
				if(!tableau.isEmpty()) {
					tableau.selectTopCard().displayAsDestination();
				}
			}
		});
		foundationGroupView.getFoundationViews().forEach((foundationView) -> {
			if(foundationView.getFoundation().canAdd(card)){
				if(!foundationView.getFoundation().isEmpty()) {
					foundationView.getFoundation().selectTopCard().displayAsDestination();
				} else {
					foundationView.displayAsDestination();
				}
			}
		});
	}
	
	public void resetAllCardEffects() {
		wasteView.getWaste().getCards().forEach((card) -> {
			card.resetEffect();
		});
		tableauView.getTableaus().forEach((tableau) -> {
			tableau.getCards().forEach((card) -> {
				card.resetEffect();
			});
		});
		foundationGroupView.getFoundationViews().forEach((foundationView) -> {
			foundationView.removeAsDestination();
			foundationView.getFoundation().getCards().forEach((card) -> {
				card.resetEffect();
			});
		});
	}
	
	public TableauGroupView getTableauView() {
		return tableauView;
	}


	public StockView getStockView() {
		return stockView;
	}
	
	public FoundationGroupView getFoundationGroupView() {
		return foundationGroupView;
	}

	public WasteView getWasteView() {
		return wasteView;
	}
	
	
	
	
}
