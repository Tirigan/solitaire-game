package application;

import java.util.HashMap;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import controllers.GameController;
import javafx.scene.Scene;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.Card;



public class Gameplay {

    private Scene scene;
    private Timer timer;
    private int secondsPassed;
    private Label lastTimerLabel;
    private TimerTask timerTask;
    private int score;
    private Label scoreLabel;
    private Label emptyTimer;
    
 
    public Gameplay(Stage primaryStage) {

        // Create a pane to hold our content
        Pane root = new Pane();
       
        
        // Set the background color to blue
        root.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));

        // Set the window size to 900x600 and make it not resizable
        scene = new Scene(root, 900, 600);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        

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

        
        // Start the Game
        GameController gameController = new GameController();
        gameController.prepareGameComponents();
        final Node tableauView = gameController.getTableauView().buildView();
        
        // layout the tableau
        tableauView.setLayoutX(16);
        tableauView.setLayoutY(scene.getHeight() - (scene.getHeight() - Card.cardHeight - 32));

        // layout the stock view
        final Node stockView = gameController.getStockView().buildView();
        stockView.setLayoutX(16);
        stockView.setLayoutY(scene.getHeight() - (scene.getHeight() - 16));

        
        root.getChildren().add(tableauView);
        root.getChildren().add(stockView);
        
        
     // Create the timer and score label
        timer = new Timer();
        scoreLabel = new Label("0/52");
        scoreLabel.setStyle("-fx-font-size: 30;");
        scoreLabel.setTranslateX(scene.getWidth() - 200);
        scoreLabel.setTranslateY(20);
        Pane root1 = (Pane) scene.getRoot();
        root1.getChildren().add(scoreLabel);
        
        // Set 0:00 when gameplay first opens
        emptyTimer = new Label("0:00");
        emptyTimer.setStyle("-fx-font-size: 30;");
        emptyTimer.setTranslateX(scene.getWidth() - 100);
        emptyTimer.setTranslateY(20);
        Pane root2 = (Pane) scene.getRoot();
        root2.getChildren().add(emptyTimer);
        
        // Create a new timer instance and schedule a task to update the timer label every second
        timerTask = new TimerTask() {
            @Override
            public void run() {
                secondsPassed++;
                Platform.runLater(() -> updateTimerLabel());
            }
        };
        timer.schedule(timerTask, 1000, 1000);
       
     // Temporary method of adding points
        Button addPoint = new Button("Add Point");
        addPoint.setPrefWidth(120);
        addPoint.setLayoutX(scene.getWidth() - addPoint.getPrefWidth() -110);
        addPoint.setLayoutY(70);
        addPoint.setOnAction(e -> {
            // Add 1 point
            score++;
            // Update the score label
            updateScoreLabel();

            // Check if score is equal to 52, and show GameOver popup if true
            if (score == 52) {
                // Get the elapsed time in minutes and seconds
                int minutes = secondsPassed / 60;
                int seconds = secondsPassed % 60;

                // Format the elapsed time as a string
                String timeString = String.format("%d:%02d", minutes, seconds);

                // Show the GameOver popup with the elapsed time
                GameOver.showGameOver(primaryStage, timeString);
            }
        });
        root.getChildren().add(addPoint);

 }

    private void updateTimerLabel() {
        int minutes = secondsPassed / 60;
        int seconds = secondsPassed % 60;
        String timeString = String.format("%d:%02d", minutes, seconds);
        Label timerLabel = new Label(timeString);
        timerLabel.setStyle("-fx-font-size: 30;");
        timerLabel.setTranslateX(scene.getWidth() - 100);
        timerLabel.setTranslateY(20);
        Pane root = (Pane) scene.getRoot();
        root.getChildren().remove(lastTimerLabel);
        root.getChildren().remove(emptyTimer);
        root.getChildren().add(timerLabel);
        lastTimerLabel = timerLabel;
    }

    // Method to update the score label
    private void updateScoreLabel() {
        String scoreString = String.format("%d/52", score);
        scoreLabel.setText(scoreString);
    }

    // Method to score a point
    public void scorePoint() {
        // Add 1 to the score
        score++;
        // Update the score label
        updateScoreLabel();
    }

    public void endGame() {
        // Stop the timer by canceling the TimerTask
        timerTask.cancel();

        // Code to end the game and timer here
    }

    
	    // Getter method for the scene
	    public Scene getScene() {
	        return scene;
    }

}
