package src;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

/**
 * The SignupForm class represents the signup form for creating a new account in the game.
 * It allows users to input a username and password and create a new account.
 * 
 * @author Sophia Tong
 * @version 1.0
 * 
 */

public class SignupForm implements ActionListener {
    private JFrame signUpPage = new JFrame("Sign Up for Cosmic Quest: Stellar Treasures");
    private JButton createAccountButton = new JButton("Create Account");
    private JButton backButton = new JButton("Back");
    private JTextField usernameField = new JTextField(20);
    private JPasswordField passwordField = new JPasswordField(20);
    private JLabel usernameLabel = new JLabel("Enter Username: ");
    private JLabel passwordLabel = new JLabel("Enter Password: ");
    private JLabel msg = new JLabel("", SwingConstants.CENTER);
    private Accounts accounts; 

    Accounts accountDatabase;

    /**
     * Constructs a new SignupForm with the provided Accounts database.
     *
     * @param acc The Accounts database to use for account management.
     */
    public SignupForm(Accounts acc) {
        accountDatabase = acc;

        signUpPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /** Set background image */
        BackgroundPanel backgroundPanel = new BackgroundPanel("/images/login.png");
        backgroundPanel.setLayout(new BorderLayout());
        signUpPage.setContentPane(backgroundPanel);

        signUpPage.setContentPane(backgroundPanel);

        /* Create form components */
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();

        /** Set layout and appearance for labels and fields */
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 50, 10, 50);

        /** Set font for labels */
        Font labelFont = new Font("Arial", Font.BOLD, 18);
        usernameLabel.setFont(labelFont);
        passwordLabel.setFont(labelFont);

        /** Set font for text fields */
        Font textFieldFont = new Font("Arial", Font.PLAIN, 24);
        usernameField.setFont(textFieldFont);
        passwordField.setFont(textFieldFont);

        /** Set preferred size for text fields */
        usernameField.setPreferredSize(new Dimension(usernameField.getPreferredSize().width, 60));
        passwordField.setPreferredSize(new Dimension(passwordField.getPreferredSize().width, 60));

        usernameLabel.setForeground(Color.WHITE);
        passwordLabel.setForeground(Color.WHITE);
        usernameField.setForeground(Color.black);
        passwordField.setForeground(Color.black);

        /** Set caret color for text fields */
        usernameField.setCaretColor(Color.black);
        passwordField.setCaretColor(Color.black);

        /** Add username label and text field to the form panel */
        formPanel.add(usernameLabel, gbc);
        formPanel.add(usernameField, gbc);
        /** Add password label and text field to the form panel */
        formPanel.add(passwordLabel, gbc);
        formPanel.add(passwordField, gbc);

        gbc.insets = new Insets(5, 50, 0, 50);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setOpaque(false);
        /** Add back and create account buttons to the button panel */
        buttonPanel.add(backButton);
        buttonPanel.add(createAccountButton);

        /** Add button panel and message label to the form panel */
        formPanel.add(buttonPanel, gbc);
        formPanel.add(msg, gbc); /** Add the message label directly under the buttons */
        msg.setFont(new Font("Arial", Font.PLAIN, 16));

        centerPanel.add(formPanel);

        backgroundPanel.add(centerPanel, BorderLayout.CENTER);

        /** Add action listeners */
        backButton.addActionListener(this);
        createAccountButton.addActionListener(this);
        signUpPage.getRootPane().setDefaultButton(createAccountButton);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        signUpPage.setSize(screenSize.width, screenSize.height);
        
        signUpPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        signUpPage.setVisible(true);
    }


    /**
     * Handles button clicks on the signup form.
     *
     * @param a The ActionEvent representing the button click event.
     */
    @Override
    public void actionPerformed(ActionEvent a) {
        if (a.getSource() == backButton) {
            /** Dispose the signup page and open the login form */
            signUpPage.dispose();
            LoginForm loginPage = new LoginForm(accountDatabase);
        } else if (a.getSource() == createAccountButton) {
            /** Attempt to register a new account */
            boolean succesfulCreation = accountDatabase.registerAccount(usernameField.getText(),
                    passwordField.getText());

            /** If account creation is successful, display success message and open the main menu */
            if (succesfulCreation) {
                msg.setForeground(Color.green);
                msg.setFont(new Font(null, Font.BOLD, 15));
                msg.setText("Created account successfully!");
                signUpPage.add(msg);
                signUpPage.dispose();
                Accounts accounts = this.accountDatabase;
                MainMenu mainMenu = new MainMenu(accounts);
            } else {
                // If account creation fails, display error message
                msg.setForeground(Color.red);
                msg.setText("An account with this username already exists. Please try to login instead.");
                signUpPage.add(msg);
            }
        }
    }

    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            super(new BorderLayout());
            try {
                URL imageUrl = getClass().getResource(imagePath);
                if (imageUrl != null) {
                    backgroundImage = ImageIO.read(imageUrl);
                } else {
                    System.err.println("Unable to load background image: " + imagePath);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

}