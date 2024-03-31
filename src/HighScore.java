package src;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class HighScore extends JFrame {
    private static final String DEFAULT_BACKGROUND_IMAGE_PATH = "images/gradient.png"; // Adjust as needed

    public HighScore() throws ParseException {
        setTitle("High Scores");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Attempt to load the default background image
        URL imageUrl = HighScore.class.getResource(DEFAULT_BACKGROUND_IMAGE_PATH);
        Image backgroundImage = null;
        if (imageUrl != null) {
            backgroundImage = new ImageIcon(imageUrl).getImage();
        } else {
            System.err.println("Background image not found: " + DEFAULT_BACKGROUND_IMAGE_PATH);
        }

        // Set the background panel with the background image
        BackgroundPanel backgroundPanel = new BackgroundPanel(backgroundImage);
        backgroundPanel.setLayout(new BorderLayout());
        setContentPane(backgroundPanel);

        List<PlayerScore> scores = getScoresFromDatabase();
        scores.sort(Comparator.comparingInt(PlayerScore::getScore).reversed());

        String[] columnNames = {"Player", "Score"};
        Object[][] data = new Object[scores.size()][2];
        for (int i = 0; i < scores.size(); i++) {
            data[i][0] = scores.get(i).getPlayerName();
            data[i][1] = scores.get(i).getScore();
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        backgroundPanel.add(scrollPane, BorderLayout.CENTER);
    }

    public List<PlayerScore> getScoresFromDatabase() throws ParseException {
        Accounts accounts = new Accounts();
        JSONArray allUserData = accounts.getAccounts();
        List<PlayerScore> userScores = new ArrayList<>();
        for (Object i : allUserData) {
            int score = 0;
            JSONObject userData = (JSONObject) i;
            JSONArray progressArray = (JSONArray) userData.get("progress");
            for (Object element : progressArray) {
                JSONObject levelData = (JSONObject) element;
                Object highScoreobj = levelData.get("highscore");
                if (highScoreobj != null) {
                    try {
                        score += Integer.parseInt(highScoreobj.toString());
                    } catch (NumberFormatException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            userScores.add(new PlayerScore(userData.get("username").toString(), score));
        }
        return userScores;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HighScore frame = null;
            try {
                frame = new HighScore();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            frame.setVisible(true);
        });
    }
}

