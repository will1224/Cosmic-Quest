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
    private static final String DEFAULT_BACKGROUND_IMAGE_PATH = "images/background.jpg"; // Adjust as needed

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
        BackgroundPanel backgroundPanel = backgroundImage != null ? new BackgroundPanel(backgroundImage) : new BackgroundPanel();
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

    private List<PlayerScore> getScoresFromDatabase() throws ParseException {
        Accounts accounts = new Accounts();
        JSONArray allUserData = accounts.getAccounts();
//        String jsonData = "[{\"password\":\"cosmic123\",\"progress\":[{\"currentLevel\":false,\"highscore\":1,\"levelNumber\":0,\"levelName\":\"The Sun\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":2,\"levelNumber\":1,\"levelName\":\"Mercury\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":2,\"levelName\":\"Venus\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":3,\"levelName\":\"Earth\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":4,\"levelName\":\"Mars\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":5,\"levelName\":\"Jupiter\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":6,\"levelName\":\"Saturn\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":7,\"levelName\":\"Uranus\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":8,\"levelName\":\"Neptune\",\"unlocked\":true},{\"currentLevel\":true,\"highscore\":0,\"levelNumber\":9,\"levelName\":\"Other Celestial Bodies\",\"unlocked\":true}],\"username\":\"education\"},{\"password\":\"cosmic456\",\"progress\":[{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":0,\"levelName\":\"The Sun\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":1,\"levelName\":\"Mercury\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":2,\"levelName\":\"Venus\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":3,\"levelName\":\"Earth\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":4,\"levelName\":\"Mars\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":5,\"levelName\":\"Jupiter\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":6,\"levelName\":\"Saturn\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":7,\"levelName\":\"Uranus\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":8,\"levelName\":\"Neptune\",\"unlocked\":true},{\"currentLevel\":true,\"highscore\":0,\"levelNumber\":9,\"levelName\":\"Other Celestial Bodies\",\"unlocked\":true}],\"username\":\"developer\"},{\"password\":\"abc123\",\"progress\":[{\"currentLevel\":true,\"highscore\":0,\"levelNumber\":0,\"levelName\":\"The Sun\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":1,\"levelName\":\"Mercury\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":2,\"levelName\":\"Venus\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":3,\"levelName\":\"Earth\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":4,\"levelName\":\"Mars\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":5,\"levelName\":\"Jupiter\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":6,\"levelName\":\"Saturn\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":7,\"levelName\":\"Uranus\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":8,\"levelName\":\"Neptune\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":9,\"levelName\":\"Other Celestial Bodies\",\"unlocked\":false}],\"username\":\"testing\"},{\"password\":\"\",\"progress\":[{\"currentLevel\":true,\"highscore\":0,\"levelNumber\":0,\"levelName\":\"The Sun\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":1,\"levelName\":\"Mercury\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":2,\"levelName\":\"Venus\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":3,\"levelName\":\"Earth\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":4,\"levelName\":\"Mars\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":5,\"levelName\":\"Jupiter\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":6,\"levelName\":\"Saturn\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":7,\"levelName\":\"Uranus\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":8,\"levelName\":\"Neptune\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":9,\"levelName\":\"Other Celestial Bodies\",\"unlocked\":false}],\"username\":\"\"}]";
//        JSONArray allUserData = null;
//        allUserData = (JSONArray) new JSONParser().parse(jsonData);
        List<PlayerScore> userScores = new ArrayList<>();
        for (Object i : allUserData){
            int score = 0;
            JSONObject userData = (JSONObject) i;
            JSONArray progressArray = (JSONArray) userData.get("progress");
            for (Object element : progressArray){
                JSONObject levelData = (JSONObject) element;
                Object highScoreobj = levelData.get("highscore");
                if (highScoreobj != null){
                    try{
                        score += Integer.parseInt(highScoreobj.toString());
                    } catch (NumberFormatException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            userScores.add(new PlayerScore(userData.get("username").toString(),score));
        }
        return userScores;

//        return Arrays.asList(
//            new PlayerScore("Alice", 90),
//            new PlayerScore("Bob", 120),
//            new PlayerScore("Charlie", 85)
//        );
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

class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(Image image) {
        super();
        this.backgroundImage = image;
    }

    public BackgroundPanel() {
        super();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
}

class PlayerScore {
    private String playerName;
    private int score;

    public PlayerScore(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }
}
