package src;
public class Main {
    public static void main(String[] args) {
        Accounts accounts = new Accounts();
        //Start the UI
        loginForm loginForm = new loginForm(accounts);
    }
}