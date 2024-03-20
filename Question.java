import java.util.*;
import org.json.simple.*;


public class Question {

    /* instance variables */
    private String questionText;
    private List<String> answers;
    private int correctAnswerIndex;

    /* makes a question */
    public Question(String questionText, List<String> answers) {
        this.questionText = questionText;
        this.answers = answers;
    }

    /* checks validity of answer */
    public boolean isCorrectAnswer(int answerIndex) {
        return false;
    }

    /* parses json file for question */
    public String getQuestionText() {
        return "bruh";
    }

    /* returns answer */
    public List<String> getAnswers() {
        
    }


}
