package src;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HighScore extends JFrame {
    public HighScore() throws ParseException {
        setTitle("High Scores");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Load the default background image directly from file path
        ImageIcon backgroundImageIcon = new ImageIcon("images/gradient.png");
        Image backgroundImage = backgroundImageIcon.getImage();

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
        setVisible(true); 
    }

    private List<PlayerScore> getScoresFromDatabase() throws ParseException {
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
                frame.getScoresFromDatabase();
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