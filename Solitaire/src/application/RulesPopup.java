package application;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;


class RulesPopup {

    public static void showPopup(Stage owner) {
        // Create a new stage for the rules pop-up
        Stage rulesStage = new Stage();
        rulesStage.initModality(Modality.APPLICATION_MODAL);
        rulesStage.initOwner(owner);
        rulesStage.setTitle("Rules");
        // Create an image view with the game rules image
        ImageView rulesImage = new ImageView(new Image(GameMenu.class.getResource("/images/game_rules.png").toExternalForm()));
        rulesImage.setFitWidth(600);
        rulesImage.setPreserveRatio(true);

        // Create a Pane to hold the ImageView
        Pane rulesPane = new Pane(rulesImage);
        rulesPane.setPrefSize(700, 725);

        // Create a Scene for the Pane
        Scene rulesScene = new Scene(rulesPane);

        // Set the scene and show the stage
        rulesStage.setScene(rulesScene);
        rulesStage.show();
    }
}

