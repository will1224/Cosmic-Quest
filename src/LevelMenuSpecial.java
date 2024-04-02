package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.awt.image.BufferedImage;

/**
 * The LevelMenuSpecial class provides a graphical interface for the instructors and the developers
 * This page will have all the planets already unlocked which is a special feature uniques to the 
 * instructor and developer.
 *
 * @author Sophia Tong
 * @version 1.0
 */
public class LevelMenuSpecial implements ActionListener {
    private JFrame frame;
    private Accounts accounts;

    public LevelMenuSpecial(Accounts accounts) {

        this.accounts = accounts;

        frame = new JFrame("Cosmic Quest: Stellar Treasures");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(2880, 1800);

        JPanel backgroundPanel = new JPanel(new GridLayout(0, 4, 10, 10)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    URL backgroundImageURL = getClass().getResource("/images/space.png");
                    if (backgroundImageURL != null) {
                        BufferedImage backgroundImage = ImageIO.read(backgroundImageURL);
                        Image scaledImage = backgroundImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
                        g.drawImage(scaledImage, 0, 0, this);
                    } else {
                        System.err.println("Unable to load background image.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        frame.setContentPane(backgroundPanel);

        /** Manually create each button with its own image */
        JButton sunButton = createButtonWithImage("/images/sun.png", "The Sun");
        JButton mercuryButton = createButtonWithImage("/images/mercury.png", "Mercury");
        JButton venusButton = createButtonWithImage("/images/venus.png", "Venus");
        JButton earthButton = createButtonWithImage("/images/earth.png", "Earth");
        JButton marsButton = createButtonWithImage("/images/mars.png", "Mars");
        JButton jupiterButton = createButtonWithImage("/images/jupiter.png", "Jupiter");
        JButton saturnButton = createButtonWithImageSaturn("/images/saturn.png", "Saturn");
        JButton uranusButton = createButtonWithImageWidth("/images/uranus.png", "Uranus");
        JButton returnButton = createButtonWithImageBack("/images/backbtn.png", "Return to Main Menu");
        JButton neptuneButton = createButtonWithImage("/images/neptune.png", "Neptune");
        JButton blackHolesButton = createButtonWithImage("/images/blackhole.png", "Black Holes");
        JLabel logo = new JLabel();
        try {
            URL logoURL = getClass().getResource("/images/logo.png");
            if (logoURL != null) {
                ImageIcon logoIcon = new ImageIcon(ImageIO.read(logoURL));
                logo.setIcon(logoIcon);
            } else {
                System.err.println("Unable to load logo image.");
                // Optionally set a placeholder or error text
                logo.setText("Logo not available");
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Optionally handle the error, such as setting a placeholder or error text
            logo.setText("Error loading logo");
        }

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
     * Creates a button with a specified image and action command. This method reads the image from the given file path,
     * resizes it to a fixed height while maintaining aspect ratio, and applies it to a new button.
     * 
     * @param imagePath The path to the image file for the button's icon.
     * @param actionCommand The action command associated with the button, used to identify button presses.
     * @return A {@code JButton} with the specified image and action command.
     */
    private JButton createButtonWithImage(String imagePath, String actionCommand) {
        try {
            // Change starts here: Load the image using getClass().getResource()
            URL imageUrl = getClass().getResource(imagePath);
            if (imageUrl == null) {
                System.err.println("Resource not found: " + imagePath);
                return new JButton(actionCommand);
            }
            BufferedImage originalImage = ImageIO.read(imageUrl);
    
            int originalWidth = originalImage.getWidth();
            int originalHeight = originalImage.getHeight();
            int newWidth = 200; // Fixed width for button images
            double aspectRatio = (double) originalHeight / (double) originalWidth;
            int newHeight = (int) Math.round(newWidth * aspectRatio);
    
            // Scale the image to fit the button
            Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
    
            // Create the button with the resized image
            JButton button = new JButton(new ImageIcon(resizedImage));
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);
            button.setActionCommand(actionCommand);
            button.addActionListener(this);
            return button;
        } catch (IOException e) {
            e.printStackTrace();
            return new JButton(actionCommand); // Return a basic button if image load fails
        }
    }

    /**
     * Similar to {@code createButtonWithImage} but resizes the image based on a fixed width while maintaining
     * aspect ratio. It's used for buttons where width is more critical than height.
     * 
     * @param imagePath The path to the image file for the button's icon.
     * @param actionCommand The action command associated with the button.
     * @return A {@code JButton} with the specified image and action command.
     */
    private JButton createButtonWithImageWidth(String imagePath, String actionCommand) {
        try {
            // Change starts here: Load the image using getClass().getResource()
            URL imageUrl = getClass().getResource(imagePath);
            if (imageUrl == null) {
                System.err.println("Resource not found: " + imagePath);
                return new JButton(actionCommand);
            }
            BufferedImage originalImage = ImageIO.read(imageUrl);
    
            int originalWidth = originalImage.getWidth();
            int originalHeight = originalImage.getHeight();
            int newWidth = 200; // Fixed width for button images
            double aspectRatio = (double) originalHeight / (double) originalWidth;
            int newHeight = (int) Math.round(newWidth * aspectRatio);
    
            // Scale the image to fit the button
            Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
    
            // Create the button with the resized image
            JButton button = new JButton(new ImageIcon(resizedImage));
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);
            button.setActionCommand(actionCommand);
            button.addActionListener(this);
            return button;
        } catch (IOException e) {
            e.printStackTrace();
            return new JButton(actionCommand); // Return a basic button if image load fails
        }
    }

     /**
     * Similar to {@code createButtonWithImage} but specifically made to size Saturn due to it's rings
     * 
     * @param imagePath The path to the image file for the button's icon.
     * @param actionCommand The action command associated with the button.
     * @return A {@code JButton} with the specified image and action command.
     */
    private JButton createButtonWithImageSaturn(String imagePath, String actionCommand) {
        try {
            // Change starts here: Load the image using getClass().getResource()
            URL imageUrl = getClass().getResource(imagePath);
            if (imageUrl == null) {
                System.err.println("Resource not found: " + imagePath);
                return new JButton(actionCommand);
            }
            BufferedImage originalImage = ImageIO.read(imageUrl);
    
            int originalWidth = originalImage.getWidth();
            int originalHeight = originalImage.getHeight();
            int newWidth = 352; // Fixed width for button images
            double aspectRatio = (double) originalHeight / (double) originalWidth;
            int newHeight = (int) Math.round(newWidth * aspectRatio);
    
            // Scale the image to fit the button
            Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
    
            // Create the button with the resized image
            JButton button = new JButton(new ImageIcon(resizedImage));
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);
            button.setActionCommand(actionCommand);
            button.addActionListener(this);
            return button;
        } catch (IOException e) {
            e.printStackTrace();
            return new JButton(actionCommand); // Return a basic button if image load fails
        }
    }


    /**
     * Specifically creates a back button with an image, resizing based on a fixed width. It allows users to return
     * to the main menu. This method demonstrates how specific button types can be customized individually.
     * 
     * @param imagePath The path to the image file for the button's icon.
     * @param actionCommand The action command indicating a return to the main menu.
     * @return A back {@code JButton} customized with the specified image.
     */
    private JButton createButtonWithImageBack(String imagePath, String actionCommand) {
         try {
            // Change starts here: Load the image using getClass().getResource()
            URL imageUrl = getClass().getResource(imagePath);
            if (imageUrl == null) {
                System.err.println("Resource not found: " + imagePath);
                return new JButton(actionCommand);
            }
            BufferedImage originalImage = ImageIO.read(imageUrl);
    
            int originalWidth = originalImage.getWidth();
            int originalHeight = originalImage.getHeight();
            int newWidth = 280; // Fixed width for button images
            double aspectRatio = (double) originalHeight / (double) originalWidth;
            int newHeight = (int) Math.round(newWidth * aspectRatio);
    
            // Scale the image to fit the button
            Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
    
            // Create the button with the resized image
            JButton button = new JButton(new ImageIcon(resizedImage));
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);
            button.setActionCommand(actionCommand);
            button.addActionListener(this);
            return button;
        } catch (IOException e) {
            e.printStackTrace();
            return new JButton(actionCommand); // Return a basic button if image load fails
        }
    }

     /**
     * Creates a button decorated with a logo image. This method is tailored for buttons that might not directly
     * relate to level selection but are integral to the menu's visual identity.
     * 
     * @param imagePath The path to the logo image file.
     * @param actionCommand The action command associated with the logo button.
     * @return A logo {@code JButton} with the specified image.
     */
    private JButton createButtonWithImageLogo(String imagePath, String actionCommand) {
        try {
            BufferedImage originalImage = ImageIO.read(new File(imagePath));
            int originalWidth = originalImage.getWidth(null);
            int originalHeight = originalImage.getHeight(null);
            int newWidth = 270;
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
     * Handles action events triggered by button presses within the {@code LevelMenu}. Depending on the action command
     * of the pressed button, this method either navigates to a specific game level, returns to the main menu,
     * or performs another action defined within the method's logic.
     *
     * @param e The {@code ActionEvent} triggered by interacting with a button.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        System.out.println(command + " button pressed");

        /** default */
        int selected = 0;

        /** Assign selected based on the command */
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
                /** MainMenu mainMenu = new MainMenu(accounts); */
                return; /** Exit the method to prevent further execution */
        }

        /** Assuming GameControl's constructor takes an int for selected */
        GameControl game = new GameControl(accounts);
        game.playLevel(selected);
    }

    private int unlockedUpTo(){
        LevelProgress progress = new LevelProgress(accounts.getProgress(accounts.getCurrentAccount()));
        return progress.getCurrentLevel();
    }

}