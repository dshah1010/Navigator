package com.javan.dev;

// Import Swing Components
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;

/**
 * @author: Riley Emma Gavigan <rgavigan@uwo.ca>
 * @version: 1.0
 * @since: 1.0
 */
public final class MapComponent extends JPanel implements ActionListener, MouseListener {
    /**
     * Initialize private variables for the map UI
     */
    private JPanel mapPanel;
    private ImageIcon mapImg;
    private JLabel map;
    private JPanel imagePanel;
    private JScrollPane scrollPane;

    /**
     * Buttons to go on top of the map
     */
    private JButton floorAbove;
    private JButton floorBelow;
    private JButton campusMap;
    private JPanel buttonPanel;

    private ArrayList<PointOfInterest> pois;
    private ImageIcon flag = new ImageIcon("data\\images\\flag.png");

    /**
     * Singleton instance variable
     */
    private static MapComponent INSTANCE;

    /**
     * variables to hold the current map and whether or not it is the campus map
     */
    private boolean isCampusMap;
    private int currentMapID;
    private Map mapObject;

    /**
     * DataProcessor instance
     */
    private DataProcessor dataProcessor = DataProcessor.getInstance();

    private Map campus;


    /**
     * Constructor to initialize the map component
     */
    private MapComponent() {
        /**
         * Create a new JPanel for the map
         */
        mapPanel = new JPanel();
        mapPanel.addMouseListener(this);
        mapPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        /**
         * Create a JPanel that stores 3 buttons to go on top of the map (Floor Up, Floor Down, Back to Campus Map)
         */
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));

        campusMap = createMapButton("Campus Map");
        floorBelow = createMapButton("Floor Down");
        floorAbove = createMapButton("Floor Up");

        buttonPanel.add(campusMap);
        buttonPanel.add(floorBelow);
        buttonPanel.add(floorAbove);

        /**
         * Add scroll bars for the map panel as required
         */
        scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        /**
         * Add the default campus map image to the image panel
         */
        imagePanel = new JPanel();
        imagePanel.setLayout(null);
        mapImg = new ImageIcon("data\\images\\campusMap.png"); // TODO: Get Campus Map from backend and use that
        isCampusMap = true;
        currentMapID = 1; // TODO: Get Map ID from backend - whatever it is determined to be
        map = new JLabel(mapImg);
        map.setLayout(null);
        map.setBounds(0, 0, mapImg.getIconWidth(), mapImg.getIconHeight());
        imagePanel.setPreferredSize(new Dimension(mapImg.getIconWidth(), mapImg.getIconHeight()));
        imagePanel.add(map);
        /**
         * Display POIs
         */
        displayPOIs();

        /**
         * Add the scroll pane to the map panel
         */
        scrollPane.setViewportView(imagePanel);

        /**
         * Make it so that the scroll pane displays the upper third of the map image when the UI is opened (use ImageIcon dimensions)
         */
        scrollPane.getViewport().setViewPosition(new Point(mapImg.getIconWidth() / 3, mapImg.getIconHeight() / 3));

        /**
         * Check for floor above/below
         */
        // isFloorAbove(); TODO: Wait for implementation to be finished from FloorMap
        // isFloorBelow(); TODO: Wait for implementation to be finished from FloorMap

        /**
         * Fill the entire display with the campus map
         */
        mapPanel.setLayout(new BorderLayout());
        mapPanel.add(buttonPanel, BorderLayout.NORTH);
        mapPanel.add(scrollPane, BorderLayout.CENTER);
    }
    
    /**
     * Getter for the instance of the MapComponent
     * @return MapComponent Instance
     */
    public static MapComponent getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MapComponent();
        }
        return INSTANCE;
    }

    /**
     * Getter for map panel
     * @return mapPanel
     */
    public JPanel getMapPanel() {
        return mapPanel;
    }

    /**
     * Getter for current map ID
     * @return currentMapID
     */
    public int getCurrentMapID() {
        return currentMapID;
    }

    /**
     * Getter for isCampusMap boolean
     * @return isCampusMap
     */
    public boolean getIsCampusMap() {
        return isCampusMap;
    }

    /**
     * Getter for the current Map object
     * @return mapObject
     */
    public Map getMapObject() {
        return mapObject;
    }

    /**
     * Setter for the Map object and Map ID, and isCampusMap status
     * @param mapObject
     */
    public void setMapDetails(Map newMap) {
        this.mapObject = newMap;
        currentMapID = newMap.getMapID();
        if (currentMapID != 1) {
            isCampusMap = false;
        }
        else {
            isCampusMap = true;
        }
    }

    /**
     * Function to create a new button and style it
     */
    public JButton createMapButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        button.setFont(new Font("Georgia", Font.PLAIN, 17));
        button.addActionListener(this);
        button.addMouseListener(this);

        return button;
    }

    /**
     * Method to change the map being displayed in the map panel based on the String filepath being provided
     * @param filepath, mapID
     * @return None
     */
    public void changeMap(Map newMap) {
        /**
         * Set map details
         */
        setMapDetails(newMap);

        /**
         * Remove the current map image from the image panel
         */
        imagePanel.remove(map);

        /**
         * Add the new map image to the image panel
         */
        mapImg = new ImageIcon(newMap.getFilePath());
        map = new JLabel(mapImg);
        map.setLayout(null);
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
         * Check for floor above/below
         */
        // isFloorAbove(); TODO: Wait for implementation to be finished from FloorMap
        // isFloorBelow(); TODO: Wait for implementation to be finished from FloorMap

        /**
         * Display the POIs for the map
         */
        displayPOIs();

        /**
         * Set visible
         */
        mapPanel.setVisible(true);
    }

    /**
     * Method to navigate to a POI's coordinates on the map when given the POI name by using DataProcessor to retrieve the POI's coordinates
     */
    public void navigateToPOI(int poiID) {
        /**
         * Get the POI's coordinates
         */
        int[] coordinates = dataProcessor.getPOIPosition(poiID);
        System.out.println(coordinates[0]);
        System.out.println(coordinates[1]);

        /**
         * Navigate to the POI's coordinates
         */
        scrollPane.getViewport().setViewPosition(new Point(coordinates[0], coordinates[1]));
    }

    /**
     * Method that checks via DataProcessor if there is a floor above the current one. 
     * If there is not, it grays out the button.
     * If there is, it enables the button.
     * @param None
     * @return None
     */
    private void isFloorAbove() {
        if (mapObject.checkfloorAbove(currentMapID)) {
            /**
             * Enable the button "Floor Up"
             */
            floorAbove.setEnabled(true);

        } else {
            floorAbove.setEnabled(false);
        }
    }

    /**
     * Method that checks via DataProcessor if there is a floor below the current one. 
     * If there is not, it grays out the button.
     * If there is, it enables the button.
     * @param None
     * @return None
     */
    private void isFloorBelow() {
        if (mapObject.checkfloorBelow(currentMapID)) {
            /**
             * Enable the button "Floor Down"
             */
            floorBelow.setEnabled(true);

        } else {
            floorBelow.setEnabled(false);
        }
    }


    /**
     * Method that changes the map to the floor above if there is a floor above the current one. Uses DataProcessor to get the Map of the floor above.
     * @param None
     * @return None
     */
    private void navigateToFloorAbove() {
        if (mapObject.checkfloorAbove(currentMapID)) {
            /**
             * Get the map of the floor above
             */
            mapObject = mapObject.getfloorAbove(currentMapID);

            /**
             * Change the map
             */
            changeMap(mapObject);
        }
    }

    /**
     * Method that changes the map to the floor below if there is a floor below the current one. Uses DataProcessor to get the Map of the floor below.
     * @param None
     * @return None
     */
    private void navigateToFloorBelow() {
        if (mapObject.checkfloorBelow(currentMapID)) {
            /**
             * Get the map of the floor below
             */
            mapObject = mapObject.getfloorBelow(currentMapID);

            /**
             * Change the map
             */
            changeMap(mapObject);
        }
    }

    /**
     * Method to change mouse cursor when button hovered over and when a POI flag is hovered over
     */
    public void mouseEntered(MouseEvent e) {
        /**
         * Button hover
         */
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        /**
         * POI Flag hovered over
         */
        else if (e.getSource() instanceof JLabel) {
            JLabel label = (JLabel) e.getSource();
            label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }

    /**
     * Method to change mouse cursor back to default when button not hovered over
     * Also changes back to default when POI flag not hovered over
     */
    public void mouseExited(MouseEvent e) {
        /**
         * Button hover off
         */
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        } 
        /**
         * POI Flag hover off
         */
        else if (e.getSource() instanceof JLabel) {
            JLabel label = (JLabel) e.getSource();
            label.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }

    /**
     * Method to display all POIs for the map currently being displayed on the map with a flag icon representing its location
     */
    public void displayPOIs() {
        /**
         * Get the POIs for the current map
         */
        pois = dataProcessor.getUniversalPOIs();

        /**
         * Loop through the POIs and add a flag icon to the map at the POI's coordinates
         */
        for (PointOfInterest poi : pois) {
            /**
             * Get the POI's coordinates
             */
            int[] coordinates = poi.getCoordinates();

            /**
             * Add the flag icon to the map at the POI's coordinates (x and y position)
             */
            ImageIcon flagIcon = flag;
            /**
             * Get scaled version of 40x40 pixels as ImageIcon
             */
            Image scaledFlag = flagIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);

            /**
             * Create a JLabel with the scaled flag icon
             */
            JLabel flag = new JLabel(new ImageIcon(scaledFlag));

            /**
             * Add the ID of the POI to the flag icon as metadata
             */
            flag.setText(Integer.toString(poi.getID()));


            flag.setBounds(coordinates[0], coordinates[1], 40, 40);
            
            /**
             * Add a mouse listener to the flag icon to navigate to the POI when clicked
             */
            flag.addMouseListener(this);

            /**
             * Add the flag icon to the map
             */
            map.add(flag);

            /**
             * Repaint the map panel
             */
            mapPanel.repaint();
            flag.setVisible(true);
            imagePanel.setVisible(true);

        }
    }

    /**
     * Method to handle button clicks by delegating to other functions that will make the appropriate changes to the map and use other classes
     */
    public void actionPerformed(ActionEvent e) {
        /**
         * Get button text
         */
        String buttonText = ((JButton) e.getSource()).getText();

        /**
         * If the button text is "Campus Map", change the map to the campus map
         */
        if (buttonText.equals("Campus Map")) {
            changeMap(campus);
        }
        /**
         * If the button text is "Floor Down", change the map to the floor below the current map
         */
        else if (buttonText.equals("Floor Down")) {
            navigateToFloorBelow();
        }
        /**
         * If the button text is "Floor Up", change the map to the floor above the current map
         */
        else if (buttonText.equals("Floor Up")) {
            navigateToFloorAbove();
        }
    }

    /**
     * Method to handle mouse clicks on a POI on the map. When a POI is clicked, opens up a small pop-up window with the POI's information
     */
    public void mouseClicked(MouseEvent e) {
        /**
         * Get the label that was clicked
         */
        JLabel label = (JLabel) e.getSource();

        /**
         * Get the POI Id of the label
         */
        String id = label.getText();

        /**
         * Get the POI object from the POI Id
         */
        PointOfInterest poi = dataProcessor.getPOI(Integer.parseInt(id));

        /**
         * Create a new POIInfoWindow object and pass the POI object to it
         */
        POIInfoWindow poiInfoWindow = new POIInfoWindow(poi);

        /**
         * Display the POIInfoWindow right on top of the map
         */
        poiInfoWindow.getFrame().setLocationRelativeTo(mapPanel);
        poiInfoWindow.setVisibleFrame();
    }

    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    
}
