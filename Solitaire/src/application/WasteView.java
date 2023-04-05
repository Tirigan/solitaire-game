package application;

import java.util.List;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import models.Card;
import models.Waste;

public class WasteView extends StackPane {
	
	private final Waste waste;
		
	
	public WasteView(Waste waste) {
		this.waste = waste;
	}
	
	public void resetView() {
		getChildren().clear();

		// create a rectangle with the size of the cards
		Rectangle rectangle = new Rectangle(Card.cardWidth, Card.cardHeight);
		// set the stroke color of the rectangle
		rectangle.setStroke(Color.BLACK);
		rectangle.setFill(Color.TRANSPARENT);
		getChildren().add(rectangle);
	}
	
	public void addCard(Card card) {
		waste.addCard(card);
		getChildren().add(card.getCardImageView());
	}
	
	public Waste getWaste() {
		return waste;
	}
}
