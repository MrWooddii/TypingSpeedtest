package main.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class gameController implements Initializable {

    private List<String> wordList = new ArrayList<>();

    @FXML
    private Label firstWord;
    @FXML
    private Label secondWord;
    @FXML
    private TextField enteredWord;

    @FXML
    private Label seconds;
    @FXML
    private Label keystrokes;
    @FXML
    private Label accuracy;

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

        Collections.shuffle(wordList);

        int index = 0;

        firstWord.setText(wordList.get(index));
        if(index + 1 < wordList.size()) {
            secondWord.setText(wordList.get(index + 1));
        } else {
            secondWord.setText("");
        }

        enteredWord.requestFocus();

        //TODO: löschen, nur zum Test
        for(String word : wordList) {
            System.out.println(word);
        }
    }

    //startet, sobald ein Buchstabe in "enteredWord" eingegeben wird
    //TODO: play button abändern, Stand jetzt startet er auch als erstes das Game
    @FXML
    public void startGame(KeyEvent keyEvent) throws InterruptedException{

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        //executor.scheduleAtFixedRate();

        /*

        while (Integer.parseInt(seconds.getText()) > 0) {
            firstWord.setText(wordList.get(index));
            if(index + 1 < wordList.size()) {
                secondWord.setText(wordList.get(index + 1));
            } else {
                secondWord.setText("");
            }

        }*/
    }

}
