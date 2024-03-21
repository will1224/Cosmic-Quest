import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

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
        return answerIndex == correctAnsIndex;
    }

    public int getCorrectAnsIndex() {
        return correctAnsIndex;
    }
    public String getQuestionText() {
        return questionText;
    }

    public List<String> getAnswers() {
        return answers;
    }

    private static List<Question> parseQuestions(String jsonText) throws ParseException {
        List<Question> questions = new ArrayList<>();
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(jsonText);
            JSONArray jsonArray = (JSONArray) obj;
            for (Object questionObj : jsonArray) {
                JSONObject questionJson = (JSONObject) questionObj;
                String questionStr = (String) questionJson.get("questionStr");
                long answer = (long) questionJson.get("answer");
                JSONArray answersJson = (JSONArray) questionJson.get("answerList");
                List<String> answers = new ArrayList<>();
                for (Object answerObj : answersJson) {
                    answers.add((String) answerObj);
                }
                questions.add(new Question(questionStr, answers, (int) answer));
            }
        } catch (ParseException e) {
            throw new ParseException(ParseException.ERROR_UNEXPECTED_EXCEPTION, e);
        }

        return questions;
    }

    public static void main(String[] args) {
        String FILE = "question.json";

        try {
            // make json string from file
            String jsonText = new String(Files.readAllBytes(Paths.get(FILE)));

            // parse json text and make question objects
            List<Question> questions = parseQuestions(jsonText);

            // print every question
            for (Question question : questions) {
                System.out.println("Question: " + question.getQuestionText());
                System.out.println("Answers: " + question.getAnswers());
                System.out.println("Correct answer: " + question.getAnswers().get(question.getCorrectAnsIndex()));
            }
        } catch (IOException e) {
            System.out.println("File reading error");
            e.printStackTrace();
        } catch (ParseException e) {
            System.out.println("JSON parsing error");
            e.printStackTrace();
        }
    }
}