package src;
public class Main {
    public static void main(String[] args) {
        Accounts accounts = new Accounts();
        /** Start the UI */
        LoginForm loginForm = new LoginForm(accounts);
    }
}