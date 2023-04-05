package controllers;

import java.util.ArrayList;
import java.util.List;

import application.FoundationGroupView;
import application.FoundationView;
import application.StockView;
import application.TableauGroupView;
import application.TableauView;
import application.WasteView;
import javafx.scene.layout.Pane;
import models.Card;
import models.Deck;
import models.Foundation;
import models.Move;
import models.Pile;
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
	private TableauGroupView tableauGroupView;
	private StockView stockView;
	private WasteView wasteView;
	private FoundationGroupView foundationGroupView;
	
	Move currentMove;
	Card cardSelected;
	
	public void prepareGameComponents() {
        // fill the deck and shuffle it
		deck.reset();
        deck.shuffle();


        // prepare the Tableau
    	final List<TableauView> tableauViews = new ArrayList<>(tableauColumnCount);
        for(int i=1;i <= tableauColumnCount; i++) {
        	tableauViews.add(new TableauView(new Tableau()));
        }
        for(int i=0;i < tableauViews.size(); i++) {
        	for(int j= 0; j<tableauViews.size();j++) {	
        		if(j < i) {
        			// don't add a card
        		} else if (j==i) {
        			// add a card face up
            		Card card =deck.takeTopCard();
            		card.flip(); // put the card in face up position
            		tableauViews.get(j).addCard(card);
        		} else {
        			// add a card face down
            		Card card =deck.takeTopCard();
            		tableauViews.get(j).addCard(card);
        		}
        	}
        }
        tableauGroupView = new TableauGroupView(tableauViews);
        
        // prepare the waste 
        Waste waste = Waste.getInstance();
        wasteView = new WasteView(waste);
        
        // add the leftover deck to the stock
        Stock stock = new Stock(deck.getCards());
        stockView = new StockView(stock, wasteView);
	
        // prepare foundation groups
    	final List<FoundationView> foundations = new ArrayList<>(foundationColumnCount);
    	foundations.add(new FoundationView(new Foundation(Card.Suit.HEARTS)));
    	foundations.add(new FoundationView(new Foundation(Card.Suit.DIAMONDS)));
    	foundations.add(new FoundationView(new Foundation(Card.Suit.CLUBS)));
    	foundations.add(new FoundationView(new Foundation(Card.Suit.SPADES)));
        foundationGroupView = new FoundationGroupView(foundations);
        

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

        
        wasteView.setOnMouseClicked(event -> {
			// if empty, restart the cycle
			if(!waste.getCards().isEmpty()) {
				Card card = wasteView.getWaste().selectTopCard();
				startMove(wasteView, wasteView.getWaste().getCards().indexOf(card), wasteView.getWaste(), card);
			}
		});
        
		foundationGroupView.getFoundationViews().forEach((foundationView) -> {
			foundationView.setOnMouseClicked(event -> {
				if(foundationView.isDestination()) {
					finishMove(foundationView, foundationView.getFoundation());
				} else if(!foundationView.getFoundation().getCards().isEmpty()) {
					Card card = foundationView.getFoundation().selectTopCard();
					int index = foundationView.getFoundation().getCards().indexOf(card);
					startMove(foundationView, index,foundationView.getFoundation(), card);
				}
			});
		});
		
		tableauGroupView.tableauViews().forEach((tableauView) -> {
			tableauView.getTableau().getCards().forEach((card)->{
				card.getCardImageView().setOnMouseClicked(event -> {
					if(!tableauView.getTableau().isEmpty()) {
						Card topCard = tableauView.getTableau().selectTopCard();
						if(!topCard.isFaceUp()) topCard.flip();
					}
					
					if(card.isDestination()) {
						finishMove(tableauView, tableauView.getTableau());
					} else if(!tableauView.getTableau().getCards().isEmpty()) {
						int index = tableauView.getTableau().getCards().indexOf(card);
						startMove(tableauView, index, tableauView.getTableau(), card);
					}
				});
			});
		});
	}
	
	private void finishMove(Pane newView, Pile newPile) {
		currentMove.setDestinationPile(newPile, newView, newPile.size());
		currentMove.execute();
		resetAllCardEffects();
	}
	
	private void startMove(Pane view, int index, Pile pile,Card card) {
		if(card.getSelected()) {
			card.setSelected(false);
			
			currentMove = null;
			cardSelected = null;
			
			resetAllCardEffects();
		} else {
			resetAllCardEffects();
			
			currentMove = new Move(pile, view, index, 1);
			card.setSelected(true);
			highlightDestinations(card);
			
			cardSelected = card;
		}
	}
	
	private void highlightDestinations(Card card) {
		tableauGroupView.tableauViews().forEach((tableauView) -> {
			if(tableauView.getTableau().canAdd(card)) {
				if(!tableauView.getTableau().isEmpty()) {
					tableauView.getTableau().selectTopCard().displayAsDestination(tableauView.getTableau().selectTopCard().getCardImageView());
				}
			}
		});
		foundationGroupView.getFoundationViews().forEach((foundationView) -> {
			if(foundationView.getFoundation().canAdd(card)){
				foundationView.displayAsDestination(foundationView);
			}
		});
	}
	
	private void resetAllCardEffects() {
		currentMove = null;
		cardSelected = null;
		
		wasteView.getWaste().getCards().forEach((card) -> {
			card.resetEffect();
		});
		tableauGroupView.tableauViews().forEach((tableauView) -> {
			if(!tableauView.getTableau().isEmpty()) {
				Card topCard = tableauView.getTableau().selectTopCard();
				if(!topCard.isFaceUp()) topCard.flip();
			}
			
			tableauView.getTableau().getCards().forEach((card) -> {
				card.resetEffect();
				// this will prevent an already established listeners from the tableau
				card.getCardImageView().setOnMouseClicked(event -> {
					if(!card.isFaceUp()) return;

					if(card.isDestination()) {
						finishMove(tableauView, tableauView.getTableau());
					} else if(!tableauView.getTableau().getCards().isEmpty()) {
						int index = tableauView.getTableau().getCards().indexOf(card);
						startMove(tableauView, index, tableauView.getTableau(), card);
					}
				});
			});
		});
		foundationGroupView.getFoundationViews().forEach((foundationView) -> {
			foundationView.removeAsDestination(foundationView);
			
			foundationView.getFoundation().getCards().forEach((card) -> {
				card.resetEffect();
				// this will prevent an already established click listener from the tableau
				card.getCardImageView().setOnMouseClicked(event -> {});
			});
		});
	}
	
	public TableauGroupView getTableauView() {
		return tableauGroupView;
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
