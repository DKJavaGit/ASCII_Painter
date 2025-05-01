package org.ascii_paint;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.ascii_paint.utils.fxutils.Transport;

import java.io.IOException;

public class Runner extends Application {
    @Override
    public void start(Stage stage) {
        Transport.openScene(stage, "scenes/choice-scene.fxml", false, "ASCII-Paint", true, true);
    }

    public static void main(String[] args) {
        launch();
    }
}