package ru.itis.handlers;

import ru.itis.GameServer;
import ru.itis.protocol.Protocol;
import ru.itis.models.Message;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {

    private final DataInputStream inputStream;
    private final DataOutputStream outputStream;

    public ClientHandler(Socket clientSocket) {
        try {
            this.inputStream = new DataInputStream(clientSocket.getInputStream());
            this.outputStream = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void sendStart() {
        Protocol.write(Protocol.START, outputStream);
    }

    public void sendChoose(Message flag) {
        Protocol.write(Protocol.CHOOSE, flag, outputStream);
    }

    public void waitChoose() {
        new Thread(() -> {
            while (true) {
                String move = (String) Protocol.read(Protocol.MOVE, inputStream, outputStream, String.class);
                if (move != null) {
                    GameServer.checkStep(move, this);
                    GameServer.nextRound(move);
                    break;
                }
            }
        }).start();
    }

    public void nextRound(String move) {
        Protocol.write(Protocol.NEXT_ROUND, move, outputStream);
    }
}
