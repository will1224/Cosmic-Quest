public class User {
    private String username;
    private String password;
    private int level; // user's CURRENT level

    public User(String username, String password) {
        level = 2; // temp hardcoded
    }

    public int getLevel() {
        return level;
    }

}
