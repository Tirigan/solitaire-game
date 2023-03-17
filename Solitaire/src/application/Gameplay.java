package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import controllers.GameController;
import javafx.application.Application;
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
import models.Card;
import models.Deck;
import models.Tableau;



public class Gameplay {

    private Scene scene;

    public Gameplay(Stage primaryStage) {

        // Create a pane to hold our content
        Pane root = new Pane();

        // Set the background color to blue
        root.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));

        // Set the window size to 900x600 and make it not resizable
        scene = new Scene(root, 900, 600);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        
        // Start the Game
        GameController gameController = new GameController();
        gameController.startGame();

        // Set the title of the window
        primaryStage.setTitle("Solitaire");        

        // Create a button for showing the rules
        Button rulesButton = new Button("Rules");
        rulesButton.setPrefWidth(120);
        rulesButton.setLayoutX(scene.getWidth() - rulesButton.getPrefWidth() - 760);
        rulesButton.setLayoutY(550);
        rulesButton.setOnAction(e -> {
            // Show the rules popup
            RulesPopup.showPopup(primaryStage);
        });
        root.getChildren().add(rulesButton);


        // Create a button for exiting the game
        Button exitButton = new Button("Exit Game");
        exitButton.setPrefWidth(120);
        exitButton.setLayoutX(scene.getWidth() - exitButton.getPrefWidth() - 20);
        exitButton.setLayoutY(550);
        exitButton.setOnAction(e -> {
            // Show a confirmation dialog box
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Exit");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to exit the game?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Go back to the game menu
                GameMenu gameMenu = new GameMenu();
                try {
                    gameMenu.start(primaryStage);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        root.getChildren().add(exitButton);

    }

    public Scene getScene() {
        return scene;
    }

}
