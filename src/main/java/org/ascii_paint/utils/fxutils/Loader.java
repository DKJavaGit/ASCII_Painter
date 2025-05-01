package org.ascii_paint.utils.fxutils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import org.ascii_paint.Runner;

import java.io.IOException;
import java.util.HashMap;

/**
 * Class for loading, getting, removing and updating scenes in memory
 * */

public class Loader {
    private static final HashMap<String, Scene> scenes = new HashMap<>();

    /// Constructors /////////////////////////////////////////////////
    private Loader() {}
    /// Constructors /////////////////////////////////////////////////

    /// Methods //////////////////////////////////////////////////////
    public static void put(String sceneName, String path) {
        try {
            Scene scene = new Scene(new FXMLLoader(Runner.class.getResource(path)).load());
            scenes.put(sceneName, scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void remove(String sceneName) {
        scenes.remove(sceneName);
    }

    public static Scene get(String sceneName) {
        return scenes.get(sceneName);
    }

    public static void update(String sceneName, String path) {
        remove(sceneName);
        put(sceneName, path);
    }
    /// Methods //////////////////////////////////////////////////////
}