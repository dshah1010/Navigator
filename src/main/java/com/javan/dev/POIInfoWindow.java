package com.javan.dev;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;

/**
 * @author: Riley Emma Gavigan <rgavigan@uwo.ca>
 * @version: 1.0
 * @since: 1.0
 */
public class POIInfoWindow extends JFrame {
    private JPanel panel;
    private JFrame frame;
    private ArrayList<JLabel> labels;

    /**
     * Constructor that creates the POI information window given the PointOfInterest object
     */
    public POIInfoWindow(PointOfInterest poi) {
        /**
         * Create JFrame Window
         */
        frame = new JFrame(poi.getName());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(350, 350);
        frame.setMinimumSize(new Dimension(200, 200));

        /**
         * Add icon to the UI frame (Flag Icon)
         */
        frame.setIconImage(new ImageIcon("data\\images\\flag.png").getImage());

        /**
         * Create JPanel to hold the POI information
         */
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        /**
         * Initialize array of 5 JLabels to hold the POI information
         */
        labels = new ArrayList<JLabel>();
        for (int i = 0; i < 5; i++) {
            labels.add(new JLabel());
        }


        /**
         * Create JLabels to hold the POI information
         */
        labels.get(0).setText(poi.getName());
        labels.get(1).setText("ID: " + poi.getID());
        labels.get(2).setText("Description: " + poi.getDescription());
        labels.get(3).setText("X: " + poi.getCoordinates()[0]);
        labels.get(4).setText("Y: " + poi.getCoordinates()[1]);
        style(labels.get(0), 20);
        style(labels.get(1), 15);
        style(labels.get(2), 15);
        style(labels.get(3), 15);
        style(labels.get(4), 15);

        /**
         * Style the panel and space apart the JLabels
         */
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        /**
         * Add the JLabels to the JPanel
         */
        for (int i = 0; i < 5; i++) {
            panel.add(labels.get(i));
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        /**
         * Add the JPanel to the JFrame
         */
        frame.add(panel);
    }

    public void setVisibleFrame() {
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    /**
     * Styling for the JLabel text and font
     */
    private void style(JLabel label, int n) {
        label.setFont(new Font("Georgia", Font.PLAIN, n));
        label.setForeground(Color.BLACK);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    /**
     * Getter for the labels ArrayList
     */
    public ArrayList<JLabel> getLabels() {
        return labels;
    }
    
}