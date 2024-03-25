import java.util.List;

public class GameControl {
        User currUser;
        Level currLevel;
    public GameControl(User currUser) {
        this.currUser = currUser;
        this.currLevel = currLevel;
    }

    // starts the game
    public void startGame() {

        // make level
        currLevel = new Level(currUser.getLevel());
        List<Question> questionSet = currLevel.getQuestions();

        GameDisplay d = new GameDisplay();
        d.displayLevel(currLevel, questionSet);

    }

    public static void main(String[] args) {
        GameControl t = new GameControl(new User("bruh", "dawg"));
        t.startGame();

    }


}
