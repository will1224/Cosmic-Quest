import java.util.HashMap;
public class Accounts {
    private HashMap<String, String> accounts;

    public void idandpass(){
        accounts.put();

    }

    public Accounts(){
        this.accounts = new HashMap<String, String>();
    }
    protected HashMap getLoginInfo(){
        return accounts;
    }
    protected boolean accountExists(String username, String password){
        return accounts.get(username) != null;
    }
}