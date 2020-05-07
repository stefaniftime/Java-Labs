package com.example.demo;

public class Game {
    String playerName;
    String content;
    String date;
    String result;

    public Game(String playerName, String content, String date, String result) {
        this.playerName = playerName;
        this.content = content;
        this.date = date;
        this.result = result;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
