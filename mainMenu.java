import javax.print.DocFlavor.URL;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainMenu implements ActionListener {
    private JFrame menu;
    private JTextField title;
    private JButton newGame;
    private JButton continueGame;
    private JButton selectLevel;
    private JButton scores;
    private JButton options;
    private JButton exitGame;

    public mainMenu() {
        menu = new JFrame("Cosmic Quest: Stellar Treasures");
        java.net.URL menuBackgroundURL = getClass().getResource("/images/menuBackground.jpg");
        if (menuBackgroundURL != null) {
            ImageIcon menuBackground = new ImageIcon(menuBackgroundURL);
            JLabel backgroundLabel = new JLabel(menuBackground);
            backgroundLabel.setLayout(new BorderLayout());
            menu.setContentPane(backgroundLabel);
            menu.setLayout(new GridBagLayout());
        } else {
            System.err.println("Unable to load background image.");
        }
        // Container Panel with BoxLayout for vertical stacking
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // Make panel transparent
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        title = new JTextField("Cosmic Quest: Stellar Treasures");
        title.setForeground(Color.WHITE);
        title.setFont(new Font(null, Font.BOLD, 24));
        title.setHorizontalAlignment(JTextField.CENTER); // Ensure title is centered
        title.setEditable(false); // Make it non-editable
        title.setBorder(null); // Remove border
        title.setOpaque(false); // Make background transparent

        newGame = createButton("New Game");
        continueGame = createButton("Continue");
        selectLevel = createButton("Select Level");
        scores = createButton("Scores");
        options = createButton("Options");
        exitGame = createButton("Exit");

        // Add components to the button panel
        buttonPanel.add(Box.createVerticalGlue()); // Add space at the top
        buttonPanel.add(title);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add space between title and buttons
        buttonPanel.add(newGame);
        buttonPanel.add(continueGame);
        buttonPanel.add(selectLevel);
        buttonPanel.add(scores);
        buttonPanel.add(options);
        buttonPanel.add(exitGame);
        buttonPanel.add(Box.createVerticalGlue()); // Add space at the bottom

        // Add button panel to the frame
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.NONE;
        menu.add(buttonPanel, gbc);

        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // menu.setSize(menuBackground.getIconWidth(), menuBackground.getIconHeight());
        menu.setVisible(true);
        menu.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button
        button.setPreferredSize(new Dimension(200, 60)); // Slightly adjust if needed
        button.setMaximumSize(new Dimension(200, 60)); // Keep them uniform
        button.setFont(new Font("Arial", Font.BOLD, 18)); // Set a larger font size for button text
        button.addActionListener(this); // Register the ActionListener
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == selectLevel) {
            menu.dispose(); // Close the main menu
            new levelMenu(); // Open the level menu
        } else if (e.getSource() == exitGame) {
            menu.dispose(); // Close the application
        } else if (e.getSource() == newGame) {
            menu.dispose(); // Example: Close the main menu and start a new game
            // new GameLogic().startNewGame(); // Hypothetical method to start a new game
        }
        // Handle other button actions...
    }


}

