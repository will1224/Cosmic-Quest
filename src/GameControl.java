package src;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * The GameControl class is responsible for managing the game's state,
 * including player accounts, level progression, and the game display.
 * It handles game initialization, progress management, and starting the game session.
 * 
 * @author Jennifer Cao
 * @version 1.0
 */
public class GameControl {
        private Accounts accounts;
        //Progress is stored as a JSONArray within a user JSONObject.
        //It must be extracted, and then placed into the LevelProgress(JSONArray progress) constructor for the getter/setter methods to be utilized.
        private LevelProgress progress;
        private Level currLevel;
        private GameDisplay d;

     /**
     * Constructs a new GameControl instance for a new or existing game.
     * Initializes the game with the given account and clears any existing progress if it's a new game.
     * 
     * @param acc The player's account.
     * @param newgame A boolean indicating if it's a new game (true) or not (false).
     */    
    public GameControl(Accounts acc, boolean newgame) {
        accounts = acc;
        clearProgress(acc);
        progress = new LevelProgress((JSONArray) accounts.getCurrentAccount().get("progress"));
        this.currLevel = new Level(0);
    }

    /**
     * Constructs a new GameControl instance for an existing game.
     * Initializes the game with the given account and loads the player's current progress.
     * 
     * @param account The player's account.
     */
    public GameControl(Accounts account) {
        accounts = account;
        //Progress is extracted from the user JSONObject by grabbing the "progress" key (typecast is done to avoid errors).
        progress = new LevelProgress((JSONArray) accounts.getCurrentAccount().get("progress"));
        //With the progress loaded into the LevelProgress object, class methods such as obtaining the current level can be done properly.
        this.currLevel = new Level(progress.getCurrentLevel());
    }

    /**
     * Clears the progress of the given account.
     * 
     * @param account The account whose progress is to be cleared.
     */
    private void clearProgress(Accounts account){
        LevelProgress progress = new LevelProgress();
        accounts.updateUserProgress(accounts.getCurrentAccount().get("username").toString(), progress.getProgress());
    }

    /**
     * Starts the game session.
     * It sets up the questions for the current level and displays it using the GameDisplay.
     */
    public void startGame() {
        // set up questions
        List<Question> questionSet = currLevel.getQuestions();
//        System.out.println(currLevel.getName()); // TEMP TESTER
        d = new GameDisplay(accounts);
        d.displayLevel(currLevel, questionSet, false);
    }

    public void playLevel(int levelId){
        Level level = new Level(levelId);
        List<Question> questionSet = level.getQuestions();
        d = new GameDisplay(accounts);
        d.displayLevel(level, questionSet, true);
    }

     /**
     * The main method to start the game.
     * Creates an Accounts object, logs in, and starts the game.
     * 
     * @param args Command line arguments (not used).
     */
   public static void main(String[] args) {
       //Accounts object must be created for login/signup to occur.
       Accounts accounts = new Accounts();
       //Account must be logged in (or new registration must be successful) for the getCurrentUser() method in Accounts.java to work properly.
       accounts.login("testing", "abc123");
       GameControl t = new GameControl(accounts);
       t.startGame();
   }
}
