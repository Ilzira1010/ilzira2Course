package ru.itis;

import ru.itis.handlers.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Server extends Thread {

    private final ConcurrentLinkedQueue<ClientHandler> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(9090);
            System.out.println("Server successfully deployd");
            while (true) {
                Socket socket = serverSocket.accept();
                ClientHandler handler = new ClientHandler(socket);
                System.out.println("new Connection");
                concurrentLinkedQueue.add(handler);
                if (concurrentLinkedQueue.size() == 2) {
                    List<ClientHandler> clientHandlers = new ArrayList<>();
                    clientHandlers.add(concurrentLinkedQueue.poll());
                    clientHandlers.add(concurrentLinkedQueue.poll());
                    GameServer gameServer = new GameServer(clientHandlers);
                    System.out.println("game start");
                    gameServer.start();
                }

            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
