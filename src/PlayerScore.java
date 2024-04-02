package src;

/**
 * Represents a player's score in the game. This class stores the player's name
 * and their score.
 * It provides methods to retrieve the player's name and score.
 * 
 * @author Jennifer Cao
 * @version 1.0
 */
public class PlayerScore {
    private String playerName;
    private int score;

    /**
     * Constructs a new {@code PlayerScore} object with the specified player name
     * and score.
     *
     * @param playerName The name of the player.
     * @param score      The player's score.
     */
    public PlayerScore(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }

    /**
     * Returns the name of the player.
     *
     * @return The name of the player.
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Returns the score of the player.
     *
     * @return The player's score.
     */
    public int getScore() {
        return score;
    }
}
