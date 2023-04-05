package application;


import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import models.Card;
import models.Foundation;

public class FoundationView extends StackPane implements CardDestination {
	
	private final Foundation foundation;
	
	private Rectangle base;
	
	private final StackPane stackPane = new StackPane();
	
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
		stackPane.getChildren().add(card.getCardImageView());
	}
	

	public Foundation getFoundation() {
		return foundation;
	}

	@Override
	public void displayAsDestination() {
        DropShadow highlight = new DropShadow();
        highlight.setColor(Color.RED);
        highlight.setRadius(20);
        highlight.setSpread(0.5);
        base.setEffect(highlight);
	}

	@Override
	public void removeAsDestination() {
		base.setEffect(null);
	}

}
