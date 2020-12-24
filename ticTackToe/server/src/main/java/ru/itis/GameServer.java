package ru.itis;

import ru.itis.handlers.ClientHandler;
import ru.itis.models.Message;

import java.util.List;

public class GameServer extends Thread {

    private static List<ClientHandler> clientHandlers;
    private static int[][] matrix = new int[3][3];
    private static int playerMove = 0;
    private static int playerNotMove = 1;

    public GameServer(List<ClientHandler> clientHandlers) {
        this.clientHandlers = clientHandlers;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = -1;
            }
        }
    }

    public static void checkStep(String token, ClientHandler clientHandler) {
        String[] tokens = token.split("");
        int index = clientHandler.equals(clientHandlers.get(0)) ? 0 : 1;
        matrix[Integer.parseInt(tokens[0])][Integer.parseInt(tokens[1])] = index;
    }

    private static int check() {
        boolean flag = true;
        for (int[] value : matrix) {
            for (int i : value) {
                if (i == -1) {
                    flag = false;
                    break;
                }
            }
            if (!flag) break;
        }

        if (flag) {
            return -1;
        }

        for (int[] ints : matrix) {
            if (ints[0] == 0 && ints[1] == 0 && ints[2] == 0) {
                return 1;
            } else if (ints[0] == 1 && ints[1] == 1 && ints[2] == 1) {
                return 1;
            }
        }
        for (int j = 0; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0 && matrix[1][j] == 0 && matrix[2][j] == 0) {
                return 1;
            } else if (matrix[0][j] == 1 && matrix[1][j] == 1 && matrix[2][j] == 1) {
                return 1;
            }
        }
        if (matrix[0][0] == 0 && matrix[1][1] == 0 && matrix[2][2] == 0) {
            return  1;
        } else if (matrix[0][0] == 1 && matrix[1][1] == 1 && matrix[2][2] == 1) {
            return  1;
        }
        if (matrix[0][2] == 0 && matrix[1][1] == 0 && matrix[2][0] == 0) {
            return 1;
        } else if (matrix[0][2] == 1 && matrix[1][1] == 1 && matrix[2][0] == 1) {
            return 1;
        }
        return 0;
    }

    public static void nextRound(String move) {
        clientHandlers.forEach(c -> c.nextRound(move));
        if (check() != 0) {
            end(check());
        } else {
            next();
        }
    }

    private static void end(int check) {
        if (check != -1) {
            clientHandlers.get(playerNotMove).sendChoose(new Message(null, 0));
            clientHandlers.get(playerMove).sendChoose(new Message(null, 1));
        } else {
            clientHandlers.get(playerNotMove).sendChoose(new Message(null, -1));
            clientHandlers.get(playerMove).sendChoose(new Message(null, -1));
        }
    }

    @Override
    public void run() {
        for (ClientHandler clientHandler : clientHandlers) {
            clientHandler.sendStart();
        }
        next();
    }

    private static void next() {
        clientHandlers.get(playerMove).sendChoose(new Message(true, null));
        clientHandlers.get(playerNotMove).sendChoose(new Message(false, null));
        clientHandlers.get(playerNotMove).waitChoose();

        int temp = playerMove;
        playerMove = playerNotMove;
        playerNotMove = temp;
    }
}
