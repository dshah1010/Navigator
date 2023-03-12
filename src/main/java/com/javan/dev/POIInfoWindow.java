package com.javan.dev;

import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

/**
 * @author: Riley Emma Gavigan <rgavigan@uwo.ca>
 * @version: 1.0
 * @since: 1.0
 */
public class POIInfoWindow extends JFrame implements ActionListener, MouseListener {
    private JPanel panel;
    private JFrame frame;
    private ArrayList<JLabel> labels;
    private PointOfInterest poi;
    private JButton favourite;
    private ImageIcon favouriteIcon = new ImageIcon("data/images/favourited_poi.png");
    private ImageIcon unfavouriteIcon = new ImageIcon("data/images/unfavourited_poi.png");

    /**
     * Constructor that creates the POI information window given the PointOfInterest object
     */
    public POIInfoWindow(PointOfInterest poi) {
        this.poi = poi;

        /**
         * Update icons
         */
        Image img = unfavouriteIcon.getImage();
        Image newimg = img.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
        unfavouriteIcon = new ImageIcon(newimg);

        img = favouriteIcon.getImage();
        newimg = img.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
        favouriteIcon = new ImageIcon(newimg);


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
        frame.setIconImage(new ImageIcon("data/images/flag.png").getImage());

        /**
         * Create JPanel to hold the POI information
         */
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


        /**
         * Initialize array of 5 JLabels to hold the POI information
         */
        labels = new ArrayList<JLabel>();
        for (int i = 0; i < 8; i++) {
            labels.add(new JLabel());
        }


        /**
         * Create JLabels to hold the POI information
         */
        labels.get(0).setText(poi.getName());
        labels.get(1).setText("POI ID: " + poi.getID());
        labels.get(2).setText("RoomNumber: " + poi.getRoomNumber());
        labels.get(3).setText("Description: " + poi.getDescription());
        labels.get(4).setText("Layer Type: " + poi.getPOItype());
        labels.get(5).setText("X-Value: " + poi.getCoordinates()[0]);
        labels.get(6).setText("Y-Value: " + poi.getCoordinates()[1]);

        /**
         * Add the favourite button to the panel
         */
        if (!poi.getIsFavourited()) {
            /**
             * Make JButton with icon image
             */
            favourite = new JButton();
            favourite.setIcon(unfavouriteIcon);

            /**
             * Add icon to center of button
             */
            favourite.setVerticalAlignment(SwingConstants.CENTER);
            favourite.setHorizontalAlignment(SwingConstants.CENTER);
        }
        else {
           /**
             * Make JButton with icon image
             */ 
            favourite = new JButton();
            favourite.setIcon(favouriteIcon);

            /**
             * Add icon to center of button
             */
            favourite.setVerticalAlignment(SwingConstants.CENTER);
            favourite.setHorizontalAlignment(SwingConstants.CENTER);
        }

        /**
         * Style the JButton
         */
        favourite.setBackground(Color.WHITE);
        favourite.setForeground(Color.BLACK);
        favourite.setFocusPainted(false);
        favourite.setPreferredSize(new Dimension(40, 40));
        favourite.setMaximumSize(new Dimension(40, 40));
        favourite.setMinimumSize(new Dimension(40, 40));
        favourite.setBorder(BorderFactory.createEmptyBorder());
        favourite.addActionListener(this);
        favourite.addMouseListener(this);

        /**
         * Move button to the far right
         */
        favourite.setAlignmentX(Component.LEFT_ALIGNMENT);

        /**
         * Style labels
         */
        style(labels.get(0), 20);
        for (int i = 1; i < 8; i++) {
            style(labels.get(i), 15);
        }

        /**
         * Style the panel and space apart the JLabels
         */
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        /**
         * Let favourite button take all horizontal space possible
         */
        JPanel favouritePanel = new JPanel();
        favouritePanel.setLayout(new BoxLayout(favouritePanel, BoxLayout.X_AXIS));
        favouritePanel.setBackground(Color.WHITE);
        favouritePanel.add(Box.createHorizontalGlue());
        favouritePanel.add(favourite);

        panel.add(favouritePanel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));


        /**
         * Add the JLabels to the JPanel
         */
        for (int i = 0; i < 8; i++) {
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

    /**
     * Method to change the favourite button appearance and update the POI object
     */
    
    public void actionPerformed(ActionEvent e) {
        /**
         * If favourited, change to unfavourited
         */
        if (poi.getIsFavourited()) {
            favourite.setIcon(unfavouriteIcon);
            poi.setIsFavourited(false);
        }
        /**
         * If unfavourited, change to favourited
         */
        else {
            favourite.setIcon(favouriteIcon);
            poi.setIsFavourited(true);
        }
    }

    /**
     * Change mouse cursor when hovering over button
     * @param e
     */
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == favourite) {
            frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }

    /**
     * Change mouse cursor when no longer hovering over button
     * @param e
     */
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == favourite) {
            frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }

    public void mouseClicked(MouseEvent e) {
        // Do nothing
    }

    public void mousePressed(MouseEvent e) {
        // Do nothing
    }

    public void mouseReleased(MouseEvent e) {
        // Do nothing
    }
    
}