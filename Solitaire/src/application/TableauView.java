package application;

import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import models.Card;
import models.Tableau;

public class TableauView extends GridPane {
	private final List<Tableau> tableaus;

	public TableauView( List<Tableau> tableaus) {
		this.tableaus = tableaus;
		setHgap(10); // horizontal gap between cards
		setVgap(-65); // vertical gap between cards
		setAlignment(Pos.CENTER); // center the cards in the grid
		for(int i=0;i<tableaus.size();i++) {
			final Tableau tableau = tableaus.get(i);
			for(int j=0;j<tableau.getCards().size();j++) {
				Card card = tableau.getCard(j);
	            add(card.getCardImageView(), i, j); // add the image to the grid
			}
		}
	}
	
}
