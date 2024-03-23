import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.List;
import java.util.Arrays;

@SuppressWarnings("unchecked")
public class LevelProgress {
    private JSONArray progress;

    public LevelProgress() {
        List<String> levelNames = Arrays.asList("The Sun", "Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune", "Other Celestial Bodies");
        progress = new JSONArray();

        for (int i = 0; i < levelNames.size(); i++) {
            JSONObject temp = new JSONObject();
            temp.put("levelName", levelNames.get(i));
            temp.put("highscore", 0);

            if (i == 0) {
                temp.put("unlocked", true);
                temp.put("currentLevel", true);
            }
            else {
                temp.put("unlocked", false);
                temp.put("currentLevel", false);
            }
            
            progress.add(temp);
        }
    }

    public JSONArray getProgress() {
        return progress;
    }

    //Getter method that returns the current level on a user's save.
    public int getCurrentLevel() {
        for (int i = 0; i < progress.size(); i++) {
            if ((Boolean)((JSONObject)progress.get(i)).get("currentLevel")) {
                return i;
            }
        }
        return 0;
    }

    //Setter method to change the score of a level.
    public void setLevelScore(int levelNumber, int score) {
        ((JSONObject) progress.get(levelNumber)).put("highscore", score);
    }

    //Setter method to change the locked/unlocked status of a level.
    public void setLockStatus(int levelNumber, boolean lockStatus) {
        ((JSONObject) progress.get(levelNumber)).put("unlocked", lockStatus);
    }

    //Setter method to change the current level status of a level.
    public void setCurrentLevelStatus(int levelNumber, boolean currentLevelStatus) {
        ((JSONObject) progress.get(levelNumber)).put("currentLevel", currentLevelStatus);
    }
}
