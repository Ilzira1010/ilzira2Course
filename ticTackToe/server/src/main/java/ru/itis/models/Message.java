package ru.itis.models;

import java.io.Serializable;

public class Message implements Serializable {
    private Boolean move;
    private Integer win;

    public Message(Boolean move, Integer win) {
        this.move = move;
        this.win = win;
    }

    public Message() {
    }

    public Boolean getMove() {
        return move;
    }

    public Integer getWin() {
        return win;
    }
}
