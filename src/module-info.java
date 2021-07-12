module TypingSpeedtest {

    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;

    opens main;
    opens images;
    opens main.Controllers;
}