import javax.swing.*;
import java.awt.*;

public class ScoreBoard extends JDialog {
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

}
