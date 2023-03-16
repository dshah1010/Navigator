package com.javan.dev;

// Import necessary libraries
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.MalformedURLException;
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
    private JButton okay;

    /** 
     * Track the currently selected POI on the list.
     */
    private PointOfInterest currSelected;

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
    public void mouseClicked(MouseEvent e) {
        /**
         * Check if the mouse click event was on an item on the JList.
         */
        if (e.getSource() instanceof JList && e.getClickCount() == 1) {
            JList<PointOfInterest> theList = (JList) e.getSource();
            /**
             * Restrict the clicking for within the items available in the list. The mouse click on any empty space within the JList scroll pane will do nothing.
             */
            Rectangle r = theList.getCellBounds(0, theList.getLastVisibleIndex());
            if (r != null && r.contains(e.getPoint())) { 
                /**
                 * Set currently selected POI to the POI selected by the user (by click).
                 */
                int index = theList.locationToIndex(e.getPoint()); 
                if (index >= 0) {
                    PointOfInterest o = theList.getModel().getElementAt(index);
                    currSelected = o;
                }
            }
        }
        /**
         * Check if the mouse click event was on the "Okay" JButton.
         */
        else if (e.getSource() instanceof JButton && e.getClickCount() == 1) {
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
                System.out.println("Going to " + currSelected + " on the map."); //test
                POIInfoWindow poiWindow = new POIInfoWindow(currSelected);

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
        frame.setSize(500, 250);
        /**
         * Create new panel to include text, JList, and JButton.
         */
        panel = new JPanel(new BorderLayout());
        /**
         * Output text on panel based on if it's a search from the campus map or from a floor.
         */
        if (currMap.getIsCampusMap()) {
            panel.add(new JLabel("Double click on your desired building, then click \"Okay\" at the bottom."), BorderLayout.PAGE_START);
        }
        else {
            panel.add(new JLabel("Double click on your desired POI, then click \"Okay\" at the bottom."), BorderLayout.PAGE_START);
        }
        /**
         * Create a new JList based on the search results list.
         */
        resultList = new JList(list.toArray());
        resultList.addMouseListener(this);
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
     * Unused method from the implemented class MouseListener
     */
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * Unused method from the implemented class MouseListener
     */
    public void mouseExited(MouseEvent e) {
    }
    
}
