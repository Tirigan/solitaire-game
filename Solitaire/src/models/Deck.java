package models;

import java.util.ArrayList;
import java.util.Collections;

public class Deck extends Pile {

    public Deck() {
        cards = new ArrayList<>();
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card getCard(int index) {
        return cards.get(index);
    }
}
