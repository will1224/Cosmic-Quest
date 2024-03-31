package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class signupForm implements ActionListener {
    private JFrame signUpPage = new JFrame("Sign Up for Cosmic Quest: Stellar Treasures");
    private JButton createAccountButton = new JButton("Create Account");
    private JButton backButton = new JButton("Back");
    private JTextField usernameField = new JTextField(20);
    private JPasswordField passwordField = new JPasswordField(20);
    private JLabel usernameLabel = new JLabel("Enter Username: ");
    private JLabel passwordLabel = new JLabel("Enter Password: ");
    private JLabel msg = new JLabel("", SwingConstants.CENTER);
    private JLabel titleLabel = new JLabel("Welcome to Cosmic Quest: Stellar Treasure", SwingConstants.CENTER);

    Accounts accountDatabase;

    public signupForm(Accounts acc) {
        accountDatabase = acc;

        signUpPage.setExtendedState(JFrame.MAXIMIZED_BOTH);
        signUpPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon("images/login.png");
        BackgroundPanel backgroundPanel = new BackgroundPanel(icon.getImage());
        backgroundPanel.setLayout(new BorderLayout());

        signUpPage.setContentPane(backgroundPanel);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 50, 10, 50);

        Font labelFont = new Font("Arial", Font.BOLD, 18);
        usernameLabel.setFont(labelFont);
        passwordLabel.setFont(labelFont);

        Font textFieldFont = new Font("Arial", Font.PLAIN, 24);
        usernameField.setFont(textFieldFont);
        passwordField.setFont(textFieldFont);

        usernameField.setPreferredSize(new Dimension(usernameField.getPreferredSize().width, 60));
        passwordField.setPreferredSize(new Dimension(passwordField.getPreferredSize().width, 60));

        titleLabel.setForeground(Color.WHITE);
        usernameLabel.setForeground(Color.WHITE);
        passwordLabel.setForeground(Color.WHITE);
        usernameField.setForeground(Color.black);
        passwordField.setForeground(Color.black);
        usernameField.setCaretColor(Color.black);
        passwordField.setCaretColor(Color.black);

        formPanel.add(titleLabel, gbc);
        formPanel.add(usernameLabel, gbc);
        formPanel.add(usernameField, gbc);
        formPanel.add(passwordLabel, gbc);
        formPanel.add(passwordField, gbc);

        gbc.insets = new Insets(5, 50, 0, 50);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setOpaque(false);
        buttonPanel.add(backButton);
        buttonPanel.add(createAccountButton);
        formPanel.add(buttonPanel, gbc);

        formPanel.add(msg, gbc); // Add the message label directly under the buttons

        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        msg.setFont(new Font("Arial", Font.PLAIN, 16));

        centerPanel.add(formPanel);

        backgroundPanel.add(centerPanel, BorderLayout.CENTER);

        backButton.addActionListener(this);
        createAccountButton.addActionListener(this);

        signUpPage.pack();
        signUpPage.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent a) {
        if (a.getSource() == backButton) {
            signUpPage.dispose();
            loginForm loginPage = new loginForm(accountDatabase);
        } else if (a.getSource() == createAccountButton) {
            boolean succesfulCreation = accountDatabase.registerAccount(usernameField.getText(),
                    passwordField.getText());
            if (succesfulCreation) {
                msg.setForeground(Color.green);
                msg.setFont(new Font(null, Font.BOLD, 15));
                msg.setText("Created account successfully!");
                signUpPage.add(msg);
                signUpPage.dispose();
                mainMenu mainMenu = new mainMenu(accountDatabase);
            } else {
                msg.setForeground(Color.red);
                msg.setText("An account with this username already exists. Please try to login instead.");
                signUpPage.add(msg);
            }
        }
    }

}