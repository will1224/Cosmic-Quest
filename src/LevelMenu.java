package src;

import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;

/**
 * Represents the level selection menu in the "Cosmic Quest: Stellar Treasures"
 * game.
 * This class creates a graphical user interface that allows players to select
 * from various
 * levels or sections within the game, such as the different planets and other
 * celestial bodies.
 * 
 * @author Sophia Tong
 */
public class LevelMenu implements ActionListener {
    private JFrame frame;
    private Accounts accounts;

    /**
     * Constructs a LevelMenu instance, initializing the user interface with buttons
     * for each game level and setting up action listeners.
     * 
     * @param accounts The accounts information, used for tracking level progress
     *                 and
     *                 unlocking levels based on player achievements.
     */
    public LevelMenu(Accounts accounts) {

        this.accounts = accounts;

        frame = new JFrame("Cosmic Quest: Stellar Treasures");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(2880, 1800);

        JPanel backgroundPanel = new JPanel(new GridLayout(0, 4, 10, 10)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    BufferedImage backgroundImage = ImageIO.read(new File("images/space.png"));
                    Image scaledImage = backgroundImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
                    g.drawImage(scaledImage, 0, 0, this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        frame.setContentPane(backgroundPanel);

        // Manually create each button with its own image
        JButton sunButton = createButtonWithImage("images/sun.png", "The Sun", 0);
        JButton mercuryButton = createButtonWithImage("images/mercury.png", "Mercury", 1);
        JButton venusButton = createButtonWithImage("images/venus.png", "Venus", 2);
        JButton earthButton = createButtonWithImage("images/earth.png", "Earth", 3);
        JButton marsButton = createButtonWithImage("images/mars.png", "Mars", 4);
        JButton jupiterButton = createButtonWithImage("images/jupiter.png", "Jupiter", 5);
        JButton saturnButton = createButtonWithImageSaturn("images/saturn.png", "Saturn", 6);
        JButton uranusButton = createButtonWithImageWidth("images/uranus.png", "Uranus", 7);
        JButton returnButton = createButtonWithImageBack("images/backbtn.png", "Return to Main Menu");
        JButton neptuneButton = createButtonWithImage("images/neptune.png", "Neptune", 8);
        JButton blackHolesButton = createButtonWithImage("images/blackhole.png", "Black Holes", 9);
        JLabel logo = new JLabel(new ImageIcon("images/logo.png"));

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
        backgroundPanel.add(blackHolesButton);
        backgroundPanel.add(logo);

        frame.setVisible(true);
    }

    /**
     * Creates and returns a {@code JButton} with an icon derived from the specified
     * image file.
     * The button is created with a fixed width, and the image is resized to
     * maintain its aspect ratio.
     * If the specified level is locked based on the user's progress, the button's
     * image is converted to grayscale.
     *
     * @param imagePath     the path to the image file for the button's icon.
     * @param actionCommand the action command associated with the button, used to
     *                      identify button presses.
     * @param level         the game level the button represents, used to determine
     *                      if the level is unlocked.
     * @return a {@code JButton} with the specified image and action command,
     *         possibly in grayscale if the level is locked.
     * @throws IOException if an error occurs while reading the image file.
     */
    private JButton createButtonWithImage(String imagePath, String actionCommand, int level) {
        try {
            BufferedImage originalImage = ImageIO.read(new File(imagePath));
            int originalWidth = originalImage.getWidth(null);
            int originalHeight = originalImage.getHeight(null);
            double aspectRatio = (double) originalHeight / (double) originalWidth;
            int newWidth = 200; // Width is constant for all buttons for consistency
            int newHeight = (int) Math.round(newWidth * aspectRatio);
            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = resizedImage.createGraphics();
            g2d.drawImage(originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
            g2d.dispose();

            // Check if the level is unlocked
            if (level > unlockedUpTo()) {
                // Apply a grayscale filter to the image if the level is locked
                ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
                op.filter(resizedImage, resizedImage);
            }

            JButton button = new JButton(new ImageIcon(resizedImage));
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);
            button.setActionCommand(actionCommand);
            button.addActionListener(this);

            // Optionally disable the button if the level is locked
            button.setEnabled(level <= unlockedUpTo());

            return button;
        } catch (IOException e) {
            e.printStackTrace();
            return new JButton(actionCommand);
        }
    }
    

    /**
     * Similar to {@code createButtonWithImage}, but specifically optimized for
     * buttons where width is a critical factor.
     * This method is typically used for special cases where button width needs to
     * be consistent across different screen sizes.
     *
     * @param imagePath     the path to the image file for the button's icon.
     * @param actionCommand the action command associated with the button.
     * @param level         the game level the button represents, used to determine
     *                      if the level is unlocked.
     * @return a {@code JButton} with the specified image and action command, with a
     *         fixed width and aspect-ratio-maintained height.
     * @throws IOException if an error occurs while reading the image file.
     */
    private JButton createButtonWithImageWidth(String imagePath, String actionCommand, int level) {
        try {
            BufferedImage originalImage = ImageIO.read(new File(imagePath));
            int originalWidth = originalImage.getWidth(null);
            int originalHeight = originalImage.getHeight(null);
            double aspectRatio = (double) originalHeight / (double) originalWidth;
            int newWidth = 200; // Width is constant for all buttons for consistency
            int newHeight = (int) Math.round(newWidth * aspectRatio);
            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = resizedImage.createGraphics();
            g2d.drawImage(originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
            g2d.dispose();

            // Check if the level is unlocked
            if (level > unlockedUpTo()) {
                // Apply a grayscale filter to the image if the level is locked
                ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
                op.filter(resizedImage, resizedImage);
            }

            JButton button = new JButton(new ImageIcon(resizedImage));
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);
            button.setActionCommand(actionCommand);
            button.addActionListener(this);

            // Optionally disable the button if the level is locked
            button.setEnabled(level <= unlockedUpTo());

            return button;
        } catch (IOException e) {
            e.printStackTrace();
            return new JButton(actionCommand);
        }
    }

    /**
     * Similar to {@code createButtonWithImage}, but specifically optimized for
     * Saturn to accomodate it's rings.
     *
     * @param imagePath     the path to the image file for the button's icon.
     * @param actionCommand the action command associated with the button.
     * @param level         the game level the button represents, used to determine
     *                      if the level is unlocked.
     * @return a {@code JButton} with the specified image and action command, with a
     *         fixed width and aspect-ratio-maintained height.
     * @throws IOException if an error occurs while reading the image file.
     */
    private JButton createButtonWithImageSaturn(String imagePath, String actionCommand, int level) {
        try {
            BufferedImage originalImage = ImageIO.read(new File(imagePath));
            int originalWidth = originalImage.getWidth(null);
            int originalHeight = originalImage.getHeight(null);
            double aspectRatio = (double) originalHeight / (double) originalWidth;
            int newWidth = 352; // Width is constant for all buttons for consistency
            int newHeight = (int) Math.round(newWidth * aspectRatio);
            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = resizedImage.createGraphics();
            g2d.drawImage(originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
            g2d.dispose();

            // Check if the level is unlocked
            if (level > unlockedUpTo()) {
                // Apply a grayscale filter to the image if the level is locked
                ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
                op.filter(resizedImage, resizedImage);
            }

            JButton button = new JButton(new ImageIcon(resizedImage));
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);
            button.setActionCommand(actionCommand);
            button.addActionListener(this);

            // Optionally disable the button if the level is locked
            button.setEnabled(level <= unlockedUpTo());

            return button;
        } catch (IOException e) {
            e.printStackTrace();
            return new JButton(actionCommand);
        }
    }

    /**
     * Creates a "Return to Main Menu" button with a specified image. This button allows users to navigate back to the main menu.
     * The image is resized based on a fixed width to ensure it fits well within the button.
     *
     * @param imagePath     the path to the image file for the button's icon.
     * @param actionCommand the action command indicating a return to the main menu.
     * @return a customized back {@code JButton} with the specified image.
     * @throws IOException if an error occurs while reading the image file.
     */
    private JButton createButtonWithImageBack(String imagePath, String actionCommand) {
        try {
            BufferedImage originalImage = ImageIO.read(new File(imagePath));
            int originalWidth = originalImage.getWidth(null);
            int originalHeight = originalImage.getHeight(null);
            int newWidth = 280;
            double aspectRatio = (double) originalHeight / (double) originalWidth;
            int newHeight = (int) Math.round(newWidth * aspectRatio);
            Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

            JButton button = new JButton(new ImageIcon(resizedImage));
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

     /**
     * Responds to action events triggered by button presses within the {@code LevelMenu}.
     * This method navigates to a specific game level, returns to the main menu, or performs other predefined actions
     * based on the action command of the pressed button.
     *
     * @param e the {@code ActionEvent} triggered by interacting with a button.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        System.out.println(command + " button pressed");

        // default
        int selected = 0;

        // Assign selected based on the command
        switch (command) {
            case "The Sun":
                selected = 0;
                break;
            case "Mercury":
                selected = 1;
                break;
            case "Venus":
                selected = 2;
                break;
            case "Earth":
                selected = 3;
                break;
            case "Mars":
                selected = 4;
                break;
            case "Jupiter":
                selected = 5;
                break;
            case "Saturn":
                selected = 6;
                break;
            case "Uranus":
                selected = 7;
                break;
            case "Neptune":
                selected = 8;
                break;
            case "Black Holes":
                selected = 9;
                break;
            case "Return to Main Menu":
                frame.dispose();
                // MainMenu mainMenu = new MainMenu(accounts);
                return; // Exit the method to prevent further execution
        }

        // Assuming GameControl's constructor takes an int for selected
        GameControl game = new GameControl(accounts);
        game.playLevel(selected);
    }

    /**
     * Retrieves the highest level unlocked by the current user.
     * This method is used internally to determine which levels should be accessible to the user and which should be
     * displayed in grayscale to indicate they are locked.
     *
     * @return the highest level number unlocked by the current user.
     */
    private int unlockedUpTo() {
        LevelProgress progress = new LevelProgress(accounts.getProgress(accounts.getCurrentAccount()));
        return progress.getCurrentLevel();
    }

}
