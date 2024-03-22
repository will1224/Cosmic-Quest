import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.*;

public class Question {
    private String questionText;
    private List<String> answers;
    private int correctAnsIndex;

    public Question(String questionText, List<String> answers, int correctAnswerIndex) {
        this.questionText = questionText;
        this.answers = answers;
        this.correctAnsIndex = correctAnswerIndex;
    }
    public boolean isCorrectAnswer(int answerIndex) {
        return (answerIndex == correctAnsIndex);
    }

    public int getCorrectAnsIndex() {
        return correctAnsIndex;
    }

    // return correct answer text
    public String getCorrectAnsText() {
        return answers.get(correctAnsIndex);
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getAnswers() {
        return answers;
    }
}