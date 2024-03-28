import static org.junit.Assert.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.*;

public class TestAccounts {
    private static Accounts accounts;
    private static JSONArray restore;

    public TestAccounts() {

    }

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
        assertEquals(true, accounts.registerAccount("testing", "abc123"));
        //Case 2: Attempting to register an account that already exists.
        assertEquals(false, accounts.registerAccount("testing", "def456"));
    }

    @Test
    public void testAccountExists() {
        //Case 1: Account exists
        assertEquals(true, accounts.accountExists("testing"));
        //Case 2: Account does not exist
        assertEquals(false, accounts.accountExists("test-ing"));
    }


    @Test
    public void testCorrectPassword() {
        //Case 1: Password is correct
        assertEquals(true, accounts.correctPassword("testing", "abc123"));
        //Case 2: Password is incorrect
        assertEquals(false, accounts.correctPassword("testing", "def456"));
    }

    @Test
    public void testLogin() {
        //Case 1: Test logging into an account that exists.
        assertEquals(true, accounts.login("testing", "abc123"));
        //Case 2: Test logging into an account that does not exist.
        assertEquals(false, accounts.login("random", "password"));
        //Case 3: Test logging into an account with the wrong username.
        assertEquals(false, accounts.login("test-ing", "abc123"));
        //Case 4: Test logging into an account with the wrong password.
        assertEquals(false, accounts.login("testing", "abc456"));
    }
}
