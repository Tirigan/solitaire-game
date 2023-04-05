package models;

import application.CardDestination;
import application.CardView;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Card implements CardDestination {
	private boolean selected = false;
	private boolean destination = false;

	public static double cardWidth = 60.0; // set the width of the card image
	public static double cardHeight = 80.0; // set the height of the card image
	private final Image faceDownImage = new Image(CardView.class.getResource("/images/cards/card-face-down.jpeg").toExternalForm());
	private final Image faceUpImage;
	ImageView imageView = new ImageView();

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
		if(isFaceUp()) {
			imageView.setImage(faceUpImage); 
		} else {
			imageView.setImage(faceDownImage); 
		}
		imageView.setFitWidth(cardWidth); // set the width of the image view
        imageView.setFitHeight(cardHeight); // set the height of the image view
                
		return imageView;
	}
	
	
	public void setSelected(boolean selected) {
		this.selected = selected;
		if(selected) {
			 // Create a DropShadow effect with a yellow highlight border
	        DropShadow highlight = new DropShadow();
	        highlight.setColor(Color.YELLOW);
	        highlight.setRadius(20);
	        highlight.setSpread(0.5);
	        imageView.setEffect(highlight);
		} else {
			resetEffect();
		}
	}
	

	public boolean getSelected() {
		return selected;
	}

	public void resetEffect() {
        imageView.setEffect(null);
	}
	

    @Override
    public String toString() {
        return rank + " of " + suit;
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
