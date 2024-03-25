import javax.swing.*;
import java.awt.*;

public class ScoreBoard extends JDialog {
    public ScoreBoard(Frame parent, String playerName, int playerScore, String imagePath) {
        super(parent, "Player Score", true);
        //setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Set layout for dialog to GridBagLayout for flexibility
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // This helps to center the components in the page
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;

        // Load the original profile picture
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image originalImage = originalIcon.getImage();
        
        // Resize the image to fit a standard profile picture size (e.g., 100x100 pixels)
        Image resizedImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon profilePic = new ImageIcon(resizedImage);
        
        JLabel picLabel = new JLabel(profilePic);

        // Player name and score labels
        JLabel nameLabel = new JLabel("Name: " + playerName);
        JLabel scoreLabel = new JLabel("Score: " + playerScore);

        // Adding some space between the name and score
        scoreLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // Top, left, bottom, right

        // Adding components to the dialog with constraints
        add(picLabel, gbc); // Adds the resized profile picture to the center
        add(nameLabel, gbc); // Adds the name label below the profile picture
        add(scoreLabel, gbc); // Adds the score label below the name label with added space

        // Set window size, location, and make it visible
        setSize(400, 400);
        setLocationRelativeTo(parent); // This will center the dialog in the parent frame
    }

    public static void main(String[] args) {
        // Creating and showing this application's GUI.
        SwingUtilities.invokeLater(() -> {
            ScoreBoard dialog = new ScoreBoard(null, "Player1", 100, "images/jen.jpeg");
            dialog.setVisible(true);
            System.exit(0);
        });
    }
}
