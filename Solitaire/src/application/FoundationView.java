package application;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import models.Card;
import models.Foundation;

public class FoundationView extends StackPane {
	
	private final Foundation foundation;
	
	private final StackPane stackPane = new StackPane();
	
	public FoundationView(Foundation foundation) {
		this.foundation = foundation;
		// create a rectangle with the size of the cards
		Rectangle rectangle = new Rectangle(Card.cardWidth, Card.cardHeight);
		// set the stroke color of the rectangle
		rectangle.setStroke(Color.BLACK);
		rectangle.setFill(Color.TRANSPARENT);
		getChildren().add(rectangle);
	}
	
	public void addCardView(CardView cardView) {
		foundation.addCard(cardView.getCard());
		stackPane.getChildren().add(cardView.getCardImageView());
	}

}
