package main.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.Main;
import main.Model.Database;

import java.io.IOException;

public class SaveHighscoreController{

    private int wpm;
    private int correctKeystrokes;
    private int wrongKeystrokes;
    private int accuracy;

    @FXML
    private TextField enterName;
    @FXML
    private Label wpmLabel;
    @FXML
    private Label correctKeystrokesLabel;
    @FXML
    private Label wrongKeystrokesLabel;
    @FXML
    private Label accuracyLabel;
    @FXML
    private Button saveButton;

    @FXML
    private void saveHighscore(ActionEvent event) throws IOException {
        Database.getInstance().saveHighscore(enterName.getText(), wpm, correctKeystrokes, wrongKeystrokes, accuracy);

        Main m = new Main();
        m.changeScene("Views/welcomeScreen.fxml");
    }

    public void setData(int wpm, int correctKeystrokes, int wrongKeystrokes, int accuracy) {
        this.wpm = wpm;
        this.correctKeystrokes = correctKeystrokes;
        this.wrongKeystrokes = wrongKeystrokes;
        this.accuracy = accuracy;

        wpmLabel.setText("" + wpm);
        correctKeystrokesLabel.setText("" + correctKeystrokes);
        wrongKeystrokesLabel.setText("" + wrongKeystrokes);
        accuracyLabel.setText("" + accuracy);

    }
}
