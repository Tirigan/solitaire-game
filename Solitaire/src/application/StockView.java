package application;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import models.Card;
import models.Stock;

public class StockView {
	private final Stock stock;

	public StockView( Stock stock) {
		this.stock = stock;
	}
	
	public Node buildView() {
		StackPane stockPane = new StackPane();
		
		for(int i=0; i < stock.getCards().size() ;i++) {
			final Card card = stock.getCard(i);
			stockPane.getChildren().add(card.getCardImageView());
		}
		return stockPane;
	}
	
}
