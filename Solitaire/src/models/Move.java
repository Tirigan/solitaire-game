package models;

import java.util.ArrayList;
import java.util.List;

import application.Gameplay;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class Move {
    private final int sourceIndex;
    private final Pile sourcePile;
    private final Pane sourcePileView;
    
    private Pile destinationPile;
    private Pane destinationPileView;
    private int destinationIndex;

    private final int cardCount;

    public Move(Pile sourcePile, Pane sourcePileView, int sourceIndex, int cardCount) {
        this.sourcePile = sourcePile;
        this.sourcePileView = sourcePileView;
        this.sourceIndex = sourceIndex;
        this.cardCount = cardCount;
    }
    
    public void setDestinationPile(Pile destinationPile, Pane destinationPileView, int destinationIndex) {
        this.destinationPile = destinationPile;
        this.destinationPileView = destinationPileView;
        this.destinationIndex = destinationIndex;
    }

    public void execute() {
		System.out.println("Moving card from " + sourcePile.getClass().getName() + " to " + destinationPile.getClass().getName());
        List<Card> cards = sourcePile.removeCards(sourceIndex, cardCount);
        List<Node> children = removeChildren(sourceIndex, cardCount);
        destinationPile.addCards(destinationIndex, cards);
        destinationPileView.getChildren().addAll(children);
        
     // Check if the destination pile is of the Foundation class, and add a point if it is
        if (destinationPile instanceof models.Foundation) {
            Gameplay gameplay = Gameplay.getInstance(null);
			gameplay.scorePoint();
        }
        
     // Check if the source pile is of the Foundation class, and remove a point if it is
        if (sourcePile instanceof models.Foundation) {
            Gameplay gameplay = Gameplay.getInstance(null);
			gameplay.removePoint();
        }
        
    }
    
    private List<Node> removeChildren(int index, int count) {
        if (index < 0 || index + count > sourcePileView.getChildren().size()) {
            return null;
        }
        List<Node> removedChildren = new ArrayList<>(sourcePileView.getChildren().subList(index, index + count));
        sourcePileView.getChildren().removeAll(removedChildren);
        return removedChildren;
    }
}
