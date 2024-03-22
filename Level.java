import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.*;

public class Level {
    private int levelID;
    private String name;
    private String description;
    private List<Question> questionList;


    // constructor
    public Level(int levelID) {
        this.levelID = levelID;
        this.questionList = readQuestions();

        // set name and description based on levelID
        switch (levelID) {
            case 0:
                this.name = "Sun";
                this.description ="temp";
                break;
            case 1:
                this.name = "Mercury";
                this.description ="temp";
                break;
            case 2:
                this.name = "Venus";
                this.description ="temp";
                break;
            case 3:
                this.name = "Earth";
                this.description ="temp";
                break;
            case 4:
                this.name = "Mars";
                this.description ="temp";
                break;
            case 5:
                this.name = "Jupiter";
                this.description ="temp";
                break;
            case 6:
                this.name = "Saturn";
                this.description ="temp";
                break;
            case 7:
                this.name = "Uranus";
                this.description ="temp";
                break;
            case 8:
                this.name = "Neptune";
                this.description ="temp";
                break;
        }
    }

    private List<Question> readQuestions() {

        // make 5 question objects
        // randomize each one and make sure of no repeats
        // store in list

        String FILE = "question.json";

        try {
            // make json string from file
            String jsonText = new String(Files.readAllBytes(Paths.get(FILE)));

            // parse json text and make question objects
            List<Question> questionList = parseQuestions(jsonText);

            // print every question FOR TESTING

            for (int i = 0; i < questionList.size(); i++) {
                System.out.println("Question: " + questionList.get(i).getQuestionText());
                System.out.println("Answers: " + questionList.get(i).getAnswers());
                System.out.println("Correct: " + questionList.get(i).getCorrectAnsText());
            }

            return questionList;


        } catch (IOException e) {
            System.out.println("File reading error");
            e.printStackTrace();
        } catch (ParseException e) {
            System.out.println("JSON parsing error");
            e.printStackTrace();
        }

        return null;

    }

    // parses json file and returns list of questions
    // Method to parse a JSON string into a list of Question objects
    private List<Question> parseQuestions(String jsonText) throws ParseException {
        // Initialize an empty list to hold the Question objects
        List<Question> questions = new ArrayList<>();
        // Create a new JSONParser object for parsing the JSON string
        JSONParser parser = new JSONParser();

        try {
            // Parse the JSON string into a generic Object
            Object obj = parser.parse(jsonText);
            // Cast the Object to a JSONArray as we expect a list of questions
            JSONArray jsonArray = (JSONArray) obj;

            // Iterate over each level question in the JSONArray
            for (int i = levelID * 7; i < levelID * 7 + 6; i++) {
                // cast the element to a JSONObject representing a single question
                JSONObject questionJson = (JSONObject) jsonArray.get(i);
                // extract the question text as a String
                String questionStr = (String) questionJson.get("questionStr");
                // extract the index of the correct answer as a long
                long answer = (long) questionJson.get("answer");
                // extract the list of possible answers as a JSONArray
                JSONArray answersJson = (JSONArray) questionJson.get("answerList");
                // initialize an empty list to hold the answer strings
                List<String> answers = new ArrayList<>();
                // iterate over each answer in the JSONArray
                for (Object answerObj : answersJson) {
                    // add each answer to the list as a String
                    answers.add((String) answerObj);
                }
                // create a new question object with the extracted data and add it to the list
                questions.add(new Question(questionStr, answers, (int) answer));
            }
        } catch (ParseException e) {
            throw new ParseException(ParseException.ERROR_UNEXPECTED_EXCEPTION, e);
        }

        // return list of question objects
        return questions;
    }

    // return randomized list of 5 questions from the question list
    public List<Question> getQuestions() {
        // First, ensure the questionList is not null and has questions
        if (questionList == null || questionList.isEmpty()) {
            System.out.println("The question list is empty or not initialized.");
            return Collections.emptyList(); // Return an empty list to avoid null pointer exceptions
        }

        // Shuffle the list to randomize the questions
        Collections.shuffle(questionList);

        // Check if the list contains at least 5 questions
        if (questionList.size() < 5) {
            // If not, handle accordingly. For now, we're just printing a message and returning the shuffled list as is.
            System.out.println("Warning: Less than 5 questions available. Returning all available questions.");
            return questionList;
        }

        // If there are at least 5 questions, return the first 5 from the shuffled list
        return questionList.subList(0, 5);
    }

    public String getName() {
        return name;
    }


    /* TESTER */
    public static void main(String[] args) {
        Level testLvl = new Level(0);
    }

}
