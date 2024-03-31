package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class creates the main menu for the Cosmic Quest: Stellar Treasures
 * game.
 * It initializes the game window and populates it with buttons for navigating
 * to
 * different parts of the game, such as starting a new game, continuing a
 * previous game,
 * selecting a level, viewing scores, adjusting options, and exiting the game.
 * It utilizes a JFrame for the main menu window and JButton components for the
 * user interactions.
 * The class also manages action events triggered by button clicks to perform
 * the corresponding actions.
 * 
 * @author Sophia Tong
 */
public class MainMenu implements ActionListener {
    private JFrame menu;
    private JTextField title;
    private JButton newGame;
    private JButton continueGame;
    private JButton selectLevel;
    private JButton scores;
    private JButton options;
    private JButton exitGame;
    private Accounts accounts;

     /**
     * Constructs the MainMenu and initializes the game's main menu interface.
     * It sets up the layout, background image, buttons, and their action listeners.
     * 
     * @param accounts The Accounts instance for managing user accounts and data.
     */
    public MainMenu(Accounts accounts) {
        this.accounts = accounts;
        menu = new JFrame("Cosmic Quest: Stellar Treasures");
        java.net.URL menuBackgroundURL = getClass().getResource("/images/mainmenuBGD.png");
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
     * Helper method to create a JButton with an image icon.
     * It configures the button to have no border, no content area filled, 
     * and no focus painted, and adds it as an action listener to this class.
     * 
     * @param imagePath The path to the image used as the button icon.
     * @return A configured JButton with the specified image icon.
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
     * Handles action events triggered by button clicks in the main menu.
     * Depending on which button is clicked, it performs the corresponding action, such as 
     * opening a new game, continuing a game, opening the level menu, displaying the score board, 
     * showing options, or exiting the game.
     * 
     * @param e The ActionEvent triggered by clicking a button.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (accounts == null) {
            System.err.println("Accounts object is not initialized.");
            return;
        }
        if (e.getSource() == selectLevel) {
            new LevelMenu(accounts); // Open the level menu
        } else if (e.getSource() == exitGame) {
            menu.dispose();
            new LoginForm(accounts);
        } else if (e.getSource() == newGame) {
            menu.dispose(); // Example: Close the main menu and start a new game
            GameControl game = new GameControl(accounts, true);
            game.startGame();
        } else if (e.getSource() == continueGame) {
            menu.dispose();
            GameControl game = new GameControl(accounts);
            game.startGame();
        } else if (e.getSource() == options) {
            new OptionsMenu();
        } else if (e.getSource() == scores) {
            String username = accounts.getCurrentAccount().get("username").toString();
            PlayerScore userScore = accounts.getPlayerScore(username);
            new ScoreBoard(menu, userScore.getPlayerName(), userScore.getScore()).setVisible(true);
        }

    }
}
