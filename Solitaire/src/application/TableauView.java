package application;


import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import models.Card;
import models.Tableau;

public class TableauView extends VBox implements CardDestination {
	private boolean destination = false;
	private final Tableau tableau;
	
	private Rectangle base;
		
	public TableauView(Tableau tableau) {
		this.tableau = tableau;
		setSpacing(-Card.cardHeight+20); // set the spacing between the child views

		// create a rectangle with the size of the cards
		base = new Rectangle(Card.cardWidth, Card.cardHeight);
		// set the stroke color of the rectangle
		base.setStroke(Color.BLACK);
		base.setFill(Color.TRANSPARENT);
//		getChildren().add(base);
	}
	
	public void addCard(Card card) {
		tableau.addCard(card);
		getChildren().add(card.getCardImageView());
	}
	

	public Tableau getTableau() {
		return tableau;
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
