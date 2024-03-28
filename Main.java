// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Main {
    public static void main(String[] args) {
        Accounts accounts = new Accounts();
        accounts.registerAccount("testing", "abc123");
        JSONObject loggedInUser = accounts.getCurrentAccount();
        LevelProgress progress = new LevelProgress((JSONArray) loggedInUser.get("progress"));
        //Simulation: Successful completion of a level
        //Updates the high score for the completed level.
        progress.setLevelScore(1, 6969);
        //Unlock the next level.
        progress.setUnlockedStatus(2, true);
        //Set the current level to the next level for save file purposes.
        //Also revokes current level status for the completed level.
        progress.setCurrentLevelStatus(1, false);
        progress.setCurrentLevelStatus(2, true);
        //Update the progress in the user's account.
        accounts.updateUserProgress((String) loggedInUser.get("username"), progress.getProgress());
        //Start the UI
        loginForm loginForm = new loginForm(accounts);
    }
}