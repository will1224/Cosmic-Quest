package src;

import javax.swing.*;

import org.json.simple.parser.ParseException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

/**
 * Constructs the main menu for our game facilitating navigation
 * through various game options.
 * It allows users to start new games, continue existing ones, select levels,
 * view scores, adjust settings,
 * exit the game, and access external software tools. The class handles UI
 * creation and event management,
 * extending JFrame for the main window and implementing ActionListener for
 * button interactions.
 * 
 * @author Sophia Tong
 * @version 1.0
 */
public class MainMenuSoftware implements ActionListener {
    private JFrame menu;
    private JTextField title;
    private JButton newGame;
    private JButton continueGame;
    private JButton selectLevel;
    private JButton scores;
    private JButton options;
    private JButton exitGame;
    private JButton software;
    private Accounts accounts;

    /**
     * Sets up the main menu interface with buttons and other components, preparing
     * it for user interaction.
     */
    public MainMenuSoftware(Accounts accounts) {
        this.accounts = accounts;
        menu = new JFrame("Cosmic Quest: Stellar Treasures");
        java.net.URL menuBackgroundURL = getClass().getResource("/images/menu.png");
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

        title = new JTextField("");
        title.setForeground(Color.WHITE);
        title.setFont(new Font(null, Font.BOLD, 24));
        title.setHorizontalAlignment(JTextField.CENTER); // Ensure title is centered
        title.setEditable(false); // Make it non-editable
        title.setBorder(null); // Remove border
        title.setOpaque(false); // Make background transparent

        newGame = createImageButton("images/newgameBTN.png");
        continueGame = createImageButton("images/continueBTN.png");
        selectLevel = createImageButton("images/levelBTN.png");
        scores = createImageButton("images/scoreBTN.png");
        options = createImageButton("images/optionBTN.png");
        software = createImageButton("images/tools.png");
        exitGame = createImageButton("images/exitBTN.png");

        // Add components to the button panel
        buttonPanel.add(Box.createVerticalGlue()); // Add space at the top
        buttonPanel.add(title);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add space between title and buttons
        buttonPanel.add(Box.createVerticalStrut(30)); // Space underneath Button
        buttonPanel.add(newGame);
        buttonPanel.add(Box.createVerticalStrut(20)); // Space underneath
        buttonPanel.add(continueGame);
        buttonPanel.add(Box.createVerticalStrut(20)); // Space underneath
        buttonPanel.add(selectLevel);
        buttonPanel.add(Box.createVerticalStrut(20)); // Space underneath
        buttonPanel.add(scores);
        buttonPanel.add(Box.createVerticalStrut(20)); // Space underneath
        buttonPanel.add(options);
        buttonPanel.add(Box.createVerticalStrut(20)); // Space underneath
        buttonPanel.add(software);
        buttonPanel.add(Box.createVerticalStrut(20)); // Space underneath
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

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        menu.setSize(screenSize.width, screenSize.height);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setVisible(true);
    }

    /**
     * Generates a button featuring an image, making the interface more engaging and visually appealing.
     * Each button is linked to specific functionality, enhancing the overall user experience.
     * 
     * @param imagePath The path to the image file for the button icon.
     * @return A visually customized JButton ready for user interaction.
     */
    private JButton createImageButton(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        JButton button = new JButton(icon);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setMaximumSize(new Dimension(icon.getIconWidth() + 20, icon.getIconHeight() + 20));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(this);
        return button;
    }

    /**
     * Responds to user interactions with the menu's buttons, triggering actions like starting a game or opening external tools.
     * This method ensures that the software reacts appropriately to each button press.
     * 
     * @param e The event generated by button clicks.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (accounts == null) {
            System.err.println("Accounts object is not initialized.");
            return;
        }
        if (e.getSource() == selectLevel) {
            new LevelMenuSpecial(accounts); // Open the level menu
        } else if (e.getSource() == exitGame) {
            menu.dispose();
            new LoginForm(accounts); // Close the application
        } else if (e.getSource() == newGame) {
            GameControl game = new GameControl(accounts, true);
            game.startGame();
        } else if (e.getSource() == continueGame) {
            GameControl game = new GameControl(accounts);
            game.startGame();
        } else if (e.getSource() == options) {
            new OptionsMenu();
        } else if (e.getSource() == scores) {
            SwingUtilities.invokeLater(() -> {
                HighScore frame = null;
                try {
                    frame = new HighScore();
                } catch (ParseException x) {
                    throw new RuntimeException(x);
                }
                frame.setVisible(true);

            });
        } else if (e.getSource() == software) {
            try {
                Desktop desktop = Desktop.getDesktop();
                if (Desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.BROWSE)) {
                    URI uri = new URI("https://jira.csd.uwo.ca/secure/Dashboard.jspa");
                    desktop.browse(uri);
                } else {
                    throw new UnsupportedOperationException("Browsing not supported!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
