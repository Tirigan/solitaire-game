package models;

import java.util.ArrayList;
import java.util.List;

public class Stock extends Pile {
    private final List<Card> originalCards;

    public Stock(List<Card> cards) {
        super();
        this.originalCards = new ArrayList<>(cards);
        this.cards.addAll(cards);
    }

    public void reset() {
        cards.clear();
        cards.addAll(originalCards);
    }

    public void moveTopCardToWaste() {
    	Card card = takeTopCard();
        if (card != null) {
            card.flip();
            Waste.getInstance().addCard(card);
        }
    }

    public boolean canReset() {
        return !cards.isEmpty();
    }

}