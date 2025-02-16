package src;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

/**
 * The {@code LoginForm} class represents the login form for the "Cosmic Quest: Stellar Treasures" application.
 * It allows users to log in or sign up for the application. The form is displayed in a fullscreen window
 * and includes fields for username and password, along with "Log In" and "Sign Up" buttons.
 * <p>
 * This class is responsible for initializing the GUI components, handling user actions, and providing
 * feedback based on the success or failure of the login attempt.
 * </p>
 * 
 * @author Sophia Tong
 * @version 1.0
 */
public class LoginForm implements ActionListener {
    private JFrame loginPage = new JFrame("Cosmic Quest: Stellar Treasures");
    private JButton signUpButton = new JButton("Sign Up");
    private JButton loginButton = new JButton("Log In");
    private JTextField usernameField = new JTextField(20);
    private JPasswordField passwordField = new JPasswordField(20);
    private JLabel usernameLabel = new JLabel("Username: ");
    private JLabel passwordLabel = new JLabel("Password: ");
    private JLabel msg = new JLabel("", SwingConstants.CENTER);
    private Color PINK = new Color(255, 104, 176);
    private Accounts accounts;

    Accounts accountDatabase;

    /**
     * Constructs a new LoginForm that is associated with the specified account database.
     * It initializes the GUI components and sets up the action listeners.
     *
     * @param accounts the {@code Accounts} database to be used for login verification.
     */
    public LoginForm(Accounts accounts) {
        accountDatabase = accounts;

        loginPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load the background image using getClass().getResource()
        BackgroundPanel backgroundPanel = new BackgroundPanel("/images/login.png");
        backgroundPanel.setLayout(new BorderLayout());
        loginPage.setContentPane(backgroundPanel);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 50, 10, 50);

        /** Set font for titles to be a bit bigger */
        Font labelFont = new Font("Arial", Font.BOLD, 18);
        usernameLabel.setFont(labelFont);
        passwordLabel.setFont(labelFont);
        gbc.insets = new Insets(-5, 50, 15, 50);

        /** Font settings for text fields */
        Font textFieldFont = new Font("Arial", Font.PLAIN, 24);
        usernameField.setFont(textFieldFont);
        passwordField.setFont(textFieldFont);

        usernameField.setPreferredSize(new Dimension(usernameField.getPreferredSize().width, 60));
        passwordField.setPreferredSize(new Dimension(passwordField.getPreferredSize().width, 60));

        usernameLabel.setForeground(Color.WHITE);
        passwordLabel.setForeground(Color.WHITE);
        usernameField.setForeground(Color.black);
        passwordField.setForeground(Color.black);
        usernameField.setCaretColor(Color.black);
        passwordField.setCaretColor(Color.black);

        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(usernameLabel, gbc);
        formPanel.add(usernameField, gbc);
        formPanel.add(passwordLabel, gbc);
        formPanel.add(passwordField, gbc);

        /** Adjust constraints for the message label */
        gbc.insets = new Insets(5, 50, 0, 50); /** Reduce top padding for the message */
        formPanel.add(msg, gbc); /** Add the message label directly under the password field */

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setOpaque(false);
        buttonPanel.add(loginButton);
        buttonPanel.add(signUpButton);
        formPanel.add(buttonPanel, gbc);
        msg.setFont(new Font("Arial", Font.PLAIN, 16));

        centerPanel.add(formPanel);

        backgroundPanel.add(centerPanel, BorderLayout.CENTER);

        loginButton.addActionListener(this);
        signUpButton.addActionListener(this);
        loginPage.getRootPane().setDefaultButton(loginButton);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        loginPage.setSize(screenSize.width, screenSize.height);

        loginPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginPage.setVisible(true);

    }

    /**
     * Handles actions performed on the GUI components. Specifically, it processes
     * the login
     * and sign up button clicks, attempting to log the user in or navigate to the
     * sign up form.
     *
     * @param e the {@code ActionEvent} triggered by interacting with the GUI
     *          components.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword()); // Correct way to get text from JPasswordField
            boolean successfulLogin = accountDatabase.login(username, password);

            if (!successfulLogin) {
                msg.setForeground(Color.red);
                msg.setFont(new Font(null, Font.BOLD, 15));
                msg.setText("Username or password incorrect");
            } else {
                msg.setForeground(Color.green);
                msg.setText("Login successful");
                loginPage.dispose(); /** Close the login window */

                /** Check username and open the corresponding main menu*/
                if (username.toLowerCase().contains("education")) {
                    MainMenuInstructor instructorMenu = new MainMenuInstructor(accountDatabase);
                } else if (username.toLowerCase().contains("developer")) {
                    MainMenuSoftware softwareMenu = new MainMenuSoftware(accountDatabase);
                } else {
                    Accounts accounts = this.accountDatabase; /** Use the existing Accounts object */
                    MainMenu mainMenu = new MainMenu(accounts);
                }
            }
        } else if (e.getSource() == signUpButton) {
            loginPage.dispose();
            SignupForm signup = new SignupForm(accountDatabase);
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
