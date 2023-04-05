package application;

import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public interface CardDestination {
	
    boolean isDestination();
    void setIsDestination(boolean value);
		
	public default void displayAsDestination(Node node) {
	  DropShadow highlight = new DropShadow();
	  highlight.setColor(Color.RED);
	  highlight.setRadius(20);
	  highlight.setSpread(0.5);
	  node.setEffect(highlight);
	  setIsDestination(true);
	}
	public default void removeAsDestination(Node node) {
		node.setEffect(null);
		setIsDestination(false);
	}
}
