package edu.westga.retirement;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


/**
 * Launcher class for the retirement calculator application
 * @author Kenji Okamoto
 * @version 20151120
 *
 */
public class Main extends Application {
    private static final String FXML_FILE = "retireAppScene.fxml";
    private static final String SCENE_TITLE = "Retirement Calculator";

    /**
     * Constructs a new instance of this Application object.
     *
     * @precondition	none
     * @postcondition	the object is ready to execute
     */
    public Main() {
        super();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource(FXML_FILE));

        primaryStage.setScene(new Scene(pane));
        primaryStage.setTitle(SCENE_TITLE);
        primaryStage.show();
    }

    /**
     * Launches the application.
     *
     * @param args
     *            not used
     */
    public static void main(String[] args) {
        launch(args);
    }
}
