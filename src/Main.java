package src;


/**
 * The Main class serves as the entry point for the application.
 * It initializes the Accounts object and starts the user interface by creating a LoginForm.
 * 
 * @author Ryanna Luo
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        Accounts accounts = new Accounts();
        /** Start the UI */
        LoginForm loginForm = new LoginForm(accounts);
    }
}