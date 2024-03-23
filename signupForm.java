import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class signupForm implements ActionListener{
    private JFrame signUpPage = new JFrame();
    private JButton createAccount = new JButton("Create Account");
    private JButton backButton = new JButton("Back");
    private JTextField usernameField = new JTextField();
    private JTextField passwordField = new JPasswordField();
    private JLabel usernameLabel = new JLabel("Enter Username: ");
    private JLabel passwordLabel = new JLabel("Enter Password: ");
    private JLabel msg = new JLabel("");

    Accounts accountDatabase;
    public signupForm(Accounts acc){
        accountDatabase = acc;

        usernameLabel.setBounds(25, 100, 150, 25);
        passwordLabel.setBounds(25, 150, 150, 25);

        msg.setBounds(125,250,250,35);
        msg.setFont(new Font(null, Font.BOLD,25));

        usernameField.setBounds(125, 100, 200, 25);
        passwordField.setBounds(125, 150, 200, 25);

        backButton.setBounds(125, 200, 100, 25);
        backButton.setFocusable(false);
        backButton.addActionListener(this);

        createAccount.setBounds(225, 200, 100, 25);
        createAccount.setFocusable(false);
        createAccount.addActionListener(this);

        signUpPage.add(usernameLabel);
        signUpPage.add(passwordLabel);
        signUpPage.add(msg);
        signUpPage.add(createAccount);
        signUpPage.add(backButton);
        signUpPage.add(usernameField);
        signUpPage.add(passwordField);

        signUpPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        signUpPage.setSize(420,420);
        signUpPage.setLayout(null);
        signUpPage.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent a) {
        if (a.getSource() == backButton) {
            signUpPage.dispose();
            loginForm loginPage = new loginForm(accountDatabase);
        } else if (a.getSource() == createAccount) {
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