package application;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import models.Card;

public class CardView extends ImageView {
	public static double cardWidth = 60.0; // set the width of the card image
	public static double cardHeight = 80.0; // set the height of the card image
	private final Image faceDownImage = new Image(CardView.class.getResource("/images/cards/card-face-down.jpeg").toExternalForm());
	private final Image faceUpImage;
	private final Card card;
	
	public CardView(Card card) {
		this.card = card;
		this.faceUpImage = new Image(CardView.class.getResource("/images/card-"+card.getSuit().name()+"-"+card.getSuit().name()+".png").toExternalForm());
		setFitWidth(cardWidth); // set the width of the image view
		setFitHeight(cardHeight); // set the height of the image view
		setImage(faceDownImage);
	}

	public Card getCard() {
		return card;
	}
	
	public void flipCard() {
		card.flip();
		
		if(card.isFaceUp()) {
			setImage(faceUpImage);
		} else {
			setImage(faceDownImage);
		}
	}
	
	
	private void highlightCard(ImageView imageView) {

        // Create a DropShadow effect with a yellow highlight border
        DropShadow highlight = new DropShadow();
        highlight.setColor(Color.YELLOW);
        highlight.setRadius(20);
        highlight.setSpread(0.5);

        // Set the effect on the ImageView
        imageView.setEffect(highlight);
	}
	
}
