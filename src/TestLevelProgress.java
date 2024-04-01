package src;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;
import org.junit.*;
import org.json.simple.JSONObject;


/**
 * Class tests the key logical components in {@link LevelProgress}.
 * 
 * @version 1.0.0
 * @author Ho Lun (Geoffrey) Kong
 */
public class TestLevelProgress {
    /**A representation of the user's current progress.*/
    private static LevelProgress progress;

    @Test
    /**
     * Tests if {@link src.LevelProgress#LevelProgress()} works as intended.
     * 
     * @see src.LevelProgress#LevelProgress()
     */
    public void testConstructorNoArgs() {
        /**Tests the creation of a new LevelProgress object.*/
        progress = new LevelProgress();
        List<String> levelNames = Arrays.asList("The Sun", "Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune", "Other Celestial Bodies");
        /**Tests that the default values for user progress are loaded correctly.*/
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
