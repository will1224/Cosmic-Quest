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

/**
 * The HighScore class extends JFrame and is responsible for displaying
 * a window with a table of high scores. It retrieves player scores from a
 * database, sorts them, and displays them in a JTable with customized styling.
 * The class demonstrates advanced usage of Swing components for building
 * user interfaces.
 * 
 * @author Sophia Tong
 * @author William Guo
 * @version 1.0
 */
public class HighScore extends JFrame {
    private JTable scoresTable;
    private DefaultTableModel tableModel;

    /**
     * Constructs a HighScore window. Initializes the window properties,
     * sets up the background panel, initializes and populates the scores table.
     * 
     * @throws ParseException if parsing JSON data fails.
     */
    public HighScore() throws ParseException {
        setTitle("High Scores");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        /** Load the default background image directly from file path */
        BackgroundPanel backgroundPanel = new BackgroundPanel(new ImageIcon("images/gradient.png").getImage());
        backgroundPanel.setLayout(new BorderLayout());
        setContentPane(backgroundPanel);

        /** Initialize the scores table */
        initScoresTable();

        /** Get scores from database and populate the table */
        List<PlayerScore> scores = getScoresFromDatabase();
        populateScoresTable(scores);
    }

    /**
     * Initializes the JTable for displaying scores including setting up the table
     * model,
     * making the table and its components transparent, and customizing its
     * appearance.
     */
    private void initScoresTable() {
        String[] columnNames = { "Username", "Score" };
        tableModel = new DefaultTableModel(columnNames, 0);
        scoresTable = new JTable(tableModel);

        /** Make the table and its scroll pane transparent */
        scoresTable.setOpaque(false); /** Make the JTable transparent */
        ((DefaultTableCellRenderer) scoresTable.getDefaultRenderer(Object.class)).setOpaque(false); /** Make default cell */
                                                                                                    /** renderer */
                                                                                                    /** non-opaque */

        /** Customize header and cell appearance */
        customizeHeader();
        customizeTable();

        JScrollPane scrollPane = new JScrollPane(scoresTable);
        scrollPane.setOpaque(false); /** Make JScrollPane non-opaque */
        scrollPane.getViewport().setOpaque(false); /** Make the viewport non-opaque */
        scrollPane.setBorder(null); /** Optionally remove the border */

        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Customizes the header of the scores table including setting transparency,
     * background color, text color, and font.
     */
    private void customizeHeader() {
        JTableHeader header = scoresTable.getTableHeader();
        header.setOpaque(false);
        header.setBackground(new Color(0, 0, 0, 122));
        header.setForeground(Color.BLACK);

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setHorizontalAlignment(JLabel.CENTER); /** Center text in header */
        headerRenderer.setOpaque(false);
        headerRenderer.setBackground(new Color(0, 0, 0, 122));
        headerRenderer.setForeground(Color.BLACK);
        headerRenderer.setFont(new Font("Arial", Font.BOLD, 20));

        header.setDefaultRenderer(headerRenderer);
    }

    /**
     * Customizes the cells of the scores table including text alignment, background
     * color,
     * text color, and font. It also applies a different background color for
     * selected rows.
     */
    private void customizeTable() {
        scoresTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
                        column);
                ((JLabel) component).setHorizontalAlignment(JLabel.CENTER); /** Center text in cells */

                if (isSelected) {
                    component.setBackground(table.getSelectionBackground());
                } else {
                    component.setBackground(new Color(0, 0, 0, 0)); /** Transparent background for cells */
                }
                component.setForeground(Color.WHITE);
                component.setFont(new Font("Arial", Font.PLAIN, 14));
                return component;
            }
        });
    }


    /**
     * Populates the scores table with data. It first clears any existing data,
     * sorts the incoming list of scores in descending order, and then adds the sorted scores to the table.
     * 
     * @param scores The list of player scores to populate the table with.
     */
    private void populateScoresTable(List<PlayerScore> scores) {
        /** Clear existing table data */
        tableModel.setRowCount(0);

        /** Sort scores in descending order
        scores.sort(Comparator.comparingInt(PlayerScore::getScore).reversed());

        /** Add scores to the table */
        for (PlayerScore score : scores) {
            tableModel.addRow(new Object[] { score.getPlayerName(), score.getScore() });
        }
    }


    /**
     * Retrieves and returns a list of player scores from the database. It processes JSON data
     * to extract player usernames and their scores, creating a list of PlayerScore objects.
     * 
     * @return List of PlayerScore objects representing the player scores.
     * @throws ParseException If parsing the JSON data fails.
     */
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
}