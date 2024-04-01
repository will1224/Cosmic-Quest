package src;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.*;


/**
 * Class creates a level and controls the logic and questions provided.
 * Grabs stored questions from the <b>question.json</b> file.
 * 
 * @version 1.0
 * @author Jennifer Cao
 */
public class Level {
    /**The ID of the level. Uniquely identifies each level in increasing order from 0-9 inclusive.*/
    private int levelID;
    /**The name of the level.*/
    private String name;
    /**The list of questions that will be provided to the level.*/
    private List<Question> questionList;

    /**
     * Constructor assigns a levelID, question list, and a name to the level.
     * 
     * @param levelID the number that uniquely identifies a level
     * @see readQuestions
     */
    public Level(int levelID) {
        String[] PLANET_NAMES = {"Sun", "Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune", "Other Celestial Bodies"};

        this.levelID = levelID;
        this.questionList = readQuestions();
        this.name = PLANET_NAMES[levelID];
    }

    /**
     * Method makes five question objects, ensuring randomization and no repeats, and stores them in a list.
     * 
     * @return a list of five questions
     * @see parseQuestions
     */
    private List<Question> readQuestions() {
        String FILE = "src/question.json";

        try {
            /**make json string from file*/
            String jsonText = new String(Files.readAllBytes(Paths.get(FILE)));

            /**parse json text and make question objects*/
            List<Question> questionList = parseQuestions(jsonText);

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

    /**
     * Method parses a JSON string to extract a list of questions.
     * 
     * @param jsonText the JSON string to convert into a list of question objects.
     * @return a list of questions
     * @throws ParseException if an error occurs while parsing the JSON string
     */
    private List<Question> parseQuestions(String jsonText) throws ParseException {
        /**Initialize an empty list to hold the Question objects*/
        List<Question> questions = new ArrayList<>();
        /**Create a new JSONParser object for parsing the JSON string*/
        JSONParser parser = new JSONParser();

        try {
            /**Parse the JSON string into a generic Object*/
            Object obj = parser.parse(jsonText);
            /**Cast the Object to a JSONArray as we expect a list of questions*/
            JSONArray jsonArray = (JSONArray) obj;

            /**Iterate over each level question in the JSONArray*/
            for (int i = levelID * 7; i < levelID * 7 + 6; i++) {
                /**cast the element to a JSONObject representing a single question*/
                JSONObject questionJson = (JSONObject) jsonArray.get(i);
                /**extract the question text as a String*/
                String questionStr = (String) questionJson.get("questionStr");
                /**extract the index of the correct answer as a long*/
                long answer = (long) questionJson.get("answer");
                /**extract the list of possible answers as a JSONArray*/
                JSONArray answersJson = (JSONArray) questionJson.get("answerList");
                /**initialize an empty list to hold the answer strings*/
                List<String> answers = new ArrayList<>();
                /**iterate over each answer in the JSONArray*/
                for (Object answerObj : answersJson) {
                    /**add each answer to the list as a String*/
                    answers.add((String) answerObj);
                }
                /**create a new question object with the extracted data and add it to the list*/
                questions.add(new Question(questionStr, answers, (int) answer));
            }
        } catch (ParseException e) {
            throw new ParseException(ParseException.ERROR_UNEXPECTED_EXCEPTION, e);
        }

        /**return list of question objects*/
        return questions;
    }

    /**
     * Method returns a randomized list of five questions from the list of questions.
     * 
     * @return a randomized list containing five questions
     */
    public List<Question> getQuestions() {
        /**Ensure the questionList is not null and has questions*/
        if (questionList == null || questionList.isEmpty()) {
            System.out.println("The question list is empty or not initialized.");
            return Collections.emptyList(); // Return an empty list to avoid null pointer exceptions
        }

        /**Shuffle the list to randomize the questions*/
        Collections.shuffle(questionList);

        /**Check if the list contains at least 5 questions*/
        if (questionList.size() < 5) {
            // If not, handle accordingly. For now, we're just printing a message and returning the shuffled list as is.
            System.out.println("Warning: Less than 5 questions available. Returning all available questions.");
            return questionList;
        }

        /**If there are at least 5 questions, return the first 5 from the shuffled list*/
        return questionList.subList(0, 5);
    }

    /**
     * Getter method that returns the name of the level.
     * 
     * @return the name of the level
     */
    public String getName() {
        return name;
    }

    public int getLevelID() {
        return levelID;
    }

    public String getLesson() {
        String file = "src/lessons.json";
        try {
            Object obj = new JSONParser().parse(new FileReader(file));
            JSONObject jsonObj = (JSONObject) obj;
            JSONArray lessons = (JSONArray) jsonObj.get("lessons");

            for (Object element : lessons) {
                JSONObject lesson = (JSONObject) element;
                String levelNum = Integer.toString(levelID);
                if (lesson.get("levelId").toString().equals(levelNum)) {
                    return (String) lesson.get("info");
                }
            }
        } catch(IOException | ParseException e){
            throw new RuntimeException(e);
        }
        return null;
    }

}