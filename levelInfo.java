import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class levelInfo extends JFrame implements ActionListener {
    private User user;
    private JButton next;
    private JLabel title;
    private JTextArea info;

    public levelInfo(User user){
        super("Cosmic Quest: Stellar Treasures");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);

        ImageIcon backgroundImage = new ImageIcon(getClass().getResource("images/menuBackground.jpg"));
        JLabel background = new JLabel(backgroundImage);
        background.setLayout(new BorderLayout());

        next = new JButton("Next");
        title = new JLabel();

        title.setText("whatever planet we are on ");
        title.setFont(new Font(null, Font.PLAIN, 40));
        title.setForeground(Color.white);

        info = new JTextArea(10, 20);
        info.setFont(new Font(null, Font.PLAIN, 20));
        info.setText("read from JSON file");
        info.setEditable(false);

        background.setLayout(null);

        next.setBounds(810, 900, 300, 80);
        title.setBounds(510, 50, 900, 50);
        info.setBounds(510, 120, 900, 750);

        background.add(next);
        background.add(title);
        background.add(info);

        setContentPane(background);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == next){
             GameControl questions = new GameControl(user); 
             questions.startGame(); //move to questions screen
             dispose();
        }
    }

    public static void main(String[] args) {
        levelInfo level = new levelInfo(new User("will", "g"));
    }
}