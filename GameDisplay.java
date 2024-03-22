import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GameDisplay extends JFrame {

    // constructor
    public GameDisplay() {
        // set title
        setTitle("Cosmic Quest: Stellar Treasures");

        // set default
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set window size
        setSize(1920, 1080);

        // make window visible
        setVisible(true);
    }

    public void displayLevel(Level currLevel, List<Question> questionSet) {
        // create JPanel with grid
        JPanel panel = new JPanel(new GridLayout(7, 3, 10, 10)); // 3x3 grid with 10px padding

        // fill grid
        for (int i = 0; i < 21; i++) {
            switch(i) {
                case 1:
                    JLabel title = new JLabel(currLevel.getName());
                    title.setFont(new Font("Space Mono", Font.BOLD, 30));
                    title.setHorizontalAlignment(JLabel.CENTER);
                    panel.add(title);
                    break;
                case 4:
                    JLabel questionText = new JLabel(questionSet.get(0).getQuestionText());
                    questionText.setFont(new Font("Space Mono", Font.PLAIN, 30));
                    questionText.setHorizontalAlignment(JLabel.CENTER);
                    questionText.setVerticalAlignment(JLabel.NORTH);
                    panel.add(questionText);
                    break;
                case 7:
                    JButton optionA = new JButton(questionSet.get(0).getAnswers().get(0));
                    optionA.setFont(new Font("Space Mono", Font.PLAIN, 25));
                    panel.add(optionA);
                    break;
                case 10:
                    JButton optionB = new JButton(questionSet.get(0).getAnswers().get(1));
                    optionB.setFont(new Font("Space Mono", Font.PLAIN, 25));
                    panel.add(optionB);
                    break;
                case 13:
                    JButton optionC = new JButton(questionSet.get(0).getAnswers().get(2));
                    optionC.setFont(new Font("Space Mono", Font.PLAIN, 25));
                    panel.add(optionC);
                    break;
                case 16:
                    JButton optionD = new JButton(questionSet.get(0).getAnswers().get(3));
                    optionD.setFont(new Font("Space Mono", Font.PLAIN, 25));
                    panel.add(optionD);
                    break;
                default:
                    panel.add(new JPanel()); // fill w JButtons so I can see grid clearly lol
            }
        }

        // make visible
        add(panel);
        revalidate();
        repaint();

    }


}
