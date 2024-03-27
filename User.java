public class User {
    private String username;
    private String password;
    private int level; // user's CURRENT level

    public User(String username, String password) {
        level = 1; // temp hardcoded
    }

    public int getLevel() {
        return level;
    }

}
