import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class GameDisplay extends JFrame {

    private JButton[] optionButtons = new JButton[4];
    private int selectedAnswerIndex; // Track the selected answer
    private List<Question> questions; // List of questions for the current level
    private int currentQuestionIndex; // Index of the current question in the list
    private Level currentLevel;
    private JPanel panel;
    private int gameState; // 0 = question mode

    public GameDisplay() {
        setTitle("Cosmic Quest: Stellar Treasures");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setVisible(true);

        this.panel = new JPanel();
        add(panel);
        panel.setVisible(true);

    }

    public void displayLevel(Level currLevel, List<Question> questionSet) {
        if (questionSet.isEmpty()) return;

        this.currentLevel = currLevel;
        this.questions = questionSet; // Store the list of questions
        this.currentQuestionIndex = 0; // Start from the first question
        this.selectedAnswerIndex = -1; // set default for no selection
        this.gameState = 0;
        displayQuestion(currLevel); // Display the first question

    }

    private void displayQuestion(Level currLevel) {
        gameState = 0;

        if (currentQuestionIndex >= questions.size()) {
            JOptionPane.showMessageDialog(this, "You've completed all the questions!", "End of Questions", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        Question currentQuestion = questions.get(currentQuestionIndex);

        panel.removeAll(); // Clear the panel for the new question
        panel.setLayout(new GridLayout(7, 1, 10, 10)); // Reset the layout if needed

        addComponentsToPanel(currLevel);

        revalidate();
        repaint();
    }

    private void displayNext() {

        if (selectedAnswerIndex == -1) {
            JOptionPane.showMessageDialog(this, "Please select an answer before proceeding.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        currentQuestionIndex++; // Move to the next question
        displayQuestion(currentLevel);
    }

    private void addComponentsToPanel(Level currLevel) {
        Question currentQuestion = questions.get(currentQuestionIndex);

        // Add level and question text
        addTitleToPanel(currLevel.getName());
        addQuestionToPanel(currentQuestion.getQuestionText());

        for (int i = 0; i < 4; i++) {
            addOptionToPanel(currentQuestion.getAnswers().get(i), i);
        }

        // add next
        addNextButtonToPanel();

    }

    private void addTitleToPanel(String titleText) {
        JLabel title = new JLabel(titleText, JLabel.CENTER);
        title.setFont(new Font("Space Mono", Font.BOLD, 30));
        panel.add(title);
    }

    private void addQuestionToPanel(String questionText) {
        JLabel questionLabel = new JLabel(questionText, JLabel.CENTER);
        questionLabel.setFont(new Font("Space Mono", Font.PLAIN, 20));
        panel.add(questionLabel);
    }

    private void addOptionToPanel(String buttonText, int index) {
        if (index < 0 || index >= optionButtons.length) {
            throw new IllegalArgumentException("Index out of bounds for option buttons array.");
        }

        JButton button = new JButton(buttonText);
        button.setFont(new Font("Space Mono", Font.PLAIN, 20));

        button.addActionListener(e -> {
            selectedAnswerIndex = index;
            updateSelected(); // show selected answer
        });

        optionButtons[index] = button; // Store the button in the array
        panel.add(button);
    }

    private void addNextButtonToPanel() {
        JButton nextBtn = new JButton("Next");
        nextBtn.setFont(new Font("Space Mono", Font.PLAIN, 20));
        nextBtn.addActionListener(this::nextButtonAction);
        JPanel btnContainer = new JPanel();
        btnContainer.add(nextBtn);
        panel.add(btnContainer);
    }

    private void nextButtonAction(ActionEvent e) {
        Question currentQuestion = questions.get(currentQuestionIndex);

        if (gameState == 0) {
            if (selectedAnswerIndex == -1) {
                JOptionPane.showMessageDialog(this, "Please select an answer.", "No Selection", JOptionPane.WARNING_MESSAGE);
            } else {
                verifyAns(currentQuestion, selectedAnswerIndex);
            }
        } else if (gameState == 1) {
            System.out.println("runs");
            displayNext(); // Move to the next question or finish
        }
    }

    private void updateSelected() {
        for (int i = 0; i < optionButtons.length; i++) {
            JButton button = optionButtons[i];
            if (button == null) continue; // Skip if the button hasn't been initialized yet

            if (i == selectedAnswerIndex) {
                button.setForeground(Color.PINK);
            } else {
                // Reset color to default to indicate deselection
                button.setForeground(UIManager.getColor(Color.RED)); // Default text color
            }
        }
    }

    // go through each button and check if correct answer
    private void verifyAns(Question question, int answerIndex) {

        for (int i = 0; i < optionButtons.length; i++) {
            JButton button = optionButtons[i];
            if (button == null) continue; // Skip if the button hasn't been initialized yet

            // change colour for correct answer
            if (i == selectedAnswerIndex && (question.isCorrectAnswer(selectedAnswerIndex))) {
                // correct
                button.setForeground(Color.GREEN);
                System.out.println("Correct answer!");
            } else if (i == selectedAnswerIndex) {
                button.setForeground(Color.PINK);
            } else if ((question.isCorrectAnswer(i))) {
                // wrong answer: show correct
                button.setForeground(Color.WHITE);
                button.setBackground(Color.RED);
                button.setOpaque(true);
            } else {
                // default button
                button.setBackground(UIManager.getColor("Button.background")); // Default button color
                button.setForeground(UIManager.getColor("Button.foreground")); // Default text color
                System.out.println("Incorrect answer.");
            }
        }

        gameState = 1;
    }
}

