package src;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * The ScoreBoard class represents a dialog displaying player's score.
 * It includes the player's name, score, and an optional profile picture.
 * 
 * @author Sophia Tong
 * @author William Guo
 * @version 1.0
 */

public class ScoreBoard extends JDialog {

    /**
     * Constructs a ScoreBoard dialog with the specified parent frame, player name, and player score.
     * 
     * @param parent The parent frame of the dialog.
     * @param playerName The name of the player.
     * @param playerScore The score of the player.
     */    
    public ScoreBoard(Frame parent, String playerName, int playerScore) {
        super(parent, "Player Score", true);
        getContentPane().setLayout(new BorderLayout());

        JPanel backgroundPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    // Using getClass().getResource() to fetch the background image
                    URL backgroundURL = getClass().getResource("/images/gradient.png");
                    if (backgroundURL != null) {
                        BufferedImage backgroundImage = ImageIO.read(backgroundURL);
                        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
                    } else {
                        System.err.println("Unable to load background image.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        add(backgroundPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 15, 5, 5); // Adding some margin

        /**  Load and resize the profile picture */
        JLabel picLabel = new JLabel();
        try {
            URL profileURL = getClass().getResource("/images/profile.jpg");
            if (profileURL != null) {
                BufferedImage profileImage = ImageIO.read(profileURL);
                Image resizedImage = profileImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                picLabel.setIcon(new ImageIcon(resizedImage));
            } else {
                System.err.println("Unable to load profile image.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        /** Player name and score labels */
        JPanel textPanel = new JPanel(new GridBagLayout()); /** Panel for text to keep it together */
        textPanel.setBackground(new Color(245, 141, 110)); /** This is an example of light gray color */
        JLabel nameLabel = new JLabel("Name: " + playerName);
        JLabel scoreLabel = new JLabel("Score: " + playerScore);

        /** Adding components to the text panel with constraints */
        gbc.anchor = GridBagConstraints.WEST;
        textPanel.add(nameLabel, gbc);
        gbc.gridy = 1;
        textPanel.add(scoreLabel, gbc);

        /** Adding picture label and text panel to the background panel */
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


}

