package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class signupForm implements ActionListener{
    private JFrame signUpPage = new JFrame("Sign Up for Cosmic Quest: Stellar Treasure");
    private JButton createAccountButton = new JButton("Create Account");
    private JButton backButton = new JButton("Back");
    private JTextField usernameField = new JTextField(20);
    private JPasswordField passwordField = new JPasswordField(20);
    private JLabel usernameLabel = new JLabel("Enter Username: ");
    private JLabel passwordLabel = new JLabel("Enter Password: ");
    private JLabel msg = new JLabel("", SwingConstants.CENTER);
    private JLabel titleLabel = new JLabel("Welcome to Cosmic Quest: Stellar Treasure", SwingConstants.CENTER);

    Accounts accountDatabase;
    public signupForm(Accounts acc){
        accountDatabase = acc;

        signUpPage.setExtendedState(JFrame.MAXIMIZED_BOTH);
        signUpPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        // Background panel setup
        ImageIcon icon = new ImageIcon("images/mainmenuBGD.png"); 
        BackgroundPanel backgroundPanel = new BackgroundPanel(icon.getImage());
        backgroundPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
    
        // Components setup
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 50, 10, 50); // Margin settings

        Font textFieldFont = new Font("Arial", Font.PLAIN, 24);
        usernameField.setFont(textFieldFont);
        passwordField.setFont(textFieldFont);

    
        // Change text color
        usernameLabel.setForeground(Color.WHITE);
        passwordLabel.setForeground(Color.WHITE);
        msg.setForeground(Color.WHITE); // Set message color, you might want to differentiate error/success messages
        titleLabel.setForeground(Color.WHITE);
    
        createAccountButton.setForeground(Color.BLACK);
        backButton.setForeground(Color.BLACK);
    
        // Add components
        backgroundPanel.add(usernameLabel, gbc);
        backgroundPanel.add(usernameField, gbc);
        backgroundPanel.add(passwordLabel, gbc);
        backgroundPanel.add(passwordField, gbc);
    
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setOpaque(false); // Transparent to show the background
        buttonPanel.add(backButton);
        buttonPanel.add(createAccountButton);
        backgroundPanel.add(buttonPanel, gbc);
    
        backgroundPanel.add(msg, gbc);
    
        // Listeners
        backButton.addActionListener(this);
        createAccountButton.addActionListener(this);
    
        // Frame setup
        signUpPage.setContentPane(backgroundPanel);
        signUpPage.pack();
        signUpPage.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent a) {
        if (a.getSource() == backButton) {
            signUpPage.dispose();
            loginForm loginPage = new loginForm(accountDatabase);
        } else if (a.getSource() == createAccountButton) {
            boolean succesfulCreation = accountDatabase.registerAccount(usernameField.getText(), passwordField.getText());
            if (succesfulCreation) {
                msg.setForeground(Color.green);
                msg.setFont(new Font(null, Font.BOLD,15));
                msg.setText("Created account successfully!");
                signUpPage.add(msg);
                signUpPage.dispose();
                mainMenu mainMenu = new mainMenu();
            }
            else {
                msg.setForeground(Color.red);
                msg.setText("An account with this username already exists. Please try to login instead.");
                signUpPage.add(msg);
            }
        }
    }

}