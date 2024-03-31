package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class LevelMenu implements ActionListener {
    private JFrame frame;
    private Accounts accounts;

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
                    BufferedImage backgroundImage = ImageIO.read(new File("images/space.jpg"));
                    Image scaledImage = backgroundImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
                    g.drawImage(scaledImage, 0, 0, this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        frame.setContentPane(backgroundPanel);

        // Manually create each button with its own image
        JButton sunButton = createButtonWithImage("images/sun.png", "The Sun");
        JButton mercuryButton = createButtonWithImage("images/mercury.png", "Mercury");
        JButton venusButton = createButtonWithImage("images/venus.png", "Venus");
        JButton earthButton = createButtonWithImage("images/earth.png", "Earth");
        JButton marsButton = createButtonWithImage("images/mars.png", "Mars");
        JButton jupiterButton = createButtonWithImage("images/jupiter.png", "Jupiter");
        JButton saturnButton = createButtonWithImage("images/saturn.png", "Saturn");
        JButton uranusButton = createButtonWithImageWidth("images/uranus.png", "Uranus");
        JButton returnButton = createButtonWithImageBack("images/backbtn.png", "Return to Main Menu");
        JButton neptuneButton = createButtonWithImage("images/neptune.png", "Neptune");
        JButton blackHolesButton = createButtonWithImage("images/blackhole.png", "Black Holes");
        JButton logo = createButtonWithImageLogo("images/logo.png", "logo");

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

    private JButton createButtonWithImage(String imagePath, String actionCommand) {
        try {
            BufferedImage originalImage = ImageIO.read(new File(imagePath));
            int originalWidth = originalImage.getWidth(null);
            int originalHeight = originalImage.getHeight(null);
            int newHeight = 190;

            double aspectRatio = (double) originalWidth / (double) originalHeight;
            int newWidth = (int) Math.round(newHeight * aspectRatio);
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

    private JButton createButtonWithImageWidth(String imagePath, String actionCommand) {
        try {
            BufferedImage originalImage = ImageIO.read(new File(imagePath));
            int originalWidth = originalImage.getWidth(null);
            int originalHeight = originalImage.getHeight(null);
            int newWidth = 200;
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

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        System.out.println(command + " button pressed");

        // CHECK ACCOUNTS NULL
        System.out.println("accounts null? " + accounts);

        GameControl game = new GameControl(accounts, false); // NEWGAME IS HARDCODED RN
        game.startGame();

        if ("Return to Main Menu".equals(command)) {
            frame.dispose();
            Accounts accounts = this.accounts; // Use the existing Accounts object
            MainMenu mainMenu = new MainMenu(accounts); 
        }
    }

    //public static void main(String[] args) {
        //new LevelMenu(accounts);
    //}

}
