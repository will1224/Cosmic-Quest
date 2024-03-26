import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.json.simple.JSONArray;

public class loginForm implements ActionListener {
    private JFrame loginPage = new JFrame("Cosmic Quest: Stellar Treasure");
    private JButton signUpButton = new JButton("Sign Up");
    private JButton loginButton = new JButton("Log In");
    private JTextField usernameField = new JTextField(20);
    private JPasswordField passwordField = new JPasswordField(20);
    private JLabel usernameLabel = new JLabel("Username: ");
    private JLabel passwordLabel = new JLabel("Password: ");
    private JLabel msg = new JLabel("", SwingConstants.CENTER);
    private JLabel titleLabel = new JLabel("Welcome to Cosmic Quest: Stellar Treasure", SwingConstants.CENTER);


    Accounts accountDatabase;

    public loginForm(Accounts acc){
        accountDatabase = acc;

        usernameLabel.setBounds(50, 100, 75, 25);
        passwordLabel.setBounds(50, 150, 75, 25);

        msg.setBounds(125,250,250,35);
        msg.setFont(new Font(null, Font.BOLD,25));

        usernameField.setBounds(125, 100, 200, 25);
        passwordField.setBounds(125, 150, 200, 25);

        loginButton.setBounds(125, 200, 100, 25);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);
        signUpButton.addActionListener(this);

        loginPage.pack();
        loginPage.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginButton){
            String username = String.valueOf(usernameField.getText());
            String password = String.valueOf(passwordField.getText());
            boolean successfulLogin = accountDatabase.login(username, password);
            if (!successfulLogin) {
                msg.setForeground(Color.red);
                msg.setFont(new Font(null, Font.BOLD,15));
                msg.setText("Username or password incorrect");
                loginPage.add(msg);
            }
            else if (successfulLogin) {
                msg.setForeground(Color.green);
                msg.setText("Login successful");
                loginPage.add(msg);
                loginPage.dispose();
                mainMenu mainMenu = new mainMenu();
            }
        }
        else if (e.getSource() == signUpButton){
            loginPage.dispose();
            signupForm signup = new signupForm(accountDatabase);
        }
    }
}