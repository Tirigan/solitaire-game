package application;


import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import models.Card;
import models.Tableau;

public class TableauView extends VBox implements CardDestination {
	private boolean destination = false;
	private final Tableau tableau;
	
	private Rectangle base = new Rectangle(Card.cardWidth, Card.cardHeight);
	private StackPane parent = new StackPane();
		
	public TableauView(Tableau tableau) {
		this.tableau = tableau;
		
		setSpacing(-Card.cardHeight+20); // set the spacing between the child views

		// set the stroke color of the rectangle
		base.setStroke(Color.BLACK);
		base.setFill(Color.TRANSPARENT);
		
		parent.setAlignment(Pos.TOP_CENTER);
		parent.getChildren().add(base);
		parent.getChildren().add(this);
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
