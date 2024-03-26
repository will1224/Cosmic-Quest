import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class levelMenu implements ActionListener {
    private JFrame frame;

    public levelMenu() {
        frame = new JFrame("Cosmic Quest: Stellar Treasure");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(2880, 1800);

        JPanel backgroundPanel = new JPanel(new GridLayout(0, 4, 10, 10)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    BufferedImage backgroundImage = ImageIO.read(new File("images/menuBackground.jpg"));
                    Image scaledImage = backgroundImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
                    g.drawImage(scaledImage, 0, 0, this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        frame.setContentPane(backgroundPanel);

        // Manually create each button with its own image
        JButton sunButton = createButtonWithImage("images/circleButton.jpg", "The Sun");
        JButton mercuryButton = createButtonWithImage("images/mercury.png", "Mercury");
        JButton venusButton = createButtonWithImage("images/venus.png", "Venus");
        JButton earthButton = createButtonWithImage("images/earth.png", "Earth");
        JButton marsButton = createButtonWithImage("images/mars.png", "Mars");
        JButton jupiterButton = createButtonWithImage("images/jupiter.png", "Jupiter");
        JButton saturnButton = createButtonWithImage("images/saturn.png", "Saturn");
        JButton uranusButton = createButtonWithImage("images/uranus.png", "Uranus");
        JButton returnButton = createButtonWithImage("images/button.png", "Return to Main Menu");
        JButton neptuneButton = createButtonWithImage("images/neptune.png", "Neptune");
        JButton nebulasButton = createButtonWithImage("images/circleButton.jpg", "Nebulas");
        JButton blackHolesButton = createButtonWithImage("images/circleButton.jpg", "Black Holes");

        // Add buttons to the background panel
        backgroundPanel.add(sunButton);
        backgroundPanel.add(mercuryButton);
        backgroundPanel.add(venusButton);
        backgroundPanel.add(earthButton);
        backgroundPanel.add(marsButton);
        backgroundPanel.add(jupiterButton);
        backgroundPanel.add(saturnButton);
        backgroundPanel.add(uranusButton);
        backgroundPanel.add(returnButton);
        backgroundPanel.add(neptuneButton);
        backgroundPanel.add(nebulasButton);
        backgroundPanel.add(blackHolesButton);

        frame.setVisible(true);
    }

    private JButton createButtonWithImage(String imagePath, String actionCommand) {
        try {
            BufferedImage originalImage = ImageIO.read(new File(imagePath));
            int buttonDiameter = 250;
            Image processedImage = processImageToCircle(originalImage, buttonDiameter);

            JButton button = new JButton(new ImageIcon(processedImage));
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);
            button.setActionCommand(actionCommand);
            button.addActionListener(this);
            return button;
        } catch (IOException e) {
            e.printStackTrace();
            return new JButton(actionCommand); 
        }
    }

    private Image processImageToCircle(Image originalImage, int diameter) {
        BufferedImage resizedImage = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImage.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setClip(new Ellipse2D.Float(0, 0, diameter, diameter));
        g2d.drawImage(originalImage, 0, 0, diameter, diameter, null);

        g2d.dispose();
        return resizedImage;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        System.out.println(command + " button pressed");

        if ("Return to Main Menu".equals(command)) {
            frame.dispose(); 
            new mainMenu(); 
        }
    }

}
