import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class GameDisplay extends JFrame {

    private JButton[] optionButtons = new JButton[4];
    private int selectedAnswerIndex = -1; // Track the selected answer
    private List<Question> questions; // List of questions for the current level
    private int currentQuestionIndex; // Index of the current question in the list
    private Level currentLevel;
    private JPanel panel;

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
        displayQuestion(currLevel); // Display the first question

    }

    private void displayQuestion(Level currLevel) {
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
            updateButtonColors();
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

        if (selectedAnswerIndex == -1) {
            JOptionPane.showMessageDialog(this, "Please select an answer.", "No Selection", JOptionPane.WARNING_MESSAGE);
        } else {
            verifyAns(currentQuestion, selectedAnswerIndex);
            // Reset for next question or end the game
            selectedAnswerIndex = -1;
        }
    }

    private void updateButtonColors() {
        for (int i = 0; i < optionButtons.length; i++) {
            JButton button = optionButtons[i];
            if (button == null) continue; // Skip if the button hasn't been initialized yet

            if (i == selectedAnswerIndex) {
                button.setForeground(Color.PINK);
            } else {
                // Reset color to default to indicate deselection
                button.setBackground(UIManager.getColor("Button.background")); // Default button color
                button.setForeground(UIManager.getColor("Button.foreground")); // Default text color
            }
        }
    }

    private void verifyAns(Question question, int answerIndex) {
        if (question.isCorrectAnswer(selectedAnswerIndex)) {
            System.out.println("Correct answer!");
        } else {
            System.out.println("Incorrect answer.");
        }
        displayNext(); // Move to the next question or finish
    }
}

