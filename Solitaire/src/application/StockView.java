package application;


import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import models.Card;
import models.Stock;

public class StockView extends StackPane {
	private final Stock stock;

	public void addCardsToView() {
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
	}
}
