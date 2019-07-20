package ics3.graphics;

// importing important libraries

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * image class uses JComponent to draw the image from either a URL or Hard Drive
 */
public class Image extends JComponent {
    // important variables
    public double imageX;
    private double imageY;
    private double imageWidth;
    private double imageHeight;

    // bufferedImage type
    BufferedImage img;

    /**
     * default constructor that assigns values tot he parameters
     *
     * @param fileLocOrURL the URL of the file path from the hard drive or from directly from the internet (must need an internet connection)
     * @param imageX       X-Position on where image will be drawn
     * @param imageY       Y-Position on where image will be drawn
     * @param imageWidth   Image width size
     * @param imageHeight  Image Height size
     */
    public Image(String fileLocOrURL, double imageX, double imageY, double imageWidth, double imageHeight) {
        this.imageX = imageX;
        this.imageY = imageY;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        // try - catch statement to try and read the image from the specified imagelocation
        try {
            img = ImageIO.read(new File(fileLocOrURL));
        } catch (IOException e) {
            try {
                img = ImageIO.read(new URL(fileLocOrURL));
            } catch (IOException ee) {
                System.out.println(ee);
                img = null;
            }
        }
        init();
    }

    /**
     * initializes the default location and the drawing pane
     */
    private void init() {
        setLocation((int) imageX, (int) imageY);
        setSize(getPreferredSize());
    }

    /**
     * Gets the size of the drawing pane in which JComponent renders
     * and draws components
     *
     * @return Dimension (Width and Height)
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension((int) imageWidth, (int) imageHeight);
    }


    /**
     * paintComponent method which is overridden from the JComponent class to draw the line on the Drawing panel (size is defined by getPreferredSize method)
     *
     * @param g Graphics parameter which is then converted into a Graphics2D by type casting g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        setDoubleBuffered(true);
        g2d.drawImage(img, 0, 0, (int) imageWidth, (int) imageHeight, this);
    }
}
