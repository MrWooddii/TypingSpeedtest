package main.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.Main;
import main.Model.Database;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class saveHighscoreController implements Initializable {

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
        Database.getInstance().saveHighscore(enterName.getText(), 200, 111, 11, 100);

        Main m = new Main();
        m.changeScene("Views/welcomeScreen.fxml");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        wpmLabel.setText("" + 200);
        correctKeystrokesLabel.setText("" + 111);
        wrongKeystrokesLabel.setText("" + 11);
        accuracyLabel.setText("" + 100);
    }
}
