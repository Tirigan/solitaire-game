package models;

import java.util.List;

/// One of the seven piles to move cards into
public class Tableau extends Pile {
    public boolean canAdd(Card card) {
        if (cards.isEmpty()) {
            return card.getRank() == Card.Rank.KING;
        }
        Card topCard = takeTopCard();
        return !topCard.isSameColor(card) && topCard.getRank().getValue() == card.getRank().getValue() + 1;
    }

    public List<Card> getCards(int index) {
        if (index < 0 || index >= cards.size()) {
            return null;
        }
        return cards.subList(index, cards.size());
    }
    
    public boolean canRemoveCard(int index) {
        if (index < 0 || index >= cards.size()) {
            return false;
        }
        if (index == cards.size() - 1) {
            return true;
        }
        Card card = cards.get(index);
        Card nextCard = cards.get(index + 1);
        return card.isSameColor(nextCard) && card.getRank().getValue() == nextCard.getRank().getValue() + 1;
    }
    
}

