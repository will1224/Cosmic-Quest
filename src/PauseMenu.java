package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Constructs the pause menu for our game facilitating navigation through various game options.
 * It allows users to continue game, adjust settings and exit the game
 * 
 * @author Sophia Tong
 * @version 1.0
 */
public class PauseMenu extends JFrame implements ActionListener {
    private JButton resumeButton;
    private JButton optionsButton;
    private JButton exitButton;
    private Accounts accounts;

     /**
     * Sets up the pause menu interface with buttons and other components, preparing
     * it for user interaction.
     */
    public PauseMenu() {
        this.accounts = accounts;
        setTitle("Cosmic Quest: Stellar Treasures");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(1000, 1000); /** You can adjust the size as needed */
        setLocationRelativeTo(null); /** Center on screen */

        /** Use the custom background panel */
        BackgroundPanel backgroundPanel = new BackgroundPanel(new ImageIcon("images/pause.png").getImage());
        backgroundPanel.setLayout(new BorderLayout());
        setContentPane(backgroundPanel); /** Set the background panel as the main content pane */

        /** Button panel for aligning buttons vertically */
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false); // Make the panel transparent

        /** Add padding around the button panel */
        JPanel containerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        containerPanel.setOpaque(false); // Make container panel transparent
        containerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 100)); // Top, Left, Bottom, Right padding

        /** Create buttons with images */
        resumeButton = createImageButton("images/continue.png");
        optionsButton = createImageButton("images/options.png");
        exitButton = createImageButton("images/exit.png");
       
        buttonPanel.add(Box.createVerticalStrut(80));
        buttonPanel.add(Box.createVerticalStrut(80));
        buttonPanel.add(resumeButton);
        buttonPanel.add(Box.createVerticalStrut(80));
        buttonPanel.add(optionsButton);
        buttonPanel.add(Box.createVerticalStrut(80)); 
        buttonPanel.add(exitButton);

        /** Add the button panel to the container panel to apply padding */
        containerPanel.add(buttonPanel);

        backgroundPanel.add(containerPanel, BorderLayout.EAST);

        setVisible(true);
    }

    /**
     * Generates a button featuring an image, making the interface more engaging and visually appealing.
     * Each button is linked to specific functionality, enhancing the overall user experience.
     * 
     * @param imagePath Takes in a string to the path to the image file for the button icon.
     * @return A visually customized JButton ready for user interaction.
     */
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

    /**
     * Responds to user interactions with the pause menu's buttons, triggering actions like resuming the game, openning
     * the option menu and exiting the game.
     * 
     * @param e The event generated by button clicks.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resumeButton) {
            this.dispose();  
        } else if (e.getSource() == optionsButton) {
            new OptionsMenu();
        } else if (e.getSource() == exitButton) {
            this.dispose();
            new LoginForm(accounts);
        }
    }

    /**
     * Custom Jpanel class to paint the background.
     */
    private static class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(Image image) {
            this.backgroundImage = image;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            /** Draw the image as the background */
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

}
