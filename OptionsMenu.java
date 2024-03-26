import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;


public class OptionsMenu extends JFrame {
    private JSlider masterSoundSlider;
    private JSlider soundEffectsSlider;
    private JSlider musicSlider;
    private JSlider brightnessSlider; // Brightness slider
    private JLabel headingSound;
    private JLabel headingDisplay;

    public OptionsMenu() {
        setTitle("Cosmic Quest: Stellar Treasures - Options");
        setSize(400, 300);
        setLocationRelativeTo(null);
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Set the background image
        setContentPane(new BackgroundPanel());

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(5, 1));
        optionsPanel.setOpaque(false); // Make the panel transparent

        // Sound Section
        optionsPanel.add(createSoundPanel());

        // Display Section
        optionsPanel.add(createDisplayPanel());

        // Return Button
        JButton returnButton = new JButton("Return to main menu");
        returnButton.setForeground(Color.WHITE); // Set text color to white
        returnButton.addActionListener(e -> dispose());

        add(optionsPanel, BorderLayout.CENTER);
        add(returnButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createSoundPanel() {
        JPanel soundPanel = new JPanel(new GridLayout(4, 1));
        soundPanel.setOpaque(false); // Make the panel transparent

        // Heading "Sound" with larger font
        headingSound = new JLabel("Sound");
        headingSound.setFont(new Font("Arial", Font.BOLD, 16));
        headingSound.setForeground(Color.WHITE); // Set text color to white
        soundPanel.add(headingSound);

        // Master Sound Slider
        masterSoundSlider = new JSlider(0, 100, 50);
        soundPanel.add(createLabeledSlider(masterSoundSlider, "Master:"));

        // Sound Effects Slider
        soundEffectsSlider = new JSlider(0, 100, 50);
        soundPanel.add(createLabeledSlider(soundEffectsSlider, "Sound Effects:"));

        // Music Slider
        musicSlider = new JSlider(0, 100, 50);
        soundPanel.add(createLabeledSlider(musicSlider, "Music:"));

        return soundPanel;
    }

    private JPanel createDisplayPanel() {
        JPanel displayPanel = new JPanel(new GridLayout(2, 1));
        displayPanel.setOpaque(false); // Make the panel transparent

        // Heading "Display" with larger font
        headingDisplay = new JLabel("Display");
        headingDisplay.setFont(new Font("Arial", Font.BOLD, 16));
        headingDisplay.setForeground(Color.WHITE); // Set text color to white
        displayPanel.add(headingDisplay);

        // Brightness Slider
        brightnessSlider = new JSlider(0, 100, 50);
        displayPanel.add(createLabeledSlider(brightnessSlider, "Brightness:"));

        return displayPanel;
    }

    private JPanel createLabeledSlider(JSlider slider, String label) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false); // Make the panel transparent
        JLabel labelComponent = new JLabel(label);
        labelComponent.setForeground(Color.WHITE); // Set text color to white
        
        // Set slider properties to make it appear white
        slider.setForeground(Color.WHITE); // Changes the color of the ticks and numbers
        slider.setUI(new BasicSliderUI(slider));
        slider.setOpaque(false); // Makes the slider background transparent
        
        UIManager.put("Slider.thumbWidth", 10); // Example of changing thumb width, might not take effect here
        
        panel.add(labelComponent, BorderLayout.WEST);
        panel.add(slider, BorderLayout.CENTER);
        return panel;
    }

    // Custom panel for the background
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            try {
                backgroundImage = new ImageIcon("images/gradientBGD.png").getImage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

    public static void main(String[] args) {
        new OptionsMenu();
    }
}
