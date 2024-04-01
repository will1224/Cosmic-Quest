package src;
import static org.junit.Assert.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.*;


public class TestAccounts {
    private static Accounts accounts;
    private static JSONArray restore;

    @BeforeClass
    public static void setUpClass() {
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
    }

    @AfterClass
    public static void tearDownClass() {
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

    @Test
    public void testRegisterAccount() {
        //Case 1: Registering an account that doesn't exist in the account file.
        assertEquals(true, accounts.registerAccount("testing99", "abc123"));
        //Case 2: Attempting to register an account that already exists.
        assertEquals(false, accounts.registerAccount("testing99", "def456"));
    }

    @Test
    public void testAccountExists() {
        //Case 1: Account exists
        assertEquals(true, accounts.accountExists("testing99"));
        //Case 2: Account does not exist
        assertEquals(false, accounts.accountExists("test-ing"));
    }


    @Test
    public void testCorrectPassword() {
        //Case 1: Password is correct
        assertEquals(true, accounts.correctPassword("testing99", "abc123"));
        //Case 2: Password is incorrect
        assertEquals(false, accounts.correctPassword("testing99", "def456"));
    }

    @Test
    public void testLogin() {
        //Case 1: Test logging into an account that exists.
        assertEquals(true, accounts.login("testing99", "abc123"));
        //Case 2: Test logging into an account that does not exist.
        assertEquals(false, accounts.login("random", "password"));
        //Case 3: Test logging into an account with the wrong username.
        assertEquals(false, accounts.login("test-ing", "abc123"));
        //Case 4: Test logging into an account with the wrong password.
        assertEquals(false, accounts.login("testing99", "abc456"));
    }

    @Test
    public void testGetAccountData() {
        JSONObject aData = accounts.getAccountData("testing99");
        JSONObject fData = accounts.getAccountData("test-ing");
        LevelProgress p = new LevelProgress();
        assertNotEquals(null, aData);
        assertEquals("testing99", aData.get("username"));
        assertEquals("abc123", aData.get("password"));
        assertEquals(p.getProgress().toString(), aData.get("progress").toString());
        assertEquals(null, fData);
    }

    @Test
    public void testUpdateAccountsFile() {
        accounts.registerAccount("testing2", "def456");
        accounts = new Accounts();
        JSONObject newAccount = accounts.getAccountData("testing2");
        LevelProgress p = new LevelProgress();
        assertEquals("testing2", newAccount.get("username"));
        assertEquals("def456", newAccount.get("password"));
        assertEquals(p.getProgress().toString(), newAccount.get("progress").toString());
    }

    @Test
    public void testUpdateUserProgress() {
        LevelProgress p = new LevelProgress((JSONArray) accounts.getAccountData("testing99").get("progress"));
        p.setCurrentLevelStatus(0, false);
        p.setCurrentLevelStatus(1, true);
        p.setUnlockedStatus(1, true);
        accounts.updateUserProgress("testing99", p.getProgress());

        LevelProgress newP = new LevelProgress((JSONArray) accounts.getAccountData("testing99").get("progress"));
        assertEquals(false, newP.getCurrentLevelStatus(0));
        assertEquals(true, newP.getCurrentLevelStatus(1));
        assertEquals(true, newP.getUnlockedStatus(1));
    }

    @Test
    public void testGetPlayerScore() {
        LevelProgress progress = new LevelProgress();
        JSONArray backup = progress.getProgress();

        progress.setLevelScore(0, 5);
        progress.setLevelScore(1, 4);
        progress.setLevelScore(2, 3);
        progress.setLevelScore(3, 2);
        progress.setLevelScore(4, 1);

        accounts.updateUserProgress("testing99", progress.getProgress());

        PlayerScore ps = accounts.getPlayerScore("testing99");
        assertEquals("testing99", ps.getPlayerName());
        assertEquals(15, ps.getScore());

        accounts.updateUserProgress("testing99", (new LevelProgress()).getProgress());
    }

    @Test
    public void testLogout() {
        accounts.logout();
        assertEquals(null, accounts.getCurrentAccount());
    }
}