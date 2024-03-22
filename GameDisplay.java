import javax.swing.*;
import java.awt.*;
import java.util.*;

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

    public void displayLevel(int level) {
        // create JPanel with grid
        JPanel panel = new JPanel(new GridLayout(5, 3, 10, 10)); // 3x3 grid with 10px padding

        // make level
        Level currLevel = new Level(level);

        // fill grid
        for (int i = 0; i < 15; i++) {
            switch(i) {
                case 1:
                    JLabel title = new JLabel("Level " + level);
                    title.setFont(new Font("Space Mono", Font.BOLD, 30));
                    title.setHorizontalAlignment(JLabel.CENTER);
                    panel.add(title);
                    break;
                case 4:
                    JLabel questionText = new JLabel(currLevel.getQTest().getQuestionText());
                    questionText.setFont(new Font("Space Mono", Font.PLAIN, 30));
                    questionText.setHorizontalAlignment(JLabel.CENTER);
                    questionText.setVerticalAlignment(JLabel.NORTH);
                    panel.add(questionText);
                    break;
                default:
                    panel.add(new JButton()); // fill w jbuttons so I can see grid clearly lol
            }
        }

        // make visible
        add(panel);
        revalidate();
        repaint();

    }

    // tester main
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameDisplay d = new GameDisplay();
                d.displayLevel(0);
            }
        });
    }

}
