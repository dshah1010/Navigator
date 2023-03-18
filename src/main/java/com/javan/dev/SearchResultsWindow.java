package com.javan.dev;

// Import necessary libraries
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * @author: Jake Choi <jchoi492@uwo.ca>
 * @version: 1.0
 * @since: 1.0
 */

public class SearchResultsWindow extends JFrame implements MouseListener {
    /**
     * Initialize all instance variables for the UI of the search results window.
     */
    private JFrame frame;
    private JPanel panel;
    private JScrollPane scrollPane;
    private JList<PointOfInterest> resultList;
    private JList<BuildingPointOfInterest> buildingResultList;
    private JButton okay;

    /** 
     * Track the currently selected POI on the list.
     */
    private PointOfInterest currSelected;
    private BuildingPointOfInterest currBuildingSelected;

    private static SearchResultsWindow INSTANCE = SearchResultsWindow.getInstance();

    private MapComponent currMap;

    private DataProcessor currProcessor;

    /**
     * This is the constructor of a SearchResults object. 
     * It will create a JList and JFrame to show a new window with the search results.
     * @param listData  This is the arra of Points of Interest that were found based on the search.
     */
    public SearchResultsWindow(ArrayList<PointOfInterest> listData, String searchText, MapComponent currMap, DataProcessor processor) {
        currSelected = null;
        this.currMap = currMap;
        this.currProcessor = processor;
        /**
         * Create the new frame.
         */
        createFrame(listData, searchText);
    }

    /**
     * Building Constructor
     */
    public SearchResultsWindow(ArrayList<BuildingPointOfInterest> buildingSearchMatch, String lowerCase, MapComponent mapComponent) {
        currBuildingSelected = null;
        this.currMap = mapComponent;
        this.currProcessor = DataProcessor.getInstance();
        /**
         * Create the new frame.
         */
        createBuildingFrame(buildingSearchMatch, lowerCase);
    }

    /**
     * Returns the instance of the current search results window. 
     * @return  Instance of current search results window.
     */
    public static SearchResultsWindow getInstance() {
        return INSTANCE;
    }

    /**
     * Open the search results window.
     * @return
     */
    public void openSearchResults() {
        frame.setVisible(true);
    }

    /**
     * Getter method for the frame of the search results window.
     * @return      Return the frame
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Getter method for the JList of the search results.
     * @return      The search results as a JList
     */
    public JList<PointOfInterest> getList() {
        return resultList;
    }

    /**
     * Getter method for the currently selected POI in the JList.
     * @return      The Point of Interest curently selected by user.
     */
    public PointOfInterest getCurrentSelected() {
        return currSelected;
    }

    /**
     * This method is to check what to do based on where the user clicks their mouse within the window.
     * @param e     The mouse event
     * @return  
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void mouseClicked(MouseEvent e) {
        /**
         * Check if the mouse click event was on an item on the JList.
         */
        if (e.getSource() instanceof JList && e.getClickCount() == 1) {
            if (currMap.getIsCampusMap()) {
                JList<BuildingPointOfInterest> buildingList = (JList) e.getSource();
                /**
                 * Restrict the clicking for within the items available in the list. The mouse click on any empty space within the JList scroll pane will do nothing.
                 */
                Rectangle r = buildingList.getCellBounds(0, buildingList.getLastVisibleIndex());
                if (r != null && r.contains(e.getPoint())) { 
                    /**
                     * Set currently selected POI to the POI selected by the user (by click).
                     */
                    int index = buildingList.locationToIndex(e.getPoint()); 
                    if (index >= 0) {
                        BuildingPointOfInterest o = buildingList.getModel().getElementAt(index);
                        currBuildingSelected = o;
                    }
                }
            }
            else {
                JList<PointOfInterest> poiList = (JList) e.getSource();
                
                /**
                 * Restrict the clicking for within the items available in the list. The mouse click on any empty space within the JList scroll pane will do nothing.
                 */
                Rectangle r = poiList.getCellBounds(0, poiList.getLastVisibleIndex());
                if (r != null && r.contains(e.getPoint())) { 
                    /**
                     * Set currently selected POI to the POI selected by the user (by click).
                     */
                    int index = poiList.locationToIndex(e.getPoint()); 
                    if (index >= 0) {
                        PointOfInterest o = poiList.getModel().getElementAt(index);
                        currSelected = o;
                    }
                }
            }
        }
        /**
         * Check if the mouse click event was on the "Okay" JButton.
         */
        else if (e.getSource() instanceof JButton && e.getClickCount() == 1) {
            if (currMap.getIsCampusMap()) {
                /**
                 * Check if any POI was selected by the user. If no POI was selected, prompt user to select a POI to jump to on the map.
                 */
                if (currBuildingSelected == null) {
                    JOptionPane.showMessageDialog(null, "No POI/building was selected. Please select one to jump to.", "Point of Interest/Building Not Selected", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    /**
                     * Close the current frame and change the map to the floor map of the building selected.
                     */
                    frame.dispose();
                    currMap.changeMap(currProcessor.getFloorMapFromMapID(currBuildingSelected.getBuildingID(), 1));
                    currBuildingSelected = null;
                }
            }
            else {
                /**
                 * Check if any POI was selected by the user. If no POI was selected, prompt user to select a POI to jump to on the map.
                 */
                if (currSelected == null) {
                    JOptionPane.showMessageDialog(null, "No POI/building was selected. Please select one to jump to.", "Point of Interest/Building Not Selected", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    /**
                     * Close the current frame and go to currently selected POI.
                     */
                    frame.dispose();
                    POIInfoWindow poiWindow = new POIInfoWindow(currSelected.getID());

                    if (currSelected.getFloorID() == currMap.getFloorMapObject().getMapID()) {
                        currMap.navigateToPOI(currSelected.getID());
                        currSelected = null;
                    }
                    else {
                        currMap.changeMap(currProcessor.getFloorMapFromMapID(currSelected.getBuildingID(), currSelected.getFloorID()));
                        currMap.navigateToPOI(currSelected.getID());
                        currSelected = null;
                    }
                    poiWindow.getFrame().setLocationRelativeTo(currMap.getMapPanel());
                    poiWindow.setVisibleFrame();
                }
            }
        }
    }

    /**
     * Create the search results window frame.
     * @param list      the list of POIs that match the search
     * @param search    the searched text
     * @return
     */
    private void createFrame(ArrayList<PointOfInterest> list, String search) {
        /**
         * Create new frame.
         */
        frame = new JFrame("Search Results for \"" + search + "\"");
        frame.setSize(650, 250);
        /**
         * Create new panel to include text, JList, and JButton.
         */
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        /**
         * Output text on panel based on if it's a search from the campus map or from a floor.
         */
        if (currMap.getIsCampusMap()) {
            JLabel textLabel = (new JLabel("Double click on your desired building, then click \"Okay\" at the bottom."));
            textLabel.setBackground(Color.WHITE);
            textLabel.setFont(new Font("Georgia", Font.BOLD, 15));
            panel.add(textLabel, BorderLayout.PAGE_START);
        }
        else {
            JLabel textLabel = (new JLabel("Double click on your desired POI, then click \"Okay\" at the bottom."));
            textLabel.setBackground(Color.WHITE);
            textLabel.setFont(new Font("Georgia", Font.BOLD, 15));
            panel.add(textLabel, BorderLayout.PAGE_START);
        }
        /**
         * Create a new JList based on the search results list.
         */
        resultList = new JList<PointOfInterest>(list.toArray(new PointOfInterest[list.size()]));
        resultList.addMouseListener(this);
        /**
         * Style the JList
         */
        resultList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        resultList.setLayoutOrientation(JList.VERTICAL);
        resultList.setVisibleRowCount(-1);
        resultList.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        resultList.setBackground(Color.WHITE);
        /**
         * Style the contents of the JList
         */
        resultList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof PointOfInterest) {
                    PointOfInterest poi = (PointOfInterest) value;
                    /**
                     * Set text to the name followed by the floor ID of the POI
                     */
                    setText(poi.getName() + " (Floor: " + poi.getFloorID() + ")");
                    /**
                     * Set the font of the JList items to be larger and Georgia
                     */
                    setFont(new Font("Georgia", Font.PLAIN, 17));
                }
                return c;
            }
        });

        /**
         * To enable scrolling if all items in list can't be showed in the frame.
         */
        scrollPane = new JScrollPane(resultList);
        panel.add(scrollPane, BorderLayout.CENTER);
        /**
         * Create an "Okay" button at the bottom of the frame.
         */
        okay = new JButton("Okay");
        okay.setMaximumSize(new Dimension(200, 100));
        okay.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        okay.setBackground(Color.WHITE);

        /**
         * Add listeners to the "Okay" button
         */
        okay.addMouseListener(this);
        panel.add(okay, BorderLayout.PAGE_END);

        frame.add(panel);
    }

    /**
     * Method to create frame for BuildingPointOfInterest
     */
    public void createBuildingFrame(ArrayList<BuildingPointOfInterest> list, String search) {
        /**
         * Create new frame.
         */
        frame = new JFrame("Search Results for \"" + search + "\"");
        frame.setSize(650, 250);
        /**
         * Create new panel to include text, JList, and JButton.
         */
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        /**
         * Output text on panel based on if it's a search from the campus map or from a floor.
         */
        if (currMap.getIsCampusMap()) {
            JLabel textLabel = (new JLabel("Double click on your desired building, then click \"Okay\" at the bottom."));
            textLabel.setBackground(Color.WHITE);
            textLabel.setFont(new Font("Georgia", Font.BOLD, 15));
            panel.add(textLabel, BorderLayout.PAGE_START);
        }
        else {
            JLabel textLabel = (new JLabel("Double click on your desired POI, then click \"Okay\" at the bottom."));
            textLabel.setBackground(Color.WHITE);
            textLabel.setFont(new Font("Georgia", Font.BOLD, 15));
            panel.add(textLabel, BorderLayout.PAGE_START);
        }
        /**
         * Create a new JList based on the search results list.
         */
        buildingResultList = new JList<BuildingPointOfInterest>(list.toArray(new BuildingPointOfInterest[list.size()]));
        buildingResultList.addMouseListener(this);
        /**
         * Style the JList
         */
        buildingResultList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        buildingResultList.setLayoutOrientation(JList.VERTICAL);
        buildingResultList.setVisibleRowCount(-1);
        buildingResultList.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        buildingResultList.setBackground(Color.WHITE);
        /**
         * Style the contents of the JList
         */
        buildingResultList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof BuildingPointOfInterest) {
                    BuildingPointOfInterest poi = (BuildingPointOfInterest) value;
                    /**
                     * Set text to the name followed by the floor ID of the POI
                     */
                    setText(poi.getName());
                    /**
                     * Set the font of the JList items to be larger and Georgia
                     */
                    setFont(new Font("Georgia", Font.PLAIN, 17));
                }
                return c;
            }
        });

        /**
         * To enable scrolling if all items in list can't be showed in the frame.
         */
        scrollPane = new JScrollPane(buildingResultList);
        panel.add(scrollPane, BorderLayout.CENTER);
        /**
         * Create an "Okay" button at the bottom of the frame.
         */
        okay = new JButton("Okay");
        okay.setMaximumSize(new Dimension(200, 100));
        okay.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        okay.setBackground(Color.WHITE);

        /**
         * Add listeners to the "Okay" button
         */
        okay.addMouseListener(this);
        panel.add(okay, BorderLayout.PAGE_END);

        frame.add(panel);
    }

    /**
     * Unused method from the implemented class MouseListener
     */
    public void mousePressed(MouseEvent e) {
    }

    /**
     * Unused method from the implemented class MouseListener
     */
    public void mouseReleased(MouseEvent e) {
    }

    /**
     * Mouse cursor change on button
     */
    public void mouseEntered(MouseEvent e) {
        /**
         * Change mouse pointer to hand when hovering over the button
         */
        if (e.getSource() instanceof JButton) {
            ((JButton) e.getSource()).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            /**
             * Change background to gray
             */
            ((JButton) e.getSource()).setBackground(Color.LIGHT_GRAY);
        }
    }

    /**
     * Mouse cursor change on not on button
     */
    public void mouseExited(MouseEvent e) {
        /**
         * Change mouse pointer to default when not hovering over the button
         */
        if (e.getSource() instanceof JButton) {
            ((JButton) e.getSource()).setCursor(Cursor.getDefaultCursor());
            /**
             * Change background to white
             */
            ((JButton) e.getSource()).setBackground(Color.WHITE);
        }
    }
    
}
