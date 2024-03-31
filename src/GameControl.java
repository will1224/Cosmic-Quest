package src;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class GameControl {
        private Accounts accounts;
        //Progress is stored as a JSONArray within a user JSONObject.
        //It must be extracted, and then placed into the LevelProgress(JSONArray progress) constructor for the getter/setter methods to be utilized.
        private LevelProgress progress;
        private Level currLevel;
    public GameControl(Accounts account, boolean newgame) {
        accounts = account;
        clearProgress(account);
        progress = new LevelProgress((JSONArray) accounts.getCurrentAccount().get("progress"));
        //With the progress loaded into the LevelProgress object, class methods such as obtaining the current level can be done properly.
        this.currLevel = new Level(progress.getCurrentLevel());
    }
    public GameControl(Accounts account) {
        accounts = account;
        //Progress is extracted from the user JSONObject by grabbing the "progress" key (typecast is done to avoid errors).
        progress = new LevelProgress((JSONArray) accounts.getCurrentAccount().get("progress"));
        //With the progress loaded into the LevelProgress object, class methods such as obtaining the current level can be done properly.
        this.currLevel = new Level(progress.getCurrentLevel());
    }
    private void clearProgress(Accounts account){
        LevelProgress progress = new LevelProgress();
        accounts.updateUserProgress(accounts.getCurrentAccount().get("username").toString(), progress.getProgress());
    }

    // Starts the game
    public void startGame() {
        // make level
        currLevel = new Level(0);// 0 for now
        List<Question> questionSet = currLevel.getQuestions();
        GameDisplay d = new GameDisplay(accounts);
        d.displayLevel(currLevel, questionSet);
    }

   public static void main(String[] args) {
       //Accounts object must be created for login/signup to occur.
       Accounts accounts = new Accounts();
       //Account must be logged in (or new registration must be successful) for the getCurrentUser() method in Accounts.java to work properly.
       accounts.login("testing", "abc123");
       GameControl t = new GameControl(accounts);
       t.startGame();
   }
}
