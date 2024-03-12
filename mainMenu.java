
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class mainMenu implements ActionListener{
    private JFrame menu = new JFrame();
    private JTextField title = new JTextField();
    private JButton newGame = new JButton();
    private JButton continueGame = new JButton();
    private JButton selectLevel = new JButton();
    private JButton scores = new JButton();
    private JButton options = new JButton();
    private JButton exitGame = new JButton();

    public mainMenu(){
        JFrame menu = new JFrame("Cosmic Quest: Stellar Treasures");
        ImageIcon menuBackground = new ImageIcon("C:\\Users\\william\\Project Files(Git)\\Java\\2212repos\\group7\\menuBackground.jpg");
        JLabel backgroundLabel = new JLabel(menuBackground);
        backgroundLabel.setBounds(0, 0, menuBackground.getIconWidth(), menuBackground.getIconHeight());
//        backgroundLabel.setBounds(0, 0, 1280, 720);

        menu.setContentPane(backgroundLabel);

        menu.setLayout(null);

        JLabel title = new JLabel("Cosmic Quest: Stellar Treasures");
        title.setForeground(Color.BLACK);
        title.setFont(new Font(null, Font.BOLD, 24));
        title.setBounds(400, 50, 400, 100);
        menu.add(title);

        JButton newGame = new JButton("New Game");
        newGame.setBounds(400, 200, 200, 50);
        menu.add(newGame);

        JButton continueGame = new JButton("Continue");
        continueGame.setBounds(400, 280, 200, 50);
        menu.add(continueGame);

        JButton selectLevel = new JButton("Select Level");
        selectLevel.setBounds(400, 360, 200, 50);
        menu.add(selectLevel);

        JButton scores = new JButton("Scores");
        scores.setBounds(400, 440, 200, 50);
        menu.add(scores);

        JButton options = new JButton("Options");
        options.setBounds(400, 520, 200, 50);
        menu.add(options);

        JButton exitGame = new JButton("Exit");
        exitGame.setBounds(400, 600, 200, 50);
        menu.add(exitGame);

        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setSize(menuBackground.getIconWidth(), menuBackground.getIconHeight());
//        menu.setSize(1280,720);
        menu.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == newGame){
            //starts new game
            System.out.println("lol");
        }
        else if(e.getSource() == continueGame){
            //resumes game from save file
            System.out.println("lol");
        }
        else if(e.getSource() == selectLevel){
            //allows player to select level to play
            System.out.println("lol");
        }
        else if(e.getSource() == scores){
            //read from file and display scores
            System.out.println("lol");
        }
        else if(e.getSource() == options){
            //opens options
            System.out.println("lol");
        }
        else if(e.getSource() == exitGame){
            //save data
            menu.dispose();
        }
    }
}
