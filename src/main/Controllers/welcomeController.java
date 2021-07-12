package main.Controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import main.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class welcomeController {

    @FXML
    private Text keystrokes;
    @FXML
    private Text wpm;
    @FXML
    private Text invalid;
    @FXML
    private Text name;

    @FXML
    public void playGame() throws IOException {
        Main m = new Main();
        m.changeScene("Views/game.fxml");
    }

}
