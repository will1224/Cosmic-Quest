import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class pauseMenu extends JFrame implements ActionListener {
    private JButton resumeButton;
    private JButton optionsButton;
    private JButton returnButton;
    private JButton exitButton;

    public pauseMenu() {
        setTitle("Cosmic Quest: Stellar Treasures");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(1000, 1000); // You can adjust the size as needed
        setLocationRelativeTo(null); // Center on screen

        // Use the custom background panel
        BackgroundPanel backgroundPanel = new BackgroundPanel(new ImageIcon("images/pause.png").getImage());
        backgroundPanel.setLayout(new BorderLayout());
        setContentPane(backgroundPanel); // Set the background panel as the main content pane

        // Button panel for aligning buttons vertically
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false); // Make the panel transparent

        // Add padding around the button panel
        JPanel containerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        containerPanel.setOpaque(false); // Make container panel transparent
        containerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 100)); // Top, Left, Bottom, Right padding

        // Create buttons with images
        resumeButton = createImageButton("images/continue.png");
        optionsButton = createImageButton("images/options.png");
        returnButton = createImageButton("images/menu.png");
        exitButton = createImageButton("images/exit.png");

        // Adding buttons to the panel with 30 pixels space underneath each
        buttonPanel.add(Box.createVerticalStrut(80)); // Space underneath resumeButton
        buttonPanel.add(resumeButton);
        buttonPanel.add(Box.createVerticalStrut(80)); // Space underneath resumeButton

        buttonPanel.add(optionsButton);
        buttonPanel.add(Box.createVerticalStrut(80)); // Space underneath optionsButton

        buttonPanel.add(returnButton);
        buttonPanel.add(Box.createVerticalStrut(80)); // Space underneath returnButton

        buttonPanel.add(exitButton);

        // Add the button panel to the container panel to apply padding
        containerPanel.add(buttonPanel);

        // Adding the container panel to the background panel on the right
        backgroundPanel.add(containerPanel, BorderLayout.EAST);

        setVisible(true);
    }

    private JButton createImageButton(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        JButton button = new JButton(icon);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setMaximumSize(new Dimension(icon.getIconWidth() + 20, icon.getIconHeight() + 20));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(this);
        return button;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resumeButton) {
            this.dispose();  
        } else if (e.getSource() == optionsButton) {
            new OptionsMenu();
        } else if (e.getSource() == returnButton) {
            this.dispose(); // Example: Close the main menu and start a new game
        } else if (e.getSource() == exitButton) {
            this.dispose();
        }
    }

    // Custom JPanel class for drawing the background image
    private static class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(Image image) {
            this.backgroundImage = image;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Draw the image as the background
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

    public static void main(String[] args) {
        // Run the GUI in the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new pauseMenu(); 
            }
        });
    }
}
