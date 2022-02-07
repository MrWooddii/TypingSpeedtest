package main.Controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import main.Main;

import java.io.*;
import java.net.URL;
import java.util.*;

public class GameController implements Initializable {

    private final int TIME_RESTRICTION = 60;

    private final List<String> wordList = new ArrayList<>();

    private boolean gameStarted = false;

    private int correctKeystrokes = 0;

    private int wrongKeystrokes = 0;

    private double accuracy = 100;

    private int time;

    private Timer timer;

    @FXML
    private Label firstWord;
    @FXML
    private Label secondWord;
    @FXML
    private TextField enteredWord;

    @FXML
    private Label seconds;
    @FXML
    private Label correctKeystrokesLabel;
    @FXML
    private Label wrongKeystrokesLabel;
    @FXML
    private Label accuracyLabel;
    @FXML
    private Label wpmLabel;

    @FXML
    private Button playAgainButton;
    @FXML
    private Button highscoreButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            File myFile = new File(this.getClass().getResource("/main/Model/words.txt").getFile());
            Scanner scanner = new Scanner(myFile);

            while(scanner.hasNextLine()) {
                String word = scanner.nextLine();
                wordList.add(word);
            }

        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException: " + e.getMessage());
        }

        setWords();
        time = TIME_RESTRICTION;
        seconds.setText("" + time);
        accuracyLabel.setText("" + accuracy);
        wrongKeystrokesLabel.setText("" + wrongKeystrokes);
        correctKeystrokesLabel.setText("" + correctKeystrokes);

        enteredWord.requestFocus();
    }

    //startet, sobald ein Buchstabe in "enteredWord" eingegeben wird
    @FXML
    public void startGame(KeyEvent keyEvent) throws InterruptedException{

        if(gameStarted) {

            if(keyEvent.getCode().equals(KeyCode.BACK_SPACE)) {
                return;
            }

            if(keyEvent.getCode().equals(KeyCode.SPACE)) {

                if(enteredWord.getText().trim().length() <= 0) return;

                checkWord(enteredWord.getText().trim(), firstWord.getText());

                //if no wrong keystrokes are registered, accuracy is 100 %
                accuracy = wrongKeystrokes == 0 ? 100 : (double) correctKeystrokes / (wrongKeystrokes + correctKeystrokes) * 100;

                accuracyLabel.setText("" + (int) accuracy);

                setWords();
                enteredWord.clear();
            }

            return;
        }

        gameStarted = true;

        timer =  new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if(time >= 0) {
                    time--;

                    Platform.runLater(new Runnable() {
                       @Override
                       public void run() {
                           seconds.setText("" + time);
                       }
                    });
                }

                if(time <= 0) {
                    System.out.println("Time is up!");
                    enteredWord.clear();
                    enteredWord.setDisable(true);
                    highscoreButton.setVisible(true);
                    timer.cancel();
                }
            }
        };

        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    private void checkWord(String enteredWord, String correctWord) {

        //Shift und Leertaste zählen als zusätzlicher Tastenanschlag
        int length = Character.isUpperCase(enteredWord.charAt(0)) ? enteredWord.length() + 2 : enteredWord.length() + 1;

        if(enteredWord.equals(correctWord)) {
            correctKeystrokes += length;
            correctKeystrokesLabel.setText("" + correctKeystrokes);
            //Ein Wort ist definiert als "5 Tastenanschläge = 1 Wort"
            wpmLabel.setText("" + (correctKeystrokes / 5));
        } else {
            wrongKeystrokes += length;
            wrongKeystrokesLabel.setText("" + wrongKeystrokes);
        }
    }

    @FXML
    private void changeToHighscoreScreen() throws IOException{

        Main m = new Main();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/Views/saveHighscoreScreen.fxml"));
        Parent root = loader.load();

        SaveHighscoreController controller = loader.getController();
        controller.setData(Integer.parseInt(wpmLabel.getText()), correctKeystrokes, wrongKeystrokes, (int) accuracy);

        m.getStage().setScene(new Scene(root));

    }

    @FXML
    private void playAgain(MouseEvent event) {

        time = TIME_RESTRICTION;
        seconds.setText("" + time);
        correctKeystrokes = 0;
        correctKeystrokesLabel.setText("" + correctKeystrokes);
        wrongKeystrokes = 0;
        wrongKeystrokesLabel.setText("" + wrongKeystrokes);
        accuracyLabel.setText("" + 100);
        wpmLabel.setText("" + 0);
        enteredWord.setDisable(false);
        highscoreButton.setVisible(false);
        setWords();
        timer.cancel();
        gameStarted = false;
        enteredWord.requestFocus();
    }

    private void setWords() {

        if(gameStarted) {
            firstWord.setText(secondWord.getText());
        } else {
            firstWord.setText(wordList.get((int) (Math.random() * wordList.size())));
        }

        //es dürfen nicht zweimal die selben Wörter hintereinander ausgewählt werden
        do {
            secondWord.setText(wordList.get((int) (Math.random() * wordList.size())));
        } while((firstWord.getText().equals(secondWord.getText())));
    }
}
