package models;

import application.CardView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card {
	public static double cardWidth = 60.0; // set the width of the card image
	public static double cardHeight = 80.0; // set the height of the card image
	private final Image faceDownImage = new Image(CardView.class.getResource("/images/cards/card-face-down.jpeg").toExternalForm());
	private final Image faceUpImage;
	
    public enum Suit {
        HEARTS,
        SPADES,
        CLUBS,
        DIAMONDS
    }

    public enum Rank {
        ACE(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        JACK(11),
        QUEEN(12),
        KING(13);

        private final int value;

        Rank(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    private final Suit suit;
    private final Rank rank;
    private boolean faceUp;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
        this.faceUp = false;
		this.faceUpImage = new Image(CardView.class.getResource("/images/cards/card-"+suit.name()+"-"+rank.name()+".png").toExternalForm());
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    public boolean isFaceUp() {
        return faceUp;
    }

    public void flip() {
        faceUp = !faceUp;
    }

    public boolean isSameColor(Card otherCard) {
        if (otherCard == null) {
            return false;
        }
        return (suit == Suit.HEARTS || suit == Suit.DIAMONDS) && (otherCard.getSuit() == Suit.HEARTS || otherCard.getSuit() == Suit.DIAMONDS)
                || (suit == Suit.CLUBS || suit == Suit.SPADES) && (otherCard.getSuit() == Suit.CLUBS || otherCard.getSuit() == Suit.SPADES);
    }

    public boolean isSameSuit(Card otherCard) {
        if (otherCard == null) {
            return false;
        }
        return suit == otherCard.getSuit();
    }
    
	public ImageView getCardImageView() {
		ImageView imageView;
		if(isFaceUp()) {
			imageView = new ImageView(faceUpImage);
		} else {
			imageView = new ImageView(faceDownImage);
		}
		imageView.setFitWidth(cardWidth); // set the width of the image view
        imageView.setFitHeight(cardHeight); // set the height of the image view
		return imageView;
	}

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
