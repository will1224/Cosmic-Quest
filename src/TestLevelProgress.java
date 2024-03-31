package src;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;
import org.junit.*;
import org.json.simple.JSONObject;


public class TestLevelProgress {
    private static LevelProgress progress;

    @Test
    public void testConstructorNoArgs() {
        progress = new LevelProgress();
        List<String> levelNames = Arrays.asList("The Sun", "Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune", "Other Celestial Bodies");
        for (int i = 0; i < 10; i++) {
            JSONObject level = (JSONObject) progress.getProgress().get(i);
            assertEquals(level.get("levelName"), levelNames.get(i));
            assertEquals(level.get("levelNumber"), i);
            assertEquals(0, level.get("highscore"));
            if (i == 0) {
                assertEquals(true, level.get("currentLevel"));
                assertEquals(true, level.get("unlocked"));
            }
            else {
                assertEquals(false, level.get("currentLevel"));
                assertEquals(false, level.get("unlocked"));
            }
        }
    }
}
