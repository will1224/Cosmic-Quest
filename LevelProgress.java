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

        for (int i = 1; i <= levelNames.size(); i++) {
            JSONObject temp = new JSONObject();
            temp.put("levelName", levelNames.get(i-1));
            temp.put("levelNumber", i);
            temp.put("highscore", 0);

            if (i == 1) {
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

    public LevelProgress(JSONArray progress) {
        this.progress = progress;
    }

    public JSONArray getProgress() {
        return progress;
    }

    //Getter method that returns the current level on a user's save.
    public int getCurrentLevel() {
        for (int i = 0; i < progress.size(); i++) {
            if ((Boolean) ((JSONObject) progress.get(i)).get("currentLevel")) {
                return i + 1;
            }
        }
        return 1;
    }

    //Setter method to change the score of a level.
    public void setLevelScore(int levelNumber, int score) {
        ((JSONObject) progress.get(levelNumber - 1)).put("highscore", score);
    }

    //Setter method to change the locked/unlocked status of a level.
    public void setUnlockedStatus(int levelNumber, boolean lockStatus) {
        ((JSONObject) progress.get(levelNumber - 1)).put("unlocked", lockStatus);
    }

    //Setter method to change the current level status of a level.
    public void setCurrentLevelStatus(int levelNumber, boolean currentLevelStatus) {
        ((JSONObject) progress.get(levelNumber - 1)).put("currentLevel", currentLevelStatus);
    }
}