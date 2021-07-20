package main.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.Main;
import main.Model.Database;
import main.Model.Player;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class welcomeController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private Label correctKeystrokesLabel;
    @FXML
    private Label wrongKeystrokesLabel;
    @FXML
    private Label wpmLabel;
    @FXML
    private Label accuracyLabel;


    @FXML
    public void playGame() throws IOException {
        Main m = new Main();
        m.changeScene("Views/game.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Player player = Database.getInstance().getHighscorePlayer();

        if(player == null) {
            player = new Player("Be the first!", 0, 0, 0, 0);
        }

        nameLabel.setText(player.getName());
        correctKeystrokesLabel.setText(String.valueOf(player.getCorrectKeystrokes()));
        wrongKeystrokesLabel.setText(String.valueOf(player.getWrongKeystrokes()));
        wpmLabel.setText(String.valueOf(player.getWpm()));
        accuracyLabel.setText(String.valueOf(player.getAccuracy()));

    }
}
