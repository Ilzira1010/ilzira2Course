package ru.itis.controller;

import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import ru.itis.Main;
import ru.itis.model.Client;
import ru.itis.window.WindowManager;
import ru.itis.protocol.Protocol;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class LobbyController implements Initializable {

    public ProgressIndicator pb;
    public static Client client;
    public static Socket socket;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pb.setProgress(ProgressBar.INDETERMINATE_PROGRESS);
        try {
            socket = new Socket(InetAddress.getByName("localhost"), 9090);
            client = new Client(socket.getInputStream(), socket.getOutputStream());
            waitStart();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private void waitStart() {
        new Thread(() -> {
            while(true) {
                try {
                    boolean command = Protocol.read(Protocol.START, socket.getInputStream(), socket.getOutputStream());
                    if (command) {
                        startGame();
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void startGame() {
        Platform.runLater(() -> WindowManager.renderWindow(Main.primaryStage, "Game", "game.fxml"));
    }
}
