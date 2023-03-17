package models;

import java.util.List;

public class Move {
    private final Pile sourcePile;
    private final Pile destinationPile;
    private final int sourceIndex;
    private final int destinationIndex;
    private final int cardCount;

    public Move(Pile sourcePile, Pile destinationPile, int sourceIndex, int destinationIndex, int cardCount) {
        this.sourcePile = sourcePile;
        this.destinationPile = destinationPile;
        this.sourceIndex = sourceIndex;
        this.destinationIndex = destinationIndex;
        this.cardCount = cardCount;
    }

    public void execute() {
        List<Card> cards = sourcePile.removeCards(sourceIndex, cardCount);
        destinationPile.addCards(destinationIndex, cards);
    }
}
