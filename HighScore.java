import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class HighScore extends JFrame {
    private static final String DEFAULT_BACKGROUND_IMAGE_PATH = "images/background.jpg"; // Adjust as needed

    public HighScore() {
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

    private List<PlayerScore> getScoresFromDatabase() {
        return Arrays.asList(
            new PlayerScore("Alice", 90),
            new PlayerScore("Bob", 120),
            new PlayerScore("Charlie", 85)
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HighScore frame = new HighScore();
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
