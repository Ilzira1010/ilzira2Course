package ru.itis.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import ru.itis.models.Message;
import ru.itis.protocol.Protocol;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    public Button leftTopButton;
    public Button leftMiddleButton;
    public Label headerLabel;
    public Button leftBottomButton;
    public Button centerMiddleButton;
    public Button centerTopButton;
    public Button centerBottomButton;
    public Button rightTopButton;
    public Button rightMiddleButton;
    public Button rightBottomButton;
    private final List<Button> buttonList = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        buttonList.add(leftTopButton);
        buttonList.add(centerTopButton);
        buttonList.add(rightTopButton);
        buttonList.add(leftMiddleButton);
        buttonList.add(leftBottomButton);
        buttonList.add(centerBottomButton);
        buttonList.add(centerMiddleButton);
        buttonList.add(rightBottomButton);
        buttonList.add(rightMiddleButton);

        waitChoose();
    }

    public void move(boolean bool) {
        for (Button button : buttonList) {
            Platform.runLater(() -> button.setDisable(bool));
        }
        drawStep();
    }

    public void step(ActionEvent event) {
        Button button = buttonList.stream().filter(b -> b.equals(event.getSource())).findFirst().get();
        button.setDisable(true);
        button.setText("X");
        buttonList.removeIf(b -> b.equals(event.getSource()));
        buttonList.forEach(b -> b.setDisable(true));
        String choose = getChooseFromButton(button);
        sendChoose(choose);
    }

    private void sendChoose(String choose) {
        Protocol.write(Protocol.MOVE, choose, LobbyController.client.getOutputStream());
    }



    private void drawStep() {
        new Thread(() -> {
            while (true) {
                String next = (String) Protocol.read(Protocol.NEXT_ROUND, LobbyController.client.getInputStream(), LobbyController.client.getOutputStream(), String.class);
                if (next != null) {
                    draw(next);
                    break;
                }
            }
        }).start();
    }

    private void draw(String token) {
        Button button = getButtonFromChoose(token);
        if (button != null && button.getText().length() == 0) {
            button.setDisable(true);
            buttonList.removeIf(b -> b.equals(button));
            Platform.runLater(() -> button.setText("O"));
        }
        waitChoose();
    }

    private void waitChoose() {
        new Thread(() -> {
            while (true) {
                try {
                    Message choose = (Message) Protocol.read(Protocol.CHOOSE, LobbyController.socket.getInputStream(), LobbyController.socket.getOutputStream(), Message.class);
                    if (choose != null) {
                        if (choose.getMove() != null) {
                            move(choose.getMove());
                        } else if (choose.getWin() != null) {
                            end(choose.getWin());
                        }
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void end(Integer next) {
        String text = "";
        if (next == 1){
            text = "Вы Выиграли!";
        } else if (next == 0) {
            text = "Вы проиграли";
        } else {
            text = "Ничья";
        }
        String finalText = text;
        Platform.runLater(() -> headerLabel.setText(finalText));
    }

    private String getChooseFromButton(Button button) {
        String choose = "";
        if (button.equals(leftTopButton)) {
            choose = "00";
        } else if (button.equals(centerTopButton)) {
            choose = "01";
        } else if (button.equals(rightTopButton)) {
            choose = "02";
        } else if (button.equals(leftMiddleButton)) {
            choose = "10";
        } else if (button.equals(centerMiddleButton)) {
            choose = "11";
        } else if (button.equals(rightMiddleButton)) {
            choose = "12";
        } else if (button.equals(leftBottomButton)) {
            choose = "20";
        } else if (button.equals(centerBottomButton)) {
            choose = "21";
        } else {
            choose = "22";
        }
        return choose;
    }

    private Button getButtonFromChoose(String choose) {
        switch (choose) {
            case "00":
                return leftTopButton;
            case "01":
                return centerTopButton;
            case "02":
                return rightTopButton;
            case "10":
                return leftMiddleButton;
            case "11":
                return centerMiddleButton;
            case "12":
                return rightMiddleButton;
            case "20":
                return leftBottomButton;
            case "21":
                return centerBottomButton;
            case "22":
                return rightBottomButton;
            default:
                return null;
        }
    }
}
