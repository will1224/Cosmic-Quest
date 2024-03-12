// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Accounts accounts = new Accounts();
        accounts.idandpass();
        loginForm loginForm = new loginForm(accounts.getLoginInfo());
    }
}