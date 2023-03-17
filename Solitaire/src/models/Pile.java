package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a group of cards piled up
public abstract class Pile {
    protected List<Card> cards;

    public Pile() {
        this.cards = new ArrayList<>();
    }

    public Pile(List<Card> cards) {
        this.cards = cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void addCards(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public void addCards(int index, List<Card> cards) {
        this.cards.addAll(index, cards);
    }

    public Card takeTopCard() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(cards.size() - 1);
    }

    public List<Card> removeCards(int index, int count) {
        if (index < 0 || index + count > cards.size()) {
            return null;
        }
        List<Card> removedCards = new ArrayList<>(cards.subList(index, index + count));
        cards.removeAll(removedCards);
        return removedCards;
    }

    public Card getCard(int index) {
        if (index < 0 || index >= cards.size()) {
            return null;
        }
        return cards.get(index);
    }

    public int size() {
        return cards.size();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public void clear() {
        cards.clear();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }
}