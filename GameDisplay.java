import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class GameDisplay extends JFrame {

    private int selectedAnswerIndex = -1; // Track the selected answer
    private Question currentQuestion; // Currently displayed question
    private JPanel panel; // Main panel for the game display

    public GameDisplay() {
        setTitle("Cosmic Quest: Stellar Treasures");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setVisible(true);
    }

    public void displayLevel(Level currLevel, List<Question> questionSet) {
        // Assuming there's at least one question in the set
        if (questionSet.isEmpty()) return;
        this.currentQuestion = questionSet.get(0);

        this.panel = new JPanel(new GridLayout(7, 3, 10, 10));
        addComponentsToPanel(currLevel);
        add(panel);
        revalidate();
        repaint();
    }

    private void addComponentsToPanel(Level currLevel) {
        for (int i = 0; i < 21; i++) {
            switch (i) {
                case 1:
                    addTitleToPanel(currLevel.getName());
                    break;
                case 4:
                    addQuestionToPanel(currentQuestion.getQuestionText());
                    break;
                case 7: case 10: case 13: case 16:
                    addButtonToPanel(currentQuestion.getAnswers().get(i / 3 - 2), i / 3 - 2);
                    break;
                case 20:
                    addNextButtonToPanel();
                    break;
                default:
                    panel.add(new JPanel()); // Filler for empty grid spaces
                    break;
            }
        }
    }

    private void addTitleToPanel(String titleText) {
        JLabel title = new JLabel(titleText, JLabel.CENTER);
        title.setFont(new Font("Space Mono", Font.BOLD, 30));
        panel.add(title);
    }

    private void addQuestionToPanel(String questionText) {
        JLabel questionLabel = new JLabel(questionText, JLabel.CENTER);
        questionLabel.setFont(new Font("Space Mono", Font.PLAIN, 30));
        panel.add(questionLabel);
    }

    private void addButtonToPanel(String buttonText, int index) {
        JButton button = new JButton(buttonText);
        button.setFont(new Font("Space Mono", Font.PLAIN, 25));
        button.addActionListener(e -> selectedAnswerIndex = index);
        panel.add(button);
    }

    private void addNextButtonToPanel() {
        JButton nextBtn = new JButton("Next");
        nextBtn.setFont(new Font("Space Mono", Font.PLAIN, 25));
        nextBtn.addActionListener(this::nextButtonAction);
        JPanel btnContainer = new JPanel();
        btnContainer.add(nextBtn);
        panel.add(btnContainer);
    }

    private void nextButtonAction(ActionEvent e) {
        if (selectedAnswerIndex == -1) {
            JOptionPane.showMessageDialog(this, "Please select an answer.", "No Selection", JOptionPane.WARNING_MESSAGE);
        } else {
            verifyAns(currentQuestion, selectedAnswerIndex);
            // Reset for next question or end the game
            selectedAnswerIndex = -1;
        }
    }

    private void verifyAns(Question question, int answerIndex) {
        System.out.println("dawg");
        // Additional code for handling the verification result
    }

}
