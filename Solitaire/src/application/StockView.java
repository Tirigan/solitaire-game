package application;

import java.util.List;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import models.Card;
import models.Stock;

public class StockView extends StackPane {
	private final Stock stock;

	private void addCardsToView() {
		for(int i=0; i < stock.getCards().size() ;i++) {
			final Card card = stock.getCard(i);
			if(card.isFaceUp()) card.flip();
			getChildren().add(card.getCardImageView());
		}
	}
	
	public StockView(Stock stock, WasteView wasteView) {
		this.stock = stock;
		// create a rectangle with the size of the cards
		Rectangle rectangle = new Rectangle(Card.cardWidth, Card.cardHeight);
		// set the stroke color of the rectangle
		rectangle.setStroke(Color.BLACK);
		rectangle.setFill(Color.TRANSPARENT);
		getChildren().add(rectangle);
		
		addCardsToView();
		
		this.setOnMouseClicked(event -> {
			// if empty, restart the cycle
			if(stock.getCards().isEmpty()) {
				// grab the cards from the waste and add them back to the stock
				List<Card> cards = wasteView.getWaste().getCards();
				stock.addCards(cards);
				addCardsToView();
				// reset the waste to zero cards
				wasteView.getWaste().removeCards(0, cards.size());
				wasteView.resetView();
				return;
			}
			
			// if not empty, take the top card and move it to the waste
			Card card = stock.takeTopCard();
			getChildren().remove(getChildren().size()-1);
			card.flip();
			wasteView.addCard(card);
		});
	}
}
