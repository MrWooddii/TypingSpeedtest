package main.Model;

import java.sql.*;

public class Database {

    public static final String DB_NAME = "Highscore.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:" + DB_NAME;

    public static final String TABLE_PLAYER = "player";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_WPM = "wpm";
    public static final String COLUMN_CORRECT_KEYSTROKES = "correctKeystrokes";
    public static final String COLUMN_WRONG_KEYSTROKES = "wrongKeystrokes";
    public static final String COLUMN_ACCURACY = "accuracy";

    private Connection conn;
    private static Database instance = new Database();

    private Database() {};

    public Player getHighscorePlayer() {
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + TABLE_PLAYER + " ORDER BY " + COLUMN_WPM + " DESC, "
                + COLUMN_CORRECT_KEYSTROKES + " DESC, " + COLUMN_WRONG_KEYSTROKES + " DESC, " + COLUMN_ACCURACY + " DESC LIMIT 1")) {
            ResultSet result = ps.executeQuery();

            Player player = new Player();
            player.setID(result.getInt(COLUMN_ID));
            player.setName(result.getString(COLUMN_NAME));
            player.setWpm(result.getInt(COLUMN_WPM));
            player.setCorrectKeystrokes(result.getInt(COLUMN_CORRECT_KEYSTROKES));
            player.setWrongKeystrokes(result.getInt(COLUMN_WRONG_KEYSTROKES));
            player.setAccuracy(result.getInt(COLUMN_ACCURACY));

            result.close();
            ps.close();

            return player;

        } catch (SQLException e) {
            System.out.println("Could not get welcome Highscore: " + e.getMessage());
            return null;
        }
    }

    public void saveHighscore(String name, int wpm, int correctKeystrokes, int wrongKeystrokes, int accuracy) {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO " + TABLE_PLAYER + " (" + COLUMN_NAME + ", " + COLUMN_WPM + ", " + COLUMN_CORRECT_KEYSTROKES + ", " + COLUMN_WRONG_KEYSTROKES + ", " + COLUMN_ACCURACY +
                ") VALUES (?, ?, ?, ?, ?)")) {
            ps.setString(1, name);
            ps.setInt(2, wpm);
            ps.setInt(3, correctKeystrokes);
            ps.setInt(4, wrongKeystrokes);
            ps.setInt(5, accuracy);
            ps.execute();

        } catch (SQLException e) {
            System.out.println("Could not save Highscore: " + e.getMessage());
        }
    }

    public static Database getInstance() {
        return instance;
    }

    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            return true;
        } catch (SQLException e) {
            System.out.println("Could not connect to database (method open(): " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            if(conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Could not close connection: " + e.getMessage());
        }
    }
}
