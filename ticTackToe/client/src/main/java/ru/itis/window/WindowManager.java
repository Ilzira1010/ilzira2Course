package ru.itis.window;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowManager {

    public static void renderWindow(Stage primaryStage, String title, String name) {
        FXMLLoader loader = new FXMLLoader();
        Parent root;
        try {
            root = loader.load(WindowManager.class.getResourceAsStream("/fxml/" + name));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        Scene scene = new Scene(root);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle(title);
        primaryStage.show();
    }
}
