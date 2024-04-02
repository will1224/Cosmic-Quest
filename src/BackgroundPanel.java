package src;

import javax.swing.*;
import java.awt.*;

/**
 * A helper class for creating panels with a background image.
 * 
 * @author Sophia Tong
 * @version 1.0
 */
class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    /**
     * Constructs a new BackgroundPanel with the specified background image.
     *
     * @param image The background image to be displayed.
     */
    public BackgroundPanel(Image image) {
        super();
        this.backgroundImage = image;
    }

    /**
     * Paints the background image on this panel.
     *
     * @param g The Graphics context.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        /** Draw the background image */
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}

