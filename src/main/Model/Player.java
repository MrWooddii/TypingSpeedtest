package main.Model;

public class Player {

    private int ID;
    private String name;
    private int wpm;
    private int correctKeystrokes;
    private int wrongKeystrokes;
    private int accuracy;

    public Player() {}

    public Player(String name, int wpm, int correctKeystrokes, int wrongKeystrokes, int accuracy) {
        this.ID = 0;
        this.name = name;
        this.wpm = wpm;
        this.correctKeystrokes = correctKeystrokes;
        this.wrongKeystrokes = wrongKeystrokes;
        this.accuracy = accuracy;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWpm() {
        return wpm;
    }

    public void setWpm(int wpm) {
        this.wpm = wpm;
    }

    public int getCorrectKeystrokes() {
        return correctKeystrokes;
    }

    public void setCorrectKeystrokes(int correctKeystrokes) {
        this.correctKeystrokes = correctKeystrokes;
    }

    public int getWrongKeystrokes() {
        return wrongKeystrokes;
    }

    public void setWrongKeystrokes(int wrongKeystrokes) {
        this.wrongKeystrokes = wrongKeystrokes;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }
}
