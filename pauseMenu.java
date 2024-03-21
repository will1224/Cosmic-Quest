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

        resumeButton = createButton("Resume Game");
        optionsButton = createButton("Options");
        returnButton = createButton("Return to main menu");
        exitButton = createButton("Exit to Desktop");

        // Adding buttons to the panel
        buttonPanel.add(resumeButton);
        buttonPanel.add(optionsButton);
        buttonPanel.add(returnButton);
        buttonPanel.add(exitButton);

        // Adding button panel to the frame
        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getMinimumSize().height));
        button.addActionListener(this);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitButton) {
            dispose(); // Dispose the frame and close the popup
        }
        // Other actions based on the button clicks can be defined here
    }

    public static void main(String[] args) {
        new pauseMenu(); // Run the pause menu
    }
}
