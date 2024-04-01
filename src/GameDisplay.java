package src;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;


public class GameDisplay extends JFrame {
    //for questions
    private JButton[] optionButtons = new JButton[4];
    private int selectedAnswerIndex; // Track the selected answer
    private List<Question> questions; // List of questions for the current level
    private int currentQuestionIndex; // Index of the current question in the list
    private Level currentLevel;
    private Image scaledBackgroundImage;
    private JPanel panel;
    private int gameState; // 0 = question mode
    private int tempScore;
    private Accounts accounts;
    private int levelID;

    //for lesson
    private JLabel levelName;
    private JTextArea lesson;
    private JButton next;
    private boolean nextPressed;
    private JButton backButton;

    // colour constants
    private Color PINK = new Color(255, 104, 176);
    private Color GREEN = new Color(0, 180, 119);
    private boolean isSingleLevel;


    // constructor
    public GameDisplay(Accounts a) {
        setTitle("Cosmic Quest: Stellar Treasures");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);

        // load the background image
        try {
            BufferedImage backgroundImage = ImageIO.read(new File("images/gradient.png"));
            BackgroundPanel bg = new BackgroundPanel(backgroundImage);
            setContentPane(bg);
            setLayout(new BorderLayout()); // use BorderLayout
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ensure panel is transparent to show the background image
        this.panel = new JPanel();
        panel.setOpaque(false); // make panel transparent
        add(panel, BorderLayout.CENTER); // add the panel to the center

        panel.setVisible(true);
        tempScore = 0;
        accounts = a;

        backButton = new JButton("Back");
        backButton.setFont(new Font("Space Mono", Font.PLAIN, 20));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Create a panel at the bottom for the Back button
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(backButton, BorderLayout.WEST); // Place the Back button on the west
        bottomPanel.setOpaque(false); // Optional: make the panel transparent

        // Add the bottom panel to the main frame
        add(bottomPanel, BorderLayout.SOUTH);


        setVisible(true);
    }


    public void displayLesson() {
        panel.removeAll();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Title
        JLabel levelName = new JLabel(currentLevel.getName(), SwingConstants.CENTER);
        levelName.setFont(new Font("Space Mono", Font.BOLD, 30));
        levelName.setForeground(Color.PINK);
        panel.add(levelName, BorderLayout.NORTH);

        // Textarea in the center
        JTextArea lessonText = new JTextArea(currentLevel.getLesson());
        lessonText.setFont(new Font("Space Mono", Font.PLAIN, 20));
        lessonText.setLineWrap(true);
        lessonText.setWrapStyleWord(true);
        lessonText.setEditable(false);
        lessonText.setForeground(Color.white);
        lessonText.setOpaque(false);

        JScrollPane scrollPane = new JScrollPane(lessonText);
        scrollPane.setPreferredSize(new Dimension(800, 700)); // control text area dimensions
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);

        JPanel scrollPanePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        scrollPanePanel.add(scrollPane);
        scrollPanePanel.setOpaque(false);
        panel.add(scrollPanePanel, BorderLayout.CENTER);

        // Next button
        JButton nextButton = new JButton("Next");
        nextButton.setPreferredSize(new Dimension(200, 40)); // control button dimensions
        nextButton.setFont(new Font("Space Mono", Font.PLAIN, 20));
        nextButton.addActionListener(e -> displayQuestion(currentLevel));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(nextButton);
        buttonPanel.setOpaque(false);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        panel.revalidate();
        panel.repaint();
    }

    public void displayLessonSingle() {
        panel.removeAll();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Title
        JLabel levelName = new JLabel(currentLevel.getName(), SwingConstants.CENTER);
        levelName.setFont(new Font("Space Mono", Font.BOLD, 30));
        levelName.setForeground(Color.PINK);
        panel.add(levelName, BorderLayout.NORTH);

        // Textarea in the center
        JTextArea lessonText = new JTextArea(currentLevel.getLesson());
        lessonText.setFont(new Font("Space Mono", Font.PLAIN, 20));
        lessonText.setLineWrap(true);
        lessonText.setWrapStyleWord(true);
        lessonText.setEditable(false);
        lessonText.setForeground(Color.white);
        lessonText.setOpaque(false);

        JScrollPane scrollPane = new JScrollPane(lessonText);
        scrollPane.setPreferredSize(new Dimension(800, 700)); // control text area dimensions
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);

        JPanel scrollPanePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        scrollPanePanel.add(scrollPane);
        scrollPanePanel.setOpaque(false);
        panel.add(scrollPanePanel, BorderLayout.CENTER);

        // Next button
        JButton nextButton = new JButton("Next");
        nextButton.setPreferredSize(new Dimension(200, 40)); // control button dimensions
        nextButton.setFont(new Font("Space Mono", Font.PLAIN, 20));
        nextButton.addActionListener(e -> displayQuestionSingle(currentLevel));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(nextButton);
        buttonPanel.setOpaque(false);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        panel.revalidate();
        panel.repaint();
    }


//    public void displayLevel(Level currLevel, List<Question> questionSet) {
//
//        if (questionSet.isEmpty()) return;
//
//        this.currentLevel = currLevel;
//        this.questions = questionSet; // store the list of questions
//        this.currentQuestionIndex = 0; // start from the first question
//        this.selectedAnswerIndex = -1; // set default for no selection
//        tempScore = 0;
//
//        displayLesson();
//    }

    public void displayLevel(Level currLevel, List<Question> questionSet, boolean fromLevelSelect) {

        if (questionSet.isEmpty()) return;
        this.isSingleLevel = fromLevelSelect;
        this.currentLevel = currLevel;
        this.questions = questionSet; // store the list of questions
        this.currentQuestionIndex = 0; // start from the first question
        this.selectedAnswerIndex = -1; // set default for no selection
        tempScore = 0;
        if (fromLevelSelect){
            displayLessonSingle();
        }else {
            displayLesson();
        }
    }

    private void displayQuestionSingle(Level currLevel) {
        gameState = 0;

        if (currentQuestionIndex >= questions.size()) {
            if(tempScore != 5) {
                JOptionPane.showMessageDialog(this, "You have not answered all questions right, please try again", "End of Questions", JOptionPane.INFORMATION_MESSAGE);
//                LevelProgress progress = new LevelProgress((JSONArray) accounts.getCurrentAccount().get("progress"));
//                if(tempScore > progress.getLevelScore(progress.getCurrentLevel())) {
//                    updateScore(tempScore);
//                }
//                tempScore = 0;
                questions = currLevel.getQuestions();
                displayLevel(currLevel, questions, true);
            }else{
                JOptionPane.showMessageDialog(this, "You've completed all the questions!", "End of Questions", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                return;
            }
        }else {
            Question currentQuestion = questions.get(currentQuestionIndex);

            panel.removeAll(); // clear the panel for the new question
            panel.setLayout(new GridLayout(7, 1, 10, 10)); // reset layout

            addComponentsToPanel(currLevel);

            revalidate();
            repaint();
        }
    }

    private void displayQuestion(Level currLevel) {
        gameState = 0;

        if (currentQuestionIndex >= questions.size()) {
            if(tempScore != 5) {
                JOptionPane.showMessageDialog(this, "You have not answered all questions right, please try again", "End of Questions", JOptionPane.INFORMATION_MESSAGE);
                LevelProgress progress = new LevelProgress((JSONArray) accounts.getCurrentAccount().get("progress"));
                if(tempScore > progress.getLevelScore(progress.getCurrentLevel())) {
                    updateScore(tempScore);
                }
                tempScore = 0;
                questions = currLevel.getQuestions();
                displayLevel(currLevel, questions, false);
            }else{
                JOptionPane.showMessageDialog(this, "You've completed all the questions!", "End of Questions", JOptionPane.INFORMATION_MESSAGE);
                updateScore(5);
                LevelProgress progress = new LevelProgress((JSONArray) accounts.getCurrentAccount().get("progress"));
                progress.setUnlockedStatus(progress.getCurrentLevel()+1, true);
                progress.setCurrentLevelStatus((progress.getCurrentLevel() + 1), true);
                progress.setCurrentLevelStatus(progress.getCurrentLevel(), false);
                accounts.updateUserProgress(accounts.getCurrentAccount().get("username").toString(), progress.getProgress());
                currLevel = new Level(currLevel.getLevelID()+1);
                displayLevel(currLevel, currLevel.getQuestions(), false);
            }
        }else {
            Question currentQuestion = questions.get(currentQuestionIndex);

            panel.removeAll(); // clear the panel for the new question
            panel.setLayout(new GridLayout(7, 1, 10, 10)); // reset layout

            addComponentsToPanel(currLevel);

            revalidate();
            repaint();
        }
    }

    private void displayNext() {
        if (selectedAnswerIndex == -1) {
            JOptionPane.showMessageDialog(this, "Please select an answer before proceeding.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        currentQuestionIndex++; // move to the next question
        if(isSingleLevel){
            displayQuestionSingle(currentLevel);
        }else {
            displayQuestion(currentLevel);
        }
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
        title.setForeground(PINK);
        panel.add(title);
    }

    private void addQuestionToPanel(String questionText) {
        JLabel questionLabel = new JLabel(questionText, JLabel.CENTER);
        questionLabel.setFont(new Font("Space Mono", Font.PLAIN, 20));
        questionLabel.setForeground(Color.white);
        panel.add(questionLabel);
    }

    private void addOptionToPanel(String buttonText, int index) {
        if (index < 0 || index >= optionButtons.length) {
            throw new IllegalArgumentException("Index out of bounds for option buttons array.");
        }

        JButton button = new JButton(buttonText);
        button.setFont(new Font("Space Mono", Font.PLAIN, 20));

        button.addActionListener(e -> {
            if (gameState == 0) {
                System.out.println("Clicked");
                selectedAnswerIndex = index;
                updateSelected(); // show selected answer
            }
        });

        selectedAnswerIndex = -1;

        optionButtons[index] = button; // store the button in the array
        panel.add(button);
    }

    private void addNextButtonToPanel() {
        JButton nextBtn = new JButton("Next");
        nextBtn.setFont(new Font("Space Mono", Font.PLAIN, 20));
        nextBtn.addActionListener(this::nextButtonAction);
        JPanel btnContainer = new JPanel();
        btnContainer.setOpaque(false);
        btnContainer.add(nextBtn);
        panel.add(btnContainer);
    }

    private void nextButtonAction(ActionEvent e) {
        Question currentQuestion = questions.get(currentQuestionIndex);

        System.out.println(selectedAnswerIndex);

        if (gameState == 0) {
            if (selectedAnswerIndex == -1) {
                JOptionPane.showMessageDialog(this, "Please select an answer.", "No Selection", JOptionPane.WARNING_MESSAGE);
            } else {
                verifyAns(currentQuestion, selectedAnswerIndex);
            }
        } else if (gameState == 1) {
            displayNext(); // Move to the next question or finish
        }
    }

    private void updateSelected() {
        for (int i = 0; i < optionButtons.length; i++) {
            JButton button = optionButtons[i];
            if (button == null) continue;

            if (i == selectedAnswerIndex) {
                button.setForeground(Color.BLUE);
            } else {
                button.setForeground(Color.BLACK); // Use a standard color instead of resetting to default
            }
        }
    }

    // go through each button and check if correct answer
    private void verifyAns(Question question, int answerIndex) {

        gameState = 1;

        for (int i = 0; i < optionButtons.length; i++) {
            JButton button = optionButtons[i];
            if (button == null) continue; // Skip if the button hasn't been initialized yet

            // change colour for correct answer
            if (i == selectedAnswerIndex && (question.isCorrectAnswer(selectedAnswerIndex))) {
                // correct
                button.setForeground(GREEN);

                tempScore++;
//                System.out.println("Score update: " + tempScore);
//                updateScore(tempScore);
            } else if (i == selectedAnswerIndex) {
                button.setForeground(Color.BLUE);
            } else if ((question.isCorrectAnswer(i))) {
                // wrong answer: show correct
                button.setForeground(PINK);
            } else {
                // default button
                button.setBackground(UIManager.getColor("Button.background")); // Default button color
                button.setForeground(UIManager.getColor("Button.foreground")); // Default text color
            }
        }
    }

    
    private void updateScore(int score) {
        JSONObject currentAccount = accounts.getCurrentAccount();

        //Get the current score.
        LevelProgress progress = new LevelProgress((JSONArray) currentAccount.get("progress"));
        progress.setLevelScore(currentLevel.getLevelID(), score);

        //Uncomment the following line if using the instance variable instead of the parameter.
        //progress.setLevelScore(currentLevel.getLevelID(), tempScore);

        //Update the progress for the user's account.
        accounts.updateUserProgress((String) currentAccount.get("username"), progress.getProgress());
    }
}