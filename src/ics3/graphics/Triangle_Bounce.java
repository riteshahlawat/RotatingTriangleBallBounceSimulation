/*
Name - Ritesh Ahlawat
Date - 2017 - 04 - 06
Assignment - Triangle Bounce
Description -
Create a program in which;
    1.  You must use JDK7 for this program
    2.  Using 2D java programming only,
    3.  Create an application in which a ball is moving in a straight line
(without slowing down) but bounces off the sides of a shape:
    4.  the shape is a triangle (larger than the ball)
    5.  the triangle rotates clockwise/counter clockwise.
    6.  Use the MVC design principle
    7.  main method should not contain processing.
    8.  Your application needs to be documented using Javadoc style tags in the code
    9.  You must develop an analysis document before the major coding starts

    And also has implemented in it:

    1.  Application allows the user to reverse the rotation direction of the triangle
    2.  Application allows the user to shrink/enlarge the shape using the '+' and '-' keys
*/
package ics3.graphics;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Main class in where MVC is declared and initialized as well with all methods
 * Also where you implement the runnable interface
 */
public class Triangle_Bounce {
    // final variables for screen size
    public static final int FRAME_WIDTH = 800;
    public static final int FRAME_HEIGHT = 650;
    // important variables
    public static int frameFrameRate = 1000 / 60;
    public static int gameFrameRate = 1000 / 60;
    // import MVC classes
    public static Model model;
    public static View view;
    public static Controller controller;

    private Timer frameTimer;
    private Timer gameTimer;

    /**
     * constructor which assigns the class
     */
    public Triangle_Bounce() {
        view = new View();
        model = new Model();
        controller = new Controller();
        // runs all the GUI components
        view.runAllGUI();

        frameTimer = new Timer(frameFrameRate, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // repaints the frame and runs the MVC loops
                view.frame.repaint();

            }
        });
        gameTimer = new Timer(gameFrameRate, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.runLoopFunctions();
            }
        });
        frameTimer.start();
        gameTimer.start();
    }

    /**
     * creates a new thread and runs the Triangle_Bounce class
     *
     * @param args Unused
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Triangle_Bounce();
            }
        });
    }
}
