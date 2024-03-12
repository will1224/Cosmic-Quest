import java.util.HashMap;
public class Accounts {
    private HashMap<String, String> accounts;

    public void idandpass(){
        accounts.put("William", "Guo");
        accounts.put("Sophia", "Tong");
        accounts.put("Jennifer", "Cao");
        accounts.put("Geoffrey", "Kong");
        accounts.put("Ryanna", "Luo");
        accounts.put("","");
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