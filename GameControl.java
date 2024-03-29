import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class GameControl {
        //Users are stored as JSONObjects {@see Accounts}
        /*
         * {
         *      "username": "testing",
         *      "password": "abc123",
         *      "progress": [
         *                      {
         *                          "levelName": "The Sun",
         *                          "levelNumber": 0,
         *                          "unlocked": true,
         *                          "currentLevel": true,
         *                          "highscore": 1234
         *                      },
         *                      {...},
         *                      {...},
         *                      ...
         *                  ]
         * }
         */
        JSONObject currUser;
        //Progress is stored as a JSONArray within a user JSONObject.
        //It must be extracted, and then placed into the LevelProgress(JSONArray progress) constructor for the getter/setter methods to be utilized.
        LevelProgress progress;
        Level currLevel;
    public GameControl(JSONObject currUser) {
        this.currUser = currUser;
        //Progress is extracted from the user JSONObject by grabbing the "progress" key (typecast is done to avoid errors).
        progress = new LevelProgress((JSONArray) this.currUser.get("progress"));
        //With the progress loaded into the LevelProgress object, class methods such as obtaining the current level can be done properly.
        this.currLevel = new Level(progress.getCurrentLevel()); 
    }

    // Starts the game
    public void startGame() {
        // make level
        currLevel = new Level(progress.getCurrentLevel());
        List<Question> questionSet = currLevel.getQuestions();

        GameDisplay d = new GameDisplay();
        d.displayLevel(currLevel, questionSet);
    }

    public static void main(String[] args) {
        //Accounts object must be created for login/signup to occur.
        Accounts accounts = new Accounts();
        //Account must be logged in (or new registration must be successful) for the getCurrentUser() method in Accounts.java to work properly.
        accounts.login("testing", "abc123");
        GameControl t = new GameControl(accounts.getCurrentAccount());
        t.startGame();
    }
}
