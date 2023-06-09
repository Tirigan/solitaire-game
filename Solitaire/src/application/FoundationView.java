package application;


import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import models.Card;
import models.Foundation;

public class FoundationView extends StackPane implements CardDestination {
	private boolean destination = false;
	private final Foundation foundation;
	
	private Rectangle base;
		
	public FoundationView(Foundation foundation) {
		this.foundation = foundation;
		// create a rectangle with the size of the cards
		base = new Rectangle(Card.cardWidth, Card.cardHeight);
		// set the stroke color of the rectangle
		base.setStroke(Color.BLACK);
		base.setFill(Color.TRANSPARENT);
		getChildren().add(base);
	}
	
	public void addCard(Card card) {
		foundation.addCard(card);
		getChildren().add(card.getCardImageView());
	}
	

	public Foundation getFoundation() {
		return foundation;
	}
	
	@Override
	public boolean isDestination() {
		return destination;
	}

	@Override
	public void setIsDestination(boolean value) {
		this.destination = value;
	}
}
