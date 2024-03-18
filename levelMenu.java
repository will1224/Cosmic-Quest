import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class levelMenu {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Solar System Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout()); // Use BorderLayout for main frame layout

        // Main panel for spherical buttons
        JPanel panel = new JPanel(new GridLayout(3, 4, 10, 10));
        panel.setBackground(Color.BLACK); // Set the background color for contrast
        String[] levels = { "Sun", "Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune",
                "Nebulas", "Black Holes" };

        for (String level : levels) {
            JButton button = new JButton(level) {
                @Override
                protected void paintComponent(Graphics g) {
                    if (getModel().isArmed()) {
                        g.setColor(Color.LIGHT_GRAY);
                    } else {
                        g.setColor(getBackground());
                    }
                    g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);
                    super.paintComponent(g);
                }

                @Override
                protected void paintBorder(Graphics g) {
                    g.setColor(getForeground());
                    g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
                }

                @Override
                public void setContentAreaFilled(boolean b) {
                }
            };
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(level + " button pressed");
                }
            });
            button.setFocusPainted(false);
            button.setBorderPainted(false);
            button.setContentAreaFilled(false);
            button.setOpaque(false);
            panel.add(button);
        }

        // Separate panel for the Back button to position it at the bottom left
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Back button pressed");
            }
        });
        backPanel.add(backButton);

        // Add the main panel and back button panel to the frame
        frame.add(panel, BorderLayout.CENTER);
        frame.add(backPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
