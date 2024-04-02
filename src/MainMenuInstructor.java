package src;
import javax.swing.*;
import org.json.simple.parser.ParseException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The MainMenuInstructor class represents the main menu of the game for instructors.
 * It provides options such as starting a new game, continuing a game, selecting a level, viewing scores, accessing options, and exiting the game.
 * 
 * This class handles the initialization of the main menu interface and responds to user actions.
 * 
 * @author Sophia Tong
 * @version 1.0
 */
public class MainMenuInstructor implements ActionListener {
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
     * Constructs a MainMenuInstructor object and initializes the main menu interface.
     */
    public MainMenuInstructor(Accounts accounts) {
        this.accounts = accounts;
        /** Initialize JFrame for main menu */
        menu = new JFrame("Cosmic Quest: Stellar Treasures");
        java.net.URL menuBackgroundURL = getClass().getResource("/images/menu.png");

        /** Set background image for the menu if available */
        if (menuBackgroundURL != null) {
            ImageIcon menuBackground = new ImageIcon(menuBackgroundURL);
            JLabel backgroundLabel = new JLabel(menuBackground);
            backgroundLabel.setLayout(new BorderLayout());
            menu.setContentPane(backgroundLabel);
            menu.setLayout(new GridBagLayout());
        } else {
            System.err.println("Unable to load background image.");
        }
        /** Container Panel with BoxLayout for vertical stacking */
        JPanel buttonPanel = new JPanel(); /** Panel for holding buttons */
        buttonPanel.setOpaque(false); /**Make panel transparent*/
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        title = new JTextField("");
        title.setForeground(Color.WHITE);
        title.setFont(new Font(null, Font.BOLD, 24));
        title.setHorizontalAlignment(JTextField.CENTER); /**Ensure title is centered */
        title.setEditable(false); /** Make it non-editable */
        title.setBorder(null); /** Remove border */
        title.setOpaque(false); /**Make background transparent */

        newGame = createImageButton("images/newgameBTN.png");
        continueGame = createImageButton("images/continueBTN.png");
        selectLevel = createImageButton("images/levelBTN.png");
        scores = createImageButton("images/scoreBTN.png");
        options = createImageButton("images/optionBTN.png");
        exitGame = createImageButton("images/exitBTN.png");

        /** Add components to the button panel */
        buttonPanel.add(Box.createVerticalGlue()); /** Add space at the top */
        buttonPanel.add(title);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20))); /** Add space between title and buttons */
        buttonPanel.add(Box.createVerticalStrut(30)); /** Space underneath Button */
        buttonPanel.add(newGame);
        buttonPanel.add(Box.createVerticalStrut(20)); /** Space underneath Button */
        buttonPanel.add(continueGame);
        buttonPanel.add(Box.createVerticalStrut(20)); /** Space underneath Button */
        buttonPanel.add(selectLevel);
        buttonPanel.add(Box.createVerticalStrut(20)); /** Space underneath Button */
        buttonPanel.add(scores);
        buttonPanel.add(Box.createVerticalStrut(20)); /** Space underneath Button */
        buttonPanel.add(options);
        buttonPanel.add(Box.createVerticalStrut(20)); /** Space underneath Button */
        buttonPanel.add(exitGame);
        buttonPanel.add(Box.createVerticalGlue()); /** Add space at the bottom */

        /** Add button panel to the frame */
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
        java.net.URL imageUrl = getClass().getResource("/"+ imagePath);
        if(imageUrl !=null) {
            ImageIcon icon = new ImageIcon(imageUrl);
            JButton button = new JButton(icon);
            button.setBorderPainted(false);
            button.setContentAreaFilled(false);
            button.setFocusPainted(false);
            button.setMaximumSize(new Dimension(icon.getIconWidth() + 20, icon.getIconHeight() + 20));
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.addActionListener(this);
            return button;
        }
        return null;
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
            new LevelMenuSpecial(accounts);
        } else if (e.getSource() == exitGame) {
            menu.dispose();
            new LoginForm(accounts);
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
        }
    }
}
