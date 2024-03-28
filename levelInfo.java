import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class levelInfo extends JFrame implements ActionListener {
    private User user;
    private JButton next;
    private JLabel title;
    private JTextArea info;
    private String level;
    private String text;

    public levelInfo(User user){
        super("Cosmic Quest: Stellar Treasures");
        this.user = user;

        try{
            File levelInfoFile= new File("lessons.json");
            JSONArray levels;
            if (levelInfoFile.exists()) {
                levels = (JSONArray) new JSONParser().parse(new FileReader("lessons.json")); //issue?
                JSONObject userLevelInfo = (JSONObject) levels.get(user.getLevel());
                level = (String) userLevelInfo.get("level");
                text = (String) userLevelInfo.get("info");
            }
            else {
                levels = new JSONArray();
                FileWriter fw = new FileWriter("lessons.json");
                fw.write(levels.toJSONString());
                fw.close();
                level = "no level found";
                text = "no info";
            }

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);

        ImageIcon backgroundImage = new ImageIcon(getClass().getResource("images/menuBackground.jpg"));
        JLabel background = new JLabel(backgroundImage);
        background.setLayout(new BorderLayout());

        next = new JButton("Next");
        title = new JLabel();

        title.setText(level);
        title.setFont(new Font(null, Font.PLAIN, 40));
        title.setForeground(Color.white);

        info = new JTextArea(10, 20);
        info.setFont(new Font(null, Font.PLAIN, 20));
        info.setText(text);
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
        User user = new User("will", "g");
        levelInfo level = new levelInfo(user);
    }
}