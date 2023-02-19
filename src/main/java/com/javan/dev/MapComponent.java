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
     * Initialize private variables for the UI
     */
    private JPanel mapPanel;
    private ImageIcon mapImg;
    private JLabel map;
    private JPanel imagePanel;
    private JScrollPane scrollPane;
    private int currentMapID;
    private DataProcessor dataProcessor = new DataProcessor();
    private Map mapObject;
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
     * Boolean to hold the status of whether the map being displayed is the campus map (to relay to SidebarComponent for display)
     */
    private boolean isCampusMap;


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
        floorAbove = new JButton("Floor Up");
        floorBelow = new JButton("Floor Down");
        campusMap = new JButton("Campus Map");
        changeButtonStyle(floorAbove);
        changeButtonStyle(floorBelow);
        changeButtonStyle(campusMap);
        buttonPanel.add(campusMap);
        buttonPanel.add(floorBelow);
        buttonPanel.add(floorAbove);

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
        isCampusMap = true;
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
         * Check for floor above/below
         */
        isFloorAbove();
        isFloorBelow();

        /**
         * Display POIs
         */
        // TODO: Fix displayPOIs();

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
     * Function to change the style of a button
     */
    public void changeButtonStyle(JButton button) {
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        button.setFont(new Font("Georgia", Font.PLAIN, 17));
        button.addActionListener(this);
        button.addMouseListener(this);
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
         * Check for floor above/below
         */
        isFloorAbove();
        isFloorBelow();

        /**
         * Change boolean to reflect if campus map or not
         */
        if (mapID == 1) {
            isCampusMap = true;
        } else {
            isCampusMap = false;
        }

        /**
         * Display the POIs for the map
         */
        // TODO: Fix displayPOIs();

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
        if (dataProcessor.checkfloorAbove(currentMapID)) {
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
        if (dataProcessor.checkfloorBelow(currentMapID)) {
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
        if (dataProcessor.checkfloorAbove(currentMapID)) {
            /**
             * Get the map of the floor above
             */
            mapObject = dataProcessor.getfloorAbove(currentMapID);

            /**
             * Change the map
             */
            changeMap(mapObject.getFilePath(), mapObject.getMapID());
        }
    }

    /**
     * Method that changes the map to the floor below if there is a floor below the current one. Uses DataProcessor to get the Map of the floor below.
     * @param None
     * @return None
     */
    private void navigateToFloorBelow() {
        if (dataProcessor.checkfloorBelow(currentMapID)) {
            /**
             * Get the map of the floor below
             */
            Map map = dataProcessor.getfloorBelow(currentMapID);

            /**
             * Change the map
             */
            changeMap(map.getFilePath(), map.getMapID());
        }
    }

    /**
     * Method to change mouse cursor when button hovered over
     */
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }

    /**
     * Method to change mouse cursor back to default when button not hovered over
     */
    public void mouseExited(MouseEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        } 
    }

    /**
     * Method to display all POIs for the map currently being displayed on the map with a flag icon representing its location
     */
    public void displayPOIs() {
        /**
         * Get the POIs for the current map
         */
        pois = DataProcessor.getUniversalPOIs();

        /**
         * Loop through the POIs and add a flag icon to the map at the POI's coordinates
         */
        for (PointOfInterest poi : pois) {
            /**
             * Get the POI's coordinates
             */
            int[] coordinates = poi.getCoordinates();

            /**
             * Add the flag icon to the map at the POI's coordinates
             */
            JLabel flagLabel = new JLabel(flag);
            flagLabel.setBounds(coordinates[0], coordinates[1], 25, 25);
            imagePanel.add(flagLabel);
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
            changeMap("data\\images\\campusMap.png", 1);
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

    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    
}
