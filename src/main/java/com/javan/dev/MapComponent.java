package com.javan.dev;

// Import Swing Components
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 * @author: Riley Emma Gavigan <rgavigan@uwo.ca>
 * @version: 1.0
 * @since: 1.0
 */
public class MapComponent extends JPanel implements ActionListener, MouseListener {
    /**
     * Initialize private variables for the UI
     */
    private JPanel mapPanel;
    private ImageIcon mapImg;
    private JLabel map;
    private JPanel imagePanel;
    private JScrollPane scrollPane;
    private int currentMapID;
    private DataProcessor dataProcessor = new DataProcessor();

    /**
     * Constructor to initialize the map component
     */
    public MapComponent() {
        /**
         * Create a new JPanel for the map
         */
        mapPanel = new JPanel();
        mapPanel.addMouseListener(this);
        mapPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        /**
         * Add scroll bars for the map panel
         */
        scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        /**
         * Add the default campus map image to the image panel
         */
        imagePanel = new JPanel();
        mapImg = new ImageIcon("data\\images\\campusMap.png");
        currentMapID = 1; // TODO: Get Map ID from backend - whatever it is determined to be
        map = new JLabel(mapImg);
        imagePanel.add(map);

        /**
         * Add the scroll pane to the map panel
         */
        scrollPane.setViewportView(imagePanel);

        /**
         * Make it so that the scroll pane displays the upper third of the map image when the UI is opened (use ImageIcon dimensions)
         */
        scrollPane.getViewport().setViewPosition(new Point(mapImg.getIconWidth() / 3, mapImg.getIconHeight() / 3));

        /**
         * Fill the entire display with the campus map
         */
        mapPanel.setLayout(new BorderLayout());
        mapPanel.add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Getter for map panel
     * @return mapPanel
     */
    public JPanel getMapPanel() {
        return mapPanel;
    }

    /**
     * Method to change the map being displayed in the map panel based on the String filepath being provided
     * @param filepath, mapID
     * @return None
     */
    public void changeMap(String filepath, int mapID) {
        /**
         * Update the current map ID
         */
        currentMapID = mapID;

        /**
         * Remove the current map image from the image panel
         */
        imagePanel.remove(map);

        /**
         * Add the new map image to the image panel
         */
        mapImg = new ImageIcon(filepath);
        map = new JLabel(mapImg);
        imagePanel.add(map);

        /**
         * Update the scroll pane
         */
        scrollPane.setViewportView(imagePanel);

        /**
         * Make it so that the scroll pane displays the upper third of the map image when the UI is opened (use ImageIcon dimensions)
         */
        scrollPane.getViewport().setViewPosition(new Point(mapImg.getIconWidth() / 3, mapImg.getIconHeight() / 3));

        /**
         * Update the map panel
         */
        mapPanel.add(scrollPane, BorderLayout.CENTER);

        /**
         * Set visible
         */
        mapPanel.setVisible(true);
    }

    /**
     * Method to navigate to a POI's coordinates on the map when given the POI name by using DataProcessor to retrieve the POI's coordinates
     */
    public void navigateToPOI(String name) {
        /**
         * Remove the current map image from the image panel
         */
        imagePanel.remove(map);

        /**
         * Add the new map image to the image panel
         */
        map = new JLabel(mapImg);
        imagePanel.add(map);

        /**
         * Update the scroll pane
         */
        scrollPane.setViewportView(imagePanel);

        /**
         * Get coordinates using DataProcessor
         */
        int[] coordinates = dataProcessor.getPOIPosition(name);
        int x = coordinates[0];
        int y = coordinates[1];

        /**
         * Make it so that the scroll pane displays the upper third of the map image when the UI is opened (use ImageIcon dimensions)
         */
        scrollPane.getViewport().setViewPosition(new Point(x, y));

        /**
         * Update the map panel
         */
        mapPanel.add(scrollPane, BorderLayout.CENTER);

        /**
         * Set visible
         */
        mapPanel.setVisible(true);
    }

    



     /**
      * Constructor to create the MapComponent within the UI
      */

    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }
    
}
