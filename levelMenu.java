import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class levelMenu {
    private JFrame frame;

    public levelMenu() {
        frame = new JFrame("Cosmic Quest: Stellar Treasure");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(2880, 1800);

        // Create a panel with overridden paintComponent method to draw the background
        // image
        JPanel backgroundPanel = new JPanel(new GridLayout(0, 4, 10, 10)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load the background image
                ImageIcon backgroundIcon = new ImageIcon("images/menuBackground.jpg");
                Image backgroundImage = backgroundIcon.getImage();

                // Scale image to fit the panel size
                Image scaledImage = backgroundImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
                g.drawImage(scaledImage, 0, 0, this); // Draw the scaled image
            }
        };
        frame.setContentPane(backgroundPanel);

        String[] levels = { "The Sun", "Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus",
                "Return to Main Menu", "Neptune", "Nebulas", "Black Holes" };

        for (String level : levels) {
            JButton button;
            if ("Return to Main Menu".equals(level)) {
                button = createButtonWithImage("images/button.png");
            } else {
                button = createButtonWithImage("images/circleButton.jpg");
            }
            button.setActionCommand(level);
            button.addActionListener(e -> {
                if ("Return to Main Menu".equals(e.getActionCommand())) {
                    frame.dispose();
                    new mainMenu();
                } else {
                    System.out.println(e.getActionCommand() + " button pressed");
                }
            });
            backgroundPanel.add(button); // Add buttons to the background panel
        }

        frame.setVisible(true);
    }

    private JButton createButtonWithImage(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        JButton button = new JButton(icon);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        return button;
    }
}

