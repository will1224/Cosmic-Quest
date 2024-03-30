import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ScoreBoard extends JDialog {
    private String playerUsername;
    public ScoreBoard(Frame parent, String playerName, int playerScore, String imagePath) {
        super(parent, "Player Score", true);
        getContentPane().setLayout(new BorderLayout()); // Use BorderLayout at the top level

        // Background panel with GridBagLayout for components
        JPanel backgroundPanel = new JPanel(new GridBagLayout()) {
            // Overriding paintComponent to draw the background image
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundIcon = new ImageIcon("images/gradient.png"); // Replace with your background image
                                                                                 // path
                Image backgroundImage = backgroundIcon.getImage();
                g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };
        add(backgroundPanel); // Add backgroundPanel to the dialog's content pane

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 15, 5, 5); // Adding some margin

        // Load and resize the profile picture
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image resizedImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel picLabel = new JLabel(new ImageIcon(resizedImage));

        // Player name and score labels
        JPanel textPanel = new JPanel(new GridBagLayout()); // Panel for text to keep it together
        textPanel.setBackground(new Color(245, 141, 110)); // This is an example of light gray color
        JLabel nameLabel = new JLabel("Name: " + playerName);
        JLabel scoreLabel = new JLabel("Score: " + playerScore);

        // Adding components to the text panel with constraints
        gbc.anchor = GridBagConstraints.WEST;
        textPanel.add(nameLabel, gbc);
        gbc.gridy = 1;
        textPanel.add(scoreLabel, gbc);

        // Adding picture label and text panel to the background panel
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 25, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        backgroundPanel.add(picLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        backgroundPanel.add(textPanel, gbc);

        setSize(400, 300);
        setLocationRelativeTo(parent);
    }

//    public PlayerScore getPlayerScore(String username){
//        try {
//            Accounts accounts = new Accounts();
////        JSONArray allUserData = accounts.getAccounts();
//            String jsonData = "[{\"password\":\"cosmic123\",\"progress\":[{\"currentLevel\":false,\"highscore\":1,\"levelNumber\":0,\"levelName\":\"The Sun\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":2,\"levelNumber\":1,\"levelName\":\"Mercury\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":2,\"levelName\":\"Venus\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":3,\"levelName\":\"Earth\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":4,\"levelName\":\"Mars\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":5,\"levelName\":\"Jupiter\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":6,\"levelName\":\"Saturn\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":7,\"levelName\":\"Uranus\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":8,\"levelName\":\"Neptune\",\"unlocked\":true},{\"currentLevel\":true,\"highscore\":0,\"levelNumber\":9,\"levelName\":\"Other Celestial Bodies\",\"unlocked\":true}],\"username\":\"education\"},{\"password\":\"cosmic456\",\"progress\":[{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":0,\"levelName\":\"The Sun\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":1,\"levelName\":\"Mercury\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":2,\"levelName\":\"Venus\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":3,\"levelName\":\"Earth\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":4,\"levelName\":\"Mars\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":5,\"levelName\":\"Jupiter\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":6,\"levelName\":\"Saturn\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":7,\"levelName\":\"Uranus\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":8,\"levelName\":\"Neptune\",\"unlocked\":true},{\"currentLevel\":true,\"highscore\":0,\"levelNumber\":9,\"levelName\":\"Other Celestial Bodies\",\"unlocked\":true}],\"username\":\"developer\"},{\"password\":\"abc123\",\"progress\":[{\"currentLevel\":true,\"highscore\":0,\"levelNumber\":0,\"levelName\":\"The Sun\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":1,\"levelName\":\"Mercury\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":2,\"levelName\":\"Venus\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":3,\"levelName\":\"Earth\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":4,\"levelName\":\"Mars\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":5,\"levelName\":\"Jupiter\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":6,\"levelName\":\"Saturn\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":7,\"levelName\":\"Uranus\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":8,\"levelName\":\"Neptune\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":9,\"levelName\":\"Other Celestial Bodies\",\"unlocked\":false}],\"username\":\"testing\"},{\"password\":\"\",\"progress\":[{\"currentLevel\":true,\"highscore\":0,\"levelNumber\":0,\"levelName\":\"The Sun\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":1,\"levelName\":\"Mercury\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":2,\"levelName\":\"Venus\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":3,\"levelName\":\"Earth\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":4,\"levelName\":\"Mars\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":5,\"levelName\":\"Jupiter\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":6,\"levelName\":\"Saturn\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":7,\"levelName\":\"Uranus\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":8,\"levelName\":\"Neptune\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":9,\"levelName\":\"Other Celestial Bodies\",\"unlocked\":false}],\"username\":\"\"}]";
//            JSONArray allUserData = null;
//            allUserData = (JSONArray) new JSONParser().parse(jsonData);
//            List<PlayerScore> userScores = new ArrayList<>();
//            int score = 0;
//            for (Object i : allUserData){
//                JSONObject userData = (JSONObject) i;
//                if(username.equals(userData.get("username").toString())) {
//                    JSONArray progressArray = (JSONArray) userData.get("progress");
//                    for (Object element : progressArray) {
//                        JSONObject levelData = (JSONObject) element;
//                        Object highScoreobj = levelData.get("highscore");
//                        if (highScoreobj != null) {
//                            try {
//                                score += Integer.parseInt(highScoreobj.toString());
//                            } catch (NumberFormatException e) {
//                                throw new RuntimeException(e);
//                            }
//                        }
//                    }
//                    break;
//                }
//            }
//            return new PlayerScore(username, score);
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public static void main(String[] args) {
        Accounts acc = new Accounts();
        PlayerScore user = acc.getPlayerScore("education");
        // Creating and showing this application's GUI.
        SwingUtilities.invokeLater(() -> {
            ScoreBoard dialog = new ScoreBoard(null, user.getPlayerName(), user.getScore(), "images/profile.jpg");
            dialog.setVisible(true);
            System.exit(0);
        });
    }
}

