package src;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.List;
import java.util.Arrays;


/**
 * Class tracks level progress and implements methods to modify and obtain level information.
 * 
 * @version 1.0.0
 * @author Ho Lun (Geoffrey) Kong
 */
@SuppressWarnings("unchecked")
public class LevelProgress {
    /**Instance variable stores the user's progress through each level.*/
    private JSONArray progress;

    /**
     * Constructor builds the default progress for a new account (all levels locked, except the Sun, which is set to the current level, and all scores set to zero).
     */
    public LevelProgress() {
        /**Create a list of level names for each level.*/
        List<String> levelNames = Arrays.asList("The Sun", "Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune", "Other Celestial Bodies");
        /**Create a new JSONArray to store the progress.*/
        progress = new JSONArray();

        /**
         * Initalize each level to default values.
         * 
         * The Sun is unlocked and set as the current level, while all other levels are locked.
         * All levels start with a score of 0.
        */
        for (int i = 0; i < levelNames.size(); i++) {
            JSONObject temp = new JSONObject();
            temp.put("levelName", levelNames.get(i));
            temp.put("levelNumber", i);
            temp.put("highscore", 0);

            if (i == 0) {
                temp.put("unlocked", true);
                temp.put("currentLevel", true);
            }
            else {
                temp.put("unlocked", false);
                temp.put("currentLevel", false);
            }
            
            /**Each level is then added to the progress tracker.*/
            progress.add(temp);
        }
    }

    /**
     * Constructor takes in progress as input so that the getter and setter methods in this class can be applied to it.
     * 
     * @param progress a JSONArray that stores a user's progress.
     */
    public LevelProgress(JSONArray progress) {
        this.progress = progress;
    }

    /**
     * Method returns the progress instance variable.
     * 
     * @return a JSONArray storing the progress in the instance variable.
     */
    public JSONArray getProgress() {
        return progress;
    }

    /**
     * Method returns the level number of the user's current level.
     * 
     * @return the number of the current level the user is on
     */
    public int getCurrentLevel() {
        /**Loop through the levels.*/
        for (int i = 0; i < progress.size(); i++) {
            /**If the current level is found, then return the level number.*/
            if ((Boolean) ((JSONObject) progress.get(i)).get("currentLevel")) {
                return i;
            }
        }
        /**Otherwise, return the tutorial level number.*/
        return 0;
    }

    /**
     * Method sets the score for a specified level.
     * 
     * @param levelNumber the number of the level to change the score for
     * @param score the new score to assign to the level
     */
    public void setLevelScore(int levelNumber, int score) {
        ((JSONObject) progress.get(levelNumber)).put("highscore", score);
    }

    /**
     * Method gets the score for a specified level.
     * 
     * @param levelNumber the number of the level to get the score for
     * @return the highscore of the specified level
     */
    public int getLevelScore(int levelNumber) {
        for(Object i: progress){
            JSONObject arr = (JSONObject) i;
            if (arr.get("levelNumber").toString().equals(String.valueOf(levelNumber))){
                return Integer.parseInt(arr.get("highscore").toString());
            }
        }
        return -1;
    }

    /**
     * Method sets the unlocked status for a specified level.
     * 
     * @param levelNumber the number of the level to get the unlocked status for
     * @param unlockStatus the unlocked status to set the level to
     */
    public void setUnlockedStatus(int levelNumber, boolean unlockStatus) {
        ((JSONObject) progress.get(levelNumber)).put("unlocked", unlockStatus);
    }

    /**
     * Method gets the unlocked status for a specified level.
     * 
     * @param levelNumber the number of the level to set the unlocked status for
     * @return the unlocked status of the specified level
     */
    public boolean getUnlockedStatus(int levelNumber) {
        return (Boolean) ((JSONObject) progress.get(levelNumber)).get("unlocked");
    }

    /**
     * Method sets the current level status for a specified level.
     * 
     * @param levelNumber the number of the level to get the current level status for
     * @param currentLevelStatus the current level status to set the level to
     */
    //Setter method to change the current level status of a level.
    public void setCurrentLevelStatus(int levelNumber, boolean currentLevelStatus) {
        ((JSONObject) progress.get(levelNumber)).put("currentLevel", currentLevelStatus);
    }

    /**
     * Method gets the current level status for a specified level.
     * 
     * @param levelNumber the number of the level to set the current level status for
     * @return the current level status of the specified level
     */
    public boolean getCurrentLevelStatus(int levelNumber) {
        return (Boolean) ((JSONObject) progress.get(levelNumber)).get("currentLevel");
    }
}