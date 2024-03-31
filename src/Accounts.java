package src;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import org.json.simple.JSONArray;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * This class is used to store all relevant information related to accounts.<br><br>
 * Accounts data is stored in the <b>accountsdata.json</b> file.<br><br>
 * Account registration and login are handled by the {@link registerAccount} and {@link login} methods respectively.<br><br>
 * A variety of getter and setter methods are also provided to obtain and modify account information.
 * 
 * @version 1.0.0
 * @author Ho Lun (Geoffrey) Kong
 */
@SuppressWarnings("unchecked")
public class Accounts {
    /**A representation of the information within <b>accountsdata.json</b> for easy access within the class.*/
    private JSONArray accounts;
    /**A representation of the information of the currently logged in user for easy access within the class. */
    private JSONObject currentAccount;

    /**
     * Constructor will either create a new accounts file or read in an existing one.
     * 
     * If the file <b>accountsdata.json</b> does not exist, the constructor will create an empty JSONArray and store it within the file.
     * If the file <b>accountsdata.json</b> does exist, the constructor will read the data in the file and load the list of accounts into the accounts instance variable.
     */
    public Accounts() {
        /**Try-catch block to catch IO and parsing exceptions.*/
        try {
            /**Obtain the data file from the current directory.*/
            File accountsFile = new File("src/accountsdata.json");
            /**Set the list of accounts to be empty.*/
            accounts = new JSONArray();
            /**Check if the accounts file exists.*/
            if (accountsFile.createNewFile()) {
                /**Write an empty JSONArray to the file.*/
                FileWriter fw = new FileWriter("src/accountsdata.json");
                fw.write(accounts.toJSONString());
                fw.close();
            }
            else {
                /**Accounts file already exists, so read in data from the file.*/
                JSONArray temp = (JSONArray) new JSONParser().parse(new FileReader("src/accountsdata.json"));
                /**Add each account into the list of accounts.*/
                for (int i = 0; i < temp.size(); i++) {
                    accounts.add((JSONObject) temp.get(i));
                }
            }
        }
        /**Catches IOExceptions.*/
        catch (IOException e) {
            e.printStackTrace();
        }
        /**Catches ParseExceptions.*/
        catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method registers users for an account, and updates the accounts file and the currently logged in user.
     * 
     * @param username the username the user wishes to register under
     * @param password the password the user wishes to use for their account
     * @return true if account was successfully registered, false if the account already exists
     * 
     * @see accountExists
     * @see updateAccountsFile
     * @see getAccountData
     * @see LevelProgress
     */
    public boolean registerAccount(String username, String password) {
        /**Gets input from fields in register form (later).*/
        /**Check if account already exists by looping through the account data.*/
        if (accountExists(username)) {
            return false;
        }
        /**If not, add account to the database with the user's chosen username and password.*/
        JSONObject newAccount = new JSONObject();
        newAccount.put("username", username);
        newAccount.put("password", password);
        /**Creates progress data.*/
        LevelProgress newProgress = new LevelProgress();
        newAccount.put("progress", newProgress.getProgress());
        /**Updates the accounts file with the new account's information.*/
        updateAccountsFile(newAccount);
        /**Updates the currently logged in user to the new account data.*/
        currentAccount = getAccountData(username);
        /**Return true for a successful registration.*/
        return true;
    }

    /**
     * Method logs user into their existing account, and updates the currently logged in user.
     * 
     * @param username the username for the account the user wishes to login as
     * @param password the password the for the user account the user wishes to login as
     * @return true if the login was sucessful, false if the login was unsuccessful because either the login or password is incorrect
     * 
     * @see accountExists
     * @see getAccountData
     * @see correctPassword
     */
    public boolean login(String username, String password) {
        /**If the account does not exist, return false.*/
        if (!accountExists(username)) {
            return false;
        }
        /**If the password is not correct, return false.*/
        if (!correctPassword(username, password)) {
            return false;
        }
        //Updates the currently logged in user to the account the user just logged in as.
        currentAccount = getAccountData(username);
        /**Return true for a successful login.*/
        return true;
    }

    /**
     * Method indicates whether a specified username and password combination is valid.
     * 
     * @param username the username to check credential validity for
     * @param password the password to check credential validity for
     * @return true if the credential pair is valid, false otherwise
     */
    public boolean correctPassword(String username, String password) {
        for (int i = 0; i < accounts.size(); i++) {
            String currentUsername = (String) ((JSONObject) accounts.get(i)).get("username");
            boolean validPassword = (Boolean) ((JSONObject) accounts.get(i)).get("password").equals(password);
            if (currentUsername.equals(username) && validPassword) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method checks if an account exists in the account data file.
     * 
     * @param username the username to check account existence for
     * @return true if the account exists in the data file, false otherwise
     */
    public boolean accountExists(String username) {
        /**Loop through the account data file.*/
        for (int i = 0; i < accounts.size(); i++) {
            //Check each account for the provided username.
            String currentUsername = (String) ((JSONObject) accounts.get(i)).get("username");
            //If the username is found, return true.
            if (currentUsername.equals(username)) {
                return true;
            }
        }
        /**Otherwise, the account does not exist in the data file, and return false.*/
        return false;
    }

    /**
     * Method updates the accounts instance variable with updated information for one account.
     * 
     * @param newAccount the account to update information for in the data file
     * 
     * @see registerAccount
     */
    private void updateAccountsFile(JSONObject newAccount) {
        /**Add the account to the instance variable.*/
        accounts.add(newAccount);

        /**Try-catch block to catch IOExceptions from file writing.*/
        try {
            //Overwrites the contents of the data file with the updated account data.
            FileWriter fw = new FileWriter("src/accountsdata.json");
            fw.write(accounts.toJSONString());
            fw.close();
        }
        /**Catches IOExceptions from writing data.*/
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method returns the instance variable representation of all account data.
     * 
     * @return a JSONArray that represents all accounts and their data
     */
    public JSONArray getAccounts() {
        return accounts;
    }

    /**
     * Method returns the instance variable representation of the current logged in account's data.
     * 
     * @return a JSONObject that represents the currently logged in account's data
     */
    public JSONObject getCurrentAccount() {
        return currentAccount;
    }

    /**
     * Method returns the data of a specific account from the data file.
     * 
     * @param username the username of the account to obtain the data for
     * @return a JSONObject that represents the desired account to obtain the data for
     */
    public JSONObject getAccountData(String username) {
        /**Loop through the list of accounts.*/
        for (int i = 0; i < accounts.size(); i++) {
            /**Get the current account and check the username.*/
            JSONObject currentAccount = (JSONObject) accounts.get(i);
            /**If the usernames match, return the account.*/
            if (currentAccount.get("username").equals(username)) {
                return currentAccount;
            }
        }
        /**Otherwise, the account does not exist, and an empty JSONObject is returned.*/
        return null;
    }

    /**
     * Method returns level progress for a specific account.
     * 
     * @param account the account to obtain the progress from
     * @return a JSONArray that contains the progress for a user
     * 
     * @see LevelProgress
     */
    public JSONArray getProgress(JSONObject account) {
        return (JSONArray) account.get("progress");
    }

    /**
     * Method updates a specific account's progress and writes the updated account data to the data file.
     * 
     * @param username the username of the account to update the data for
     * @param progress the progress to add to the account
     */
    public void updateUserProgress(String username, JSONArray progress) {
        /**Loop through the list of all accounts.*/
        for (int i = 0; i < accounts.size(); i++) {
            /**If the usernames match, updates the progress to the new progress and break out of the loop.*/
            if (((JSONObject) accounts.get(i)).get("username").equals(username)) {
                ((JSONObject) accounts.get(i)).put("progress", progress);
                break;
            }
        }
        /**Try-catch to catch IOExceptions*/
        try {
            /**Writes the updated account data to the data file.*/
            FileWriter fw = new FileWriter("src/accountsdata.json");
            fw.write(accounts.toJSONString());
            fw.close();
        }
        /**Catches IOExceptions. */
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method logs out a user and sets the currently logged in user instance variable to null.
     */
    public void logout() {
        currentAccount = null;
    }


    public PlayerScore getPlayerScore(String username){
        Accounts accounts = new Accounts();
        JSONArray allUserData = accounts.getAccounts();
//            String jsonData = "[{\"password\":\"cosmic123\",\"progress\":[{\"currentLevel\":false,\"highscore\":1,\"levelNumber\":0,\"levelName\":\"The Sun\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":2,\"levelNumber\":1,\"levelName\":\"Mercury\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":2,\"levelName\":\"Venus\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":3,\"levelName\":\"Earth\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":4,\"levelName\":\"Mars\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":5,\"levelName\":\"Jupiter\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":6,\"levelName\":\"Saturn\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":7,\"levelName\":\"Uranus\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":8,\"levelName\":\"Neptune\",\"unlocked\":true},{\"currentLevel\":true,\"highscore\":0,\"levelNumber\":9,\"levelName\":\"Other Celestial Bodies\",\"unlocked\":true}],\"username\":\"education\"},{\"password\":\"cosmic456\",\"progress\":[{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":0,\"levelName\":\"The Sun\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":1,\"levelName\":\"Mercury\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":2,\"levelName\":\"Venus\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":3,\"levelName\":\"Earth\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":4,\"levelName\":\"Mars\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":5,\"levelName\":\"Jupiter\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":6,\"levelName\":\"Saturn\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":7,\"levelName\":\"Uranus\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":8,\"levelName\":\"Neptune\",\"unlocked\":true},{\"currentLevel\":true,\"highscore\":0,\"levelNumber\":9,\"levelName\":\"Other Celestial Bodies\",\"unlocked\":true}],\"username\":\"developer\"},{\"password\":\"abc123\",\"progress\":[{\"currentLevel\":true,\"highscore\":0,\"levelNumber\":0,\"levelName\":\"The Sun\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":1,\"levelName\":\"Mercury\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":2,\"levelName\":\"Venus\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":3,\"levelName\":\"Earth\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":4,\"levelName\":\"Mars\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":5,\"levelName\":\"Jupiter\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":6,\"levelName\":\"Saturn\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":7,\"levelName\":\"Uranus\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":8,\"levelName\":\"Neptune\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":9,\"levelName\":\"Other Celestial Bodies\",\"unlocked\":false}],\"username\":\"testing\"},{\"password\":\"\",\"progress\":[{\"currentLevel\":true,\"highscore\":0,\"levelNumber\":0,\"levelName\":\"The Sun\",\"unlocked\":true},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":1,\"levelName\":\"Mercury\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":2,\"levelName\":\"Venus\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":3,\"levelName\":\"Earth\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":4,\"levelName\":\"Mars\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":5,\"levelName\":\"Jupiter\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":6,\"levelName\":\"Saturn\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":7,\"levelName\":\"Uranus\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":8,\"levelName\":\"Neptune\",\"unlocked\":false},{\"currentLevel\":false,\"highscore\":0,\"levelNumber\":9,\"levelName\":\"Other Celestial Bodies\",\"unlocked\":false}],\"username\":\"\"}]";
//            JSONArray allUserData = null;
//            allUserData = (JSONArray) new JSONParser().parse(jsonData);
        List<PlayerScore> userScores = new ArrayList<>();
        int score = 0;
        for (Object i : allUserData){
            JSONObject userData = (JSONObject) i;
            if(username.equals(userData.get("username").toString())) {
                JSONArray progressArray = (JSONArray) userData.get("progress");
                for (Object element : progressArray) {
                    JSONObject levelData = (JSONObject) element;
                    Object highScoreobj = levelData.get("highscore");
                    if (highScoreobj != null) {
                        try {
                            score += Integer.parseInt(highScoreobj.toString());
                        } catch (NumberFormatException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                break;
            }
        }
        return new PlayerScore(username, score);
    }

    public static void main(String[] args) {
        Accounts accounts = new Accounts();
        JSONArray testing = accounts.getAccounts();
        System.out.println(testing);
    }
}