package models;


public class Foundation extends Pile {
    private final Card.Suit suit;

    public Foundation(Card.Suit suit) {
        super();
        this.suit = suit;
    }

    public boolean canAdd(Card card) {
        if (card.getSuit() != suit) {
            return false;
        }
        if (cards.isEmpty()) {
            return card.getRank() == Card.Rank.ACE;
        }
        Card topCard = takeTopCard();
        return topCard.getRank().getValue() == card.getRank().getValue() - 1;
    }
}