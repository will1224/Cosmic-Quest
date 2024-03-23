import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
public class loginForm implements ActionListener {
    private JFrame loginPage = new JFrame();
    private JButton signUpButton = new JButton("Sign Up");
    private JButton loginButton = new JButton("Log In");
    private JTextField usernameField = new JTextField();
    private JTextField passwordField = new JPasswordField();
    private JLabel usernameLabel = new JLabel("Username: ");
    private JLabel passwordLabel = new JLabel("Password: ");
    private JLabel msg = new JLabel("");

    HashMap<String, String> accountDatabase;
    public loginForm(HashMap<String, String> acc){
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

        signUpButton.setBounds(225, 200, 100, 25);
        signUpButton.setFocusable(false);
        signUpButton.addActionListener(this);

        loginPage.add(usernameLabel);
        loginPage.add(passwordLabel);
        loginPage.add(msg);
        loginPage.add(signUpButton);
        loginPage.add(loginButton);
        loginPage.add(usernameField);
        loginPage.add(passwordField);

        loginPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginPage.setSize(420,420);
        loginPage.setLayout(null);
        loginPage.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginButton){
            String username = usernameField.getText();
            String password = String.valueOf(passwordField.getText());
            if (accountDatabase.get(username) == null){
                msg.setForeground(Color.red);
                msg.setFont(new Font(null, Font.BOLD,15));
                msg.setText("Username or Password Incorrect");
                loginPage.add(msg);
            }
            else if(accountDatabase.get(username).equals(password)){
                msg.setForeground(Color.green);
                msg.setText("Log In Successful");
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
