import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.List;
import java.util.Arrays;

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
}
