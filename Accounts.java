import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import org.json.simple.JSONArray;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@SuppressWarnings("unchecked")
public class Accounts {
    private JSONArray accounts;

    //Constructor will either create a new accounts file or read in an existing one.
    public Accounts(){
        try {
            File accountsFile = new File("accountsdata.json");
            accounts = new JSONArray();
            //Check if the accounts file exists.
            if (accountsFile.createNewFile()) {
                //Write an empty JSONArray to the file.
                FileWriter fw = new FileWriter("accountsdata.json");
                fw.write(accounts.toJSONString());
                fw.close();
            }
            else {
                //Accounts file already exists, so read in data from the file.
                JSONArray temp = (JSONArray) new JSONParser().parse(new FileReader("accountsdata.json"));
                for (int i = 0; i < temp.size(); i++) {
                    accounts.add((JSONObject) temp.get(i));
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //Method to register an account. Returns true if successful, false otherwise.
    public boolean registerAccount(String username, String password) {
        //Gets input from fields in register form (later).
        //Check if account already exists by looping through the account data.
        if (accountExists(username)) {
            return false;
        }
        //If not, add account to the database with the user's chosen username and password.
        JSONObject newAccount = new JSONObject();
        newAccount.put("username", username);
        newAccount.put("password", password);
        //Create progress data.
        LevelProgress newProgress = new LevelProgress();
        newAccount.put("progress", newProgress.getProgress());
        updateAccountsFile(newAccount);

        return true;
    }

    //Checks login credentials. Returns false if account does not exist or password is incorrect. Returns true only on successful login.
    public boolean login(String username, String password) {
        //If the account does not exist, return false.
        if (!accountExists(username)) {
            return false;
        }
        String correctPassword = getPassword(username);
        if (!correctPassword.equals(password)) {
            return false;
        }
        return true;
    }

    //Private helper method for getting the password associated with a user account.
    public String getPassword(String username) {
        for (int i = 0; i < accounts.size(); i++) {
            String currentUsername = (String) ((JSONObject) accounts.get(i)).get("username");
            if (currentUsername.equals(username)) {
                return (String) ((JSONObject) accounts.get(i)).get("password");
            }
        }
        return "";
    }

    //Checks the accounts file to see if the account exists. Returns true if the account exists, false otherwise.
    public boolean accountExists(String username) {
        for (int i = 0; i < accounts.size(); i++) {
            //If so, return false.
            String currentUsername = (String) ((JSONObject) accounts.get(i)).get("username");
            if (currentUsername.equals(username)) {
                return true;
            }
        }
        return false;
    }

    //Updates the accounts instance variable and the accountsdata.json file with new account data.
    private void updateAccountsFile(JSONObject newAccount) {
        accounts.add(newAccount);

        try {
            FileWriter fw = new FileWriter("accountsdata.json");
            fw.write(accounts.toJSONString());
            fw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Getter method that returns a JSONArray with all accounts.
    public JSONArray getAccounts() {
        return accounts;
    }

    //Getter method that returns the progress for a user's account.
    public JSONObject getAccountData(String username) {
        for (int i = 0; i < accounts.size(); i++) {
            JSONObject currentAccount = (JSONObject) accounts.get(i);
            if (currentAccount.get(username).equals(username)) {
                return currentAccount;
            }
        }
        return new JSONObject();
    }

    //Getter method that returns the progress for a given account.
    public JSONArray getProgress(JSONObject account) {
        return (JSONArray) account.get("progress");
    }

    //Setter method that updates the level progress for a user and writes the user to the accounts data file.
    public void updateUserProgress(String username, JSONArray progress) {
        for (int i = 0; i < accounts.size(); i++) {
            if (((JSONObject) accounts.get(i)).get(username).equals(username)) {
                ((JSONObject) accounts.get(i)).put("progress", progress);
                break;
            }
        }
        try {
            FileWriter fw = new FileWriter("accountsdata.json");
            fw.write(accounts.toJSONString());
            fw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}