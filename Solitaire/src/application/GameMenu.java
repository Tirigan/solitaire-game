package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class GameMenu extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create a pane to hold our content
        Pane root = new Pane();
     

        // Set the background color to green
        root.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));

        // Set the window size to 620 and make it not resizable
        Scene scene = new Scene(root, 620, 600);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        // Set the title of the window
        primaryStage.setTitle("Solitaire Menu");

     // Create a label for the title
        Label titleLabel = new Label("Solitaire");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setLayoutX(0);
        titleLabel.setLayoutY(20);
        titleLabel.setPrefWidth(scene.getWidth());
        root.getChildren().add(titleLabel);
        
     // Add a label for the version number
        Label versionLabel = new Label("Version 3.0");
        versionLabel.setFont(new Font("Arial", 14));
        versionLabel.setTextFill(Color.GRAY);
        versionLabel.setLayoutX(scene.getWidth() - 100);
        versionLabel.setLayoutY(20);
        root.getChildren().add(versionLabel);

        
     // Create a button for starting the game
        Button startButton = new Button("Start Game");
        startButton.setPrefWidth(120);
        startButton.setLayoutX(scene.getWidth() / 2 - startButton.getPrefWidth() / 2);
        startButton.setLayoutY(100);
        startButton.setOnAction(e -> {
        	Gameplay.resetGameplay();
            // Create a new instance of the Gameplay class
            Gameplay gameplay = Gameplay.getInstance(primaryStage);
            
            // Set the scene of the primaryStage to the scene of the Gameplay instance
            primaryStage.setScene(gameplay.getScene());
        });
        root.getChildren().add(startButton);


        // Create a button for showing the rules
        Button rulesButton = new Button("Rules");
        rulesButton.setPrefWidth(120);
        rulesButton.setLayoutX(scene.getWidth() / 2 - rulesButton.getPrefWidth() / 2);
        rulesButton.setLayoutY(150);
        rulesButton.setOnAction(e -> {
            // Show the rules popup
            RulesPopup.showPopup(primaryStage);
        });
        root.getChildren().add(rulesButton);

        // Show the window
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}











