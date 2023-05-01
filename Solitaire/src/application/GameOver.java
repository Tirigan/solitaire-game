package application;

import java.util.Optional;
import java.util.TimerTask;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GameOver {
	
	public static void showGameOver(Stage owner, String timeString) {
	    // Create a pane to hold our content
	    Pane root = new Pane();

	    // Set the background color to green
	    root.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));

	    // Set the window size to 900x600 and make it not resizable
	    Scene scene = new Scene(root, 620, 600);
	    owner.setScene(scene);
	    owner.setResizable(false);

	    // Set the title of the window
	    owner.setTitle("Game Over");

	    // Create a label for the title
	    Label titleLabel = new Label("You Win! Congratulations!");
	    titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 36));
	    titleLabel.setAlignment(Pos.CENTER);
	    titleLabel.setLayoutX(0);
	    titleLabel.setLayoutY(100);
	    titleLabel.setPrefWidth(scene.getWidth());
	    root.getChildren().add(titleLabel);

	    // Create a label for the elapsed time
	    Label timeLabel = new Label("Game Time: " + timeString);
	    timeLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
	    timeLabel.setAlignment(Pos.CENTER);
	    timeLabel.setLayoutX(0);
	    timeLabel.setLayoutY(170);
	    timeLabel.setPrefWidth(scene.getWidth());
	    root.getChildren().add(timeLabel);

	    // Create a button for exiting the game
	    Button exitButton = new Button("Game Menu");
	    exitButton.setPrefWidth(120);
	    exitButton.setLayoutX(scene.getWidth() / 2 - exitButton.getPrefWidth() / 2);
	    exitButton.setLayoutY(225);
	    exitButton.setOnAction(e -> {
	        // Create a new instance of the GameMenu class
	        GameMenu gameMenu = new GameMenu();
	        try {
	            gameMenu.start(owner);
	        } catch (Exception e1) {
	            e1.printStackTrace();
	        }
	    });
	    root.getChildren().add(exitButton);

	    // Show the window
	    owner.show();
	}



}
