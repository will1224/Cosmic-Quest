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
        BackgroundPanel backgroundPanel = new BackgroundPanel(new ImageIcon("images/background.jpg").getImage());
        backgroundPanel.setLayout(new BorderLayout());
        setContentPane(backgroundPanel); // Set the background panel as the main content pane

        // Title label
        JLabel titleLabel = new JLabel("Cosmic Quest: Stellar Treasures", SwingConstants.CENTER);
        backgroundPanel.add(titleLabel, BorderLayout.NORTH);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false); // Make the panel transparent to show the background

        // Create buttons with images
        resumeButton = createImageButton("images/button.png");
        optionsButton = createImageButton("images/button.png");
        returnButton = createImageButton("images/button.png");
        exitButton = createImageButton("images/button.png");

        // Adding buttons to the panel
        buttonPanel.add(resumeButton);
        buttonPanel.add(optionsButton);
        buttonPanel.add(returnButton);
        buttonPanel.add(exitButton);

        // Adding button panel to the background panel
        backgroundPanel.add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JButton createImageButton(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath); // Load the image icon
        JButton button = new JButton(icon);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(icon.getIconWidth() + 20, icon.getIconHeight() + 20));
        button.addActionListener(this);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Your action handling code remains the same
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

}

