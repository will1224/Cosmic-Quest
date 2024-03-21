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
        setSize(400, 300); // You can adjust the size as needed
        setLocationRelativeTo(null); // Center on screen

        // Title label
        JLabel titleLabel = new JLabel("Cosmic Quest: Stellar Treasures", SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

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

        // Adding button panel to the frame
        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JButton createImageButton(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath); // Load the image icon
        JButton button = new JButton(icon);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Set maximum button size to be reasonable, consider the image size
        button.setMaximumSize(new Dimension(icon.getIconWidth() + 20, icon.getIconHeight() + 20));
        button.addActionListener(this);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == returnButton) {
            dispose(); // Close the pause menu
            new mainMenu(); // Open the main menu
        } else if (e.getSource() == exitButton) {
            dispose(); // Close the application
        }
    }
}

