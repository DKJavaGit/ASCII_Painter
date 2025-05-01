package org.ascii_paint.utils.fxutils;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Class for moving between scenes
 * */

public class Transport {
    private static final int DEFAULT_FADE_DURATION = 300;
    private static final int MIN_OPACITY = 0;
    private static final int MAX_OPACITY = 1;

    /// Constructors ////////////////////////////////////////////////////
    private Transport() {}
    /// Constructors ////////////////////////////////////////////////////

    /// Methods /////////////////////////////////////////////////////////
    // Method for opening scene
    public static void openScene(Stage stage, String path,
                                 boolean update, String title,
                                 boolean resizable, boolean enforceMinSize) {
        loadScene(path, update);

        Scene scene = Loader.get(path);
        scene.getRoot().setOpacity(0);

        configureStage(stage, scene, title, resizable, enforceMinSize);
        stage.show();

        applyFadeAnimation(scene);
    }

    private static void loadScene(String path, boolean update) {
        if (Loader.get(path) == null)
            Loader.put(path, path);
        else if (update) {
            Loader.update(path, path);
        }
    }

    private static void configureStage (Stage stage, Scene scene,
                                       String title, boolean resizable,
                                       boolean enforceMinSize) {
        stage.setScene(scene);
        stage.setTitle(title);
        stage.setResizable(resizable);

        if (resizable && enforceMinSize) {
            stage.setOnShown(e -> {
                stage.setMinWidth(stage.getWidth());
                stage.setMinHeight(stage.getHeight());
            });
        }
    }

    private static void applyFadeAnimation(Scene scene) {
        FadeTransition fadeIn = new FadeTransition(
                Duration.millis(DEFAULT_FADE_DURATION),
                scene.getRoot()
        );
        fadeIn.setFromValue(MIN_OPACITY);
        fadeIn.setToValue(MAX_OPACITY);

        runOnFXThread(fadeIn::play);
    }

    private static void runOnFXThread(Runnable action) {
        if (Platform.isFxApplicationThread()) {
            action.run();
        } else {
            Platform.runLater(action);
        }
    }

    public static void openInNewStage (String path,
                                       boolean update, String title,
                                       boolean resizable, boolean enforceMinSize) {
        Stage stage = new Stage();
        openScene(stage, path, update, title, resizable, enforceMinSize);
    }
    /// Methods /////////////////////////////////////////////////////////
}