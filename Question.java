import java.util.*;

/**
 * Class contains logic related to question implementation.
 */
public class Question {
    /**The text of the question.*/
    private String questionText;
    /**The list of possible answers.*/
    private List<String> answers;
    /**The index of the correct answer within the list.*/
    private int correctAnsIndex;

    /**
     * Constructor loads the instance variables with the question text, the list of answers, and the index of the correct answer.
     * 
     * @param questionText the text of the question
     * @param answers the list of possible answers to the question
     * @param correctAnswerIndex the index of the correct answer
     */
    public Question(String questionText, List<String> answers, int correctAnswerIndex) {
        this.questionText = questionText;
        this.answers = answers;
        this.correctAnsIndex = correctAnswerIndex;
    }

    /**
     * Method determines whether or not the answer chosen is correct based on whether or not the chosen index matches the index of the correct answer.
     * 
     * @param answerIndex the chosen index to be compared to the correct index
     * @return true if the answer is correct, false otherwise
     */
    public boolean isCorrectAnswer(int answerIndex) {
        return (answerIndex == correctAnsIndex);
    }

    /**
     * Getter method returns the correct answer's index.
     * 
     * @return the index of the correct answer
     */
    public int getCorrectAnsIndex() {
        return correctAnsIndex;
    }

    /**
     * Getter method returns the correct answer's text.
     * 
     * @return the text of the correct answer
     */
    public String getCorrectAnsText() {
        return answers.get(correctAnsIndex);
    }

    /**
     * Getter method returns the question's text.
     * 
     * @return the text of the question
     */
    public String getQuestionText() {
        return questionText;
    }

    /**
     * Getter method returns the list of answers.
     * 
     * @return the list of answers
     */
    public List<String> getAnswers() {
        return answers;
    }
}