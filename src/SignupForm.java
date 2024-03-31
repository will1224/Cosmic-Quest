package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JLabel titleLabel = new JLabel("Welcome to Cosmic Quest: Stellar Treasure", SwingConstants.CENTER);
    private Accounts accounts; 

    Accounts accountDatabase;

    /**
     * Constructs a new SignupForm with the provided Accounts database.
     *
     * @param acc The Accounts database to use for account management.
     */
    public SignupForm(Accounts acc) {
        accountDatabase = acc;

        signUpPage.setExtendedState(JFrame.MAXIMIZED_BOTH);
        signUpPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /** Set background image */
        ImageIcon icon = new ImageIcon("images/login.png");
        BackgroundPanel backgroundPanel = new BackgroundPanel(icon.getImage());
        backgroundPanel.setLayout(new BorderLayout());

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
        gbc.insets = new Insets(10, 50, 10, 50);

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

        titleLabel.setForeground(Color.WHITE);
        usernameLabel.setForeground(Color.WHITE);
        passwordLabel.setForeground(Color.WHITE);
        usernameField.setForeground(Color.black);
        passwordField.setForeground(Color.black);

        /** Set caret color for text fields */
        usernameField.setCaretColor(Color.black);
        passwordField.setCaretColor(Color.black);

        /** Add components to form panel */
        formPanel.add(titleLabel, gbc);
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

        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        msg.setFont(new Font("Arial", Font.PLAIN, 16));

        centerPanel.add(formPanel);

        backgroundPanel.add(centerPanel, BorderLayout.CENTER);

        /** Add action listeners */
        backButton.addActionListener(this);
        createAccountButton.addActionListener(this);
        signUpPage.getRootPane().setDefaultButton(createAccountButton);

        signUpPage.pack();
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

}