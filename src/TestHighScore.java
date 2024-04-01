package src;
import static org.junit.Assert.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.junit.*;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;


/**
 * Class tests the key logical components in {@link HighScore}.
 * 
 * @version 1.0.0
 * @author Ho Lun (Geoffrey) Kong
 */
public class TestHighScore {
    /**A representation of the information within <b>accountsdata.json</b>.*/
    private static Accounts accounts;
    /**A backup of all the current information within <b>accountsdata.json</b>.*/
    private static JSONArray restore;

    @Test
    /**
     * Tests if {@link src.HighScore#getScoresFromDatabase()} works as intended.
     * 
     * @see src.HighScore#getScoresFromDatabase()
     */
    public void testGetScoresFromDatabase() {
        //Backup current list of accounts.
        accounts = new Accounts();
        restore = accounts.getAccounts();
        try {
            //Overwrite data file to run tests.
            FileWriter fw = new FileWriter("accountsdata.json");
            fw.write(new JSONArray().toJSONString());
            fw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        //Keep a copy of the blank data file on record within the testing class.
        accounts = new Accounts();
        //Register 3 accounts.
        accounts.registerAccount("test-ing123", "abc123");
        accounts.registerAccount("test-ing456", "def456");
        accounts.registerAccount("test-ing789", "ghi789");

        //Create new progress.
        LevelProgress progress1 = new LevelProgress();
        LevelProgress progress2 = new LevelProgress();
        LevelProgress progress3 = new LevelProgress();

        //Update level scores.
        progress1.setLevelScore(0, 5);
        progress1.setLevelScore(6,1);

        progress2.setLevelScore(2, 4);
        progress2.setLevelScore(7, 3);

        progress3.setLevelScore(1, 4);
        progress3.setLevelScore(4, 4);

        accounts.updateUserProgress("test-ing123", progress1.getProgress());
        accounts.updateUserProgress("test-ing456", progress2.getProgress());
        accounts.updateUserProgress("test-ing789", progress3.getProgress());

        try {
            HighScore hs = new HighScore();
            List<PlayerScore> lp = hs.getScoresFromDatabase();
            assertEquals("test-ing123", lp.get(0).getPlayerName());
            assertEquals(6, lp.get(0).getScore());
            assertEquals("test-ing456", lp.get(1).getPlayerName());
            assertEquals(7, lp.get(1).getScore());
            assertEquals("test-ing789", lp.get(2).getPlayerName());
            assertEquals(8, lp.get(2).getScore());
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        //Restore the previously stored data in the testing file.
        try {
            FileWriter fw = new FileWriter("accountsdata.json");
            fw.write(restore.toJSONString());
            fw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}