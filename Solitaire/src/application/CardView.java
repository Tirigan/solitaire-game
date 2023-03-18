package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Card;

public class CardView {
	private final Card card;
	private final Image faceDownImage = new Image(CardView.class.getResource("/images/card-face-down.jpeg").toExternalForm());
	private final Image faceUpImage;

	CardView(Card card) {
		this.card = card;
		this.faceUpImage = new Image(CardView.class.getResource("/images/card-"+card.getSuit().name()+"-"+card.getSuit().name()+".png").toExternalForm());
	}

	public Card getCard() {
		return card;
	}

	public ImageView getCardImageView() {
		if(card.isFaceUp()) {
			return new ImageView(faceUpImage);
		}
		return new ImageView(faceDownImage);
	}
	
}
