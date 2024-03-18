import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class levelMenu {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Cosmic Quest: Stellar Treasure");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Adjust the GridLayout for 4 components per row, with sufficient rows for all
        // buttons
        JPanel panel = new JPanel(new GridLayout(0, 4, 10, 10)); // Automatically determine rows, 4 cols
        panel.setBackground(Color.BLACK); // Optional: Set the background color for contrast

        String[] levels = { "The Sun", "Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus",
                "Return to Main Menu", "Neptune", "Nebulas", "Black Holes" };

        for (String level : levels) {
            JButton button = new JButton(level) {
                @Override
                protected void paintComponent(Graphics g) {
                    if (getModel().isArmed()) {
                        g.setColor(Color.LIGHT_GRAY);
                    } else {
                        g.setColor(getBackground());
                    }
                    // Adjust the fillOval size for smaller buttons
                    g.fillOval(1, 1, getSize().width - 5, getSize().height - 5);
                    super.paintComponent(g);
                }

                @Override
                protected void paintBorder(Graphics g) {
                    g.setColor(getForeground());
                    // Adjust the drawOval size for smaller buttons
                    g.drawOval(1, 1, getSize().width - 5, getSize().height - 5);
                }

                @Override
                public Dimension getPreferredSize() {
                    // Return a smaller size for the buttons
                    return new Dimension(50, 50); // Adjust width and height for your needs
                }

                @Override
                public void setContentAreaFilled(boolean b) {
                }
            };
            button.setActionCommand(level);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(e.getActionCommand() + " button pressed");
                }
            });
            button.setFocusPainted(false);
            button.setBorderPainted(false);
            button.setContentAreaFilled(false);
            button.setOpaque(false);
            panel.add(button);
        }

        frame.add(panel);
        frame.setVisible(true);
    }
}

