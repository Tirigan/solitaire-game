package application;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import models.Card;
import javafx.scene.input.ClipboardContent;

public class CardView extends ImageView {

    private final Card card;
    private final Image faceDownImage = new Image(CardView.class.getResource("/images/card-face-down.jpeg").toExternalForm());
    private final Image faceUpImage;

    private double startX, startY;

    public CardView(Card card) {
        super();
        this.card = card;
        this.faceUpImage = new Image(CardView.class.getResource("/images/card-" + card.getSuit().name() + "-" + card.getRank().name() + ".png").toExternalForm());
        setImage(faceDownImage);
        setOnMousePressed(event -> {
            startX = event.getSceneX();
            startY = event.getSceneY();
        });
        setOnMouseDragged(event -> {
            double offsetX = event.getSceneX() - startX;
            double offsetY = event.getSceneY() - startY;
            setTranslateX(offsetX);
            setTranslateY(offsetY);
        });
        setOnMouseReleased(event -> {
            // Add code to snap card to appropriate position on release
        });
        setOnDragDetected(event -> {
            Dragboard dragboard = startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString("card");
            dragboard.setContent(content);
            event.consume();
        });
    }

    public Card getCard() {
        return card;
    }

    public void showFaceUp() {
        setImage(faceUpImage);
    }

    public void showFaceDown() {
        setImage(faceDownImage);
    }
}