package src;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HighScore extends JFrame {
    private JTable scoresTable;
    private DefaultTableModel tableModel;

    public HighScore() throws ParseException {
        setTitle("High Scores");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Load the default background image directly from file path
        BackgroundPanel backgroundPanel = new BackgroundPanel(new ImageIcon("images/gradient.png").getImage());
        backgroundPanel.setLayout(new BorderLayout());
        setContentPane(backgroundPanel);

        // Initialize the scores table
        initScoresTable();

        // Get scores from database and populate the table
        List<PlayerScore> scores = getScoresFromDatabase();
        populateScoresTable(scores);
    }

    private void initScoresTable() {
        String[] columnNames = { "Name", "Score" };
        tableModel = new DefaultTableModel(columnNames, 0);
        scoresTable = new JTable(tableModel);

        // Make the table and its scroll pane transparent
        scoresTable.setOpaque(false); // Make the JTable transparent
        ((DefaultTableCellRenderer) scoresTable.getDefaultRenderer(Object.class)).setOpaque(false); // Make default cell
                                                                                                    // renderer
                                                                                                    // non-opaque

        // Customize header and cell appearance
        customizeHeader();
        customizeTable();

        JScrollPane scrollPane = new JScrollPane(scoresTable);
        scrollPane.setOpaque(false); // Make JScrollPane non-opaque
        scrollPane.getViewport().setOpaque(false); // Make the viewport non-opaque
        scrollPane.setBorder(null); // Optionally remove the border

        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    private void customizeHeader() {
        JTableHeader header = scoresTable.getTableHeader();
        header.setOpaque(false);
        header.setBackground(new Color(0, 0, 0, 122));
        header.setForeground(Color.BLACK); // Example customization

        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
                        column);
                label.setOpaque(false);
                label.setBackground(new Color(0, 0, 0, 122));
                label.setForeground(Color.BLACK); // Set the font color
                label.setFont(new Font("Serif", Font.BOLD, 16)); // Set the font and size
                return label;
            }
        });
    }

    private void customizeTable() {
        scoresTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (isSelected) {
                    component.setBackground(table.getSelectionBackground());
                } else {
                    component.setBackground(new Color(0, 0, 0, 0)); // Transparent background for cells
                }
                component.setForeground(Color.WHITE); // Example customization
                component.setFont(new Font("Arial", Font.PLAIN, 14)); // Example customization
                return component;
            }
        });
    }
    
    private void populateScoresTable(List<PlayerScore> scores) {
        // Clear existing table data
        tableModel.setRowCount(0);

        // Sort scores in descending order
        scores.sort(Comparator.comparingInt(PlayerScore::getScore).reversed());

        // Add scores to the table
        for (PlayerScore score : scores) {
            tableModel.addRow(new Object[] { score.getPlayerName(), score.getScore() });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new HighScore().setVisible(true);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
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

}

class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(Image backgroundImage2) {
        super();
        // Load the image
        ImageIcon icon = new ImageIcon(backgroundImage2);
        this.backgroundImage = icon.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the image as the background
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
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