package com.javan.dev;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @author: Riley Emma Gavigan <rgavigan@uwo.ca>
 * @version: 1.0
 * @since: 1.0
 */
public class POIComponent extends JPanel implements ActionListener, MouseListener, FocusListener {
    /**
     * Initialize private variables for the POIComponent
     */
    private JPanel POIPanel;
    private ArrayList<JPanel> POIPanels = new ArrayList<JPanel>(); // {POILayerPanel, FavouritePOIPanel, UserPOIPanel, OtherPOIPanel}
    private ArrayList<JScrollPane> POIScrollPanes = new ArrayList<JScrollPane>(); // {POILayerScrollPane, FavouritePOIScrollPane, UserPOIScrollPane, OtherPOIScrollPane}
    private JList<String> favouriteList;
    private ArrayList<JPanel> favouritePOIPanels = new ArrayList<JPanel>();
    private ArrayList<PointOfInterest> favouritePOIList;
    private ArrayList<String> favouritePOIStrings = new ArrayList<String>();
    private MapComponent mapPanel = MapComponent.getInstance();
    private ArrayList<JPanel> userPOIPanels = new ArrayList<JPanel>();
    private ArrayList<PointOfInterest> userPOIList;
    private ArrayList<String> userPOIStrings = new ArrayList<String>();
    private ArrayList<JPanel> otherPOIPanels = new ArrayList<JPanel>();
    private ArrayList<PointOfInterest> otherPOIList;
    private ArrayList<String> otherPOIStrings = new ArrayList<String>();

    /**
     * Constructor to create POIPanel that holds the four other panels vertically, allowing them to display their information
     */
    public POIComponent() {
        /**
         * Initialize list of POI panels and scroll panes
         */
        for (int i = 0; i < 4; i++) {
            POIPanels.add(new JPanel());
            POIScrollPanes.add(new JScrollPane());
            POIScrollPanes.get(i).setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            POIScrollPanes.get(i).setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        }

        /**
         * Initialize POIPanel
         */
        POIPanel = new JPanel();
        POIPanel.setLayout(new BoxLayout(POIPanel, BoxLayout.Y_AXIS));
        POIPanel.setBackground(Color.WHITE);
        POIPanel.setVisible(true);

        /**
         * Initialize the 4 panels contained in a loop with max dimensions to allow for scrolling and resizing
         */
        for (int i = 0; i < 4; i++) {
            POIScrollPanes.get(i).setOpaque(true);
            POIScrollPanes.get(i).setBackground(Color.WHITE);
            POIScrollPanes.get(i).setMaximumSize(new Dimension(100000, 1000));
            POIScrollPanes.get(i).setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            POIPanels.get(i).setMaximumSize(new Dimension(100000, 1000));
            POIPanels.get(i).setBackground(Color.WHITE);
            POIPanel.add(POIScrollPanes.get(i));
            POIScrollPanes.get(i).setViewportView(POIPanels.get(i));
        }


        /**
         * Use functions to add content to each of the four POI Panels
         */
        addPOILayerPanel();
        addFavouritePOIPanel();
        addUserPOIPanel();
        addOtherPOIPanel();

        /**
         * Make POI Panel visible
         */
        POIPanel.setVisible(true);
    }

    /**
     * Method to create a button for the POI Panel
     * @param string The text to be displayed on the button
     * @return JButton The button to be added to the POI Panel to toggle the visibility of the POI Panels
     */
    private JButton createPOIButton(String string) {
        JButton button = new JButton(string);
        button.setBackground(Color.WHITE);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setHorizontalAlignment(JLabel.CENTER);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setMaximumSize(new Dimension(100000, 50));
        button.setFont(new Font("Georgia", Font.BOLD, 20));
        button.addActionListener(this);
        button.addMouseListener(this);
        return button;
    }

    /**
     * Method to add the POI Layer Panel to the POI Panel
     * This panel contains the toggle buttons for the POI layers
     * The toggle buttons are styled and have action listeners for toggling
     * @param None
     * @return None
     */
    private void addPOILayerPanel() {
        /**
         * Create a centered title label 'POI Layers'
         */
        JPanel titleLayer = new JPanel();
        JButton POILayerButton = createPOIButton("POI Layers");
        titleLayer.setBackground(Color.WHITE);
        titleLayer.add(POILayerButton);
        /**
         * Create 5 toggle buttons for the different POI layers
         */
        JToggleButton accessibilityButton = createPOILayerToggleButton("Accessibility / Washrooms");
        JToggleButton restaurantsButton = createPOILayerToggleButton("Restaurants");
        JToggleButton classroomsButton = createPOILayerToggleButton("Classrooms");
        JToggleButton labsButton = createPOILayerToggleButton("Labs");
        JToggleButton userButton = createPOILayerToggleButton("User POI");

        /**
         * Put the buttons into POIPanels.get(0), the POI Layer Panel
         */
        GridBagLayout grid = new GridBagLayout();
        GridBagConstraints gridConstraints = new GridBagConstraints();

        POIPanels.get(0).setLayout(grid);
        /**
         * Have the title take up 2 columns 1 row
         * Have the first four buttons take up 2 rows, 2 in each row, and the last button take up 2 columns 1 row
         */
        gridConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.gridwidth = 2;
        gridConstraints.gridheight = 1;
        gridConstraints.weightx = 1;
        gridConstraints.weighty = 0.25;
        POIPanels.get(0).add(titleLayer, gridConstraints);

        gridConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        gridConstraints.gridwidth = 1;
        gridConstraints.gridheight = 1;
        gridConstraints.weightx = 0.5;
        gridConstraints.weighty = 0.25;
        POIPanels.get(0).add(accessibilityButton, gridConstraints);

        gridConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 1;
        gridConstraints.gridwidth = 1;
        gridConstraints.gridheight = 1;
        gridConstraints.weightx = 0.5;
        gridConstraints.weighty = 0.25;
        POIPanels.get(0).add(restaurantsButton, gridConstraints);

        gridConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 2;
        gridConstraints.gridwidth = 1;
        gridConstraints.gridheight = 1;
        gridConstraints.weightx = 0.5;
        gridConstraints.weighty = 0.25;
        POIPanels.get(0).add(classroomsButton, gridConstraints);

        gridConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 2;
        gridConstraints.gridwidth = 1;
        gridConstraints.gridheight = 1;
        gridConstraints.weightx = 0.5;
        gridConstraints.weighty = 0.25;
        POIPanels.get(0).add(labsButton, gridConstraints);

        gridConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 3;
        gridConstraints.gridwidth = 2;
        gridConstraints.gridheight = 1;
        gridConstraints.weightx = 1.0;
        gridConstraints.weighty = 0.25;
        POIPanels.get(0).add(userButton, gridConstraints);



        /**
         * Set the POI Layer Panel to be visible
         */
        POIPanels.get(0).setVisible(true);
    }

    /**
     * Method to add user favourites to the Favourite POI Panel.
     * Has a title 'Favourite POIs' and a list of favourite POIs that can be scrolled through
     * @param None
     * @return None
     */
    private void addFavouritePOIPanel() {
        /**
         * JPanel that separates the title from the list of favourite POIs
         */
        JPanel gridDisplay = new JPanel();
        POIPanels.get(1).setLayout(new BoxLayout(POIPanels.get(1), BoxLayout.Y_AXIS));


        /**
         * Create a centered title label 'Favourite POIs'
         */
        JPanel titleFavourite = new JPanel();
        JButton favouritePOIButton = createPOIButton("Favourite POIs");
        titleFavourite.add(favouritePOIButton);
        titleFavourite.setBackground(Color.WHITE);
        POIPanels.get(1).add(titleFavourite);

        /**
         * Update the list of Strings representing the favourite POIs 
         */
        updateFavouritePOIList();
        
        /**
         * Add the POIs to the panel
         */
        int numRows = addPOIsToPanel(gridDisplay, favouritePOIList);


        GridLayout grid = new GridLayout(numRows, 2);
        gridDisplay.setLayout(grid);
        POIPanels.get(1).add(gridDisplay);


        /**
         * Set the Favourite POI Panel to be visible
         */
        POIPanels.get(1).setVisible(true);
    }

    /**
     * Method to update the favourites POI list by calling DataProcessor to get a user's favourite POIs
     */
    public void updateFavouritePOIList() {

        /**
         * Get list of favourite PointOfInterest objects from DataProcessor
         */
         favouritePOIList = DataProcessor.getFavouritePOIs();

         /**
          * Empty out favouritePOIStrings before adding updated list of favourite POIs
          */
        favouritePOIStrings.clear();

        /**
         * Get the strings representing the name of each POI and put into favouritePOIStrings
         */
        for (PointOfInterest poi : favouritePOIList) {
            favouritePOIStrings.add(poi.getName());
        }
    }



    /**
     * Method to add POIs that were created by users to the user POI panel, the same way Favourites POIs are added
     */
    private void addUserPOIPanel() {
        /**
         * JPanel that separates the title from the list of user-created POIs
         */
        JPanel gridDisplay = new JPanel();
        POIPanels.get(2).setLayout(new BoxLayout(POIPanels.get(2), BoxLayout.Y_AXIS));

        /**
         * Create a centered title label 'User POIs'
         */
        JPanel titleUser = new JPanel();
        JButton userPOIButton = createPOIButton("User POIs");
        titleUser.setBackground(Color.WHITE);
        titleUser.add(userPOIButton);
        POIPanels.get(2).add(titleUser);

        /**
         * Update the list of Strings representing the user-created POIs 
         */
        updateUserPOIList();

        /**
         * Add the POIs to the panel
         */
        int numRows = addPOIsToPanel(gridDisplay, favouritePOIList);


        GridLayout grid = new GridLayout(numRows, 2);
        gridDisplay.setLayout(grid);
        POIPanels.get(2).add(gridDisplay);

        /**
         * Set the User POI Panel to be visible
         */
        POIPanels.get(2).setVisible(true);
    }

    private void updateUserPOIList() {
        /**
         * Get list of strings of user-created POIs from DataProcessor
         */
         userPOIList = DataProcessor.getUserPOIs();

         /**
          * Empty out userPOIStrings before adding updated list of user-created POIs
          */
        userPOIStrings.clear();

        /**
         * Get the strings representing the name of each POI and put into userPOIStrings
         */
        for (PointOfInterest poi : userPOIList) {
            userPOIStrings.add(poi.getName());
        }
    }

    /**
     * Method to add all other POIs to the other POI panel, the same way Favourites POIs and User POIs are added
     */
    private void addOtherPOIPanel() {
        /**
         * JPanel that separates the title from the list of other POIs
         */
        JPanel gridDisplay = new JPanel();
        POIPanels.get(3).setLayout(new BoxLayout(POIPanels.get(3), BoxLayout.Y_AXIS));


        /**
         * Create a centered title label 'Other POIs'
         */
        JPanel titleOther = new JPanel();
        titleOther.setBackground(Color.WHITE);
        JButton otherPOIButton = createPOIButton("All POIs");
        titleOther.add(otherPOIButton);
        POIPanels.get(3).add(titleOther);

        /**
         * Update the list of Strings representing the other POIs 
         */
        updateOtherPOIList();

        /**
         * Add the POIs to the panel
         */
        int numRows = addPOIsToPanel(gridDisplay, favouritePOIList);

        GridLayout grid = new GridLayout(numRows, 2);
        gridDisplay.setLayout(grid);
        POIPanels.get(3).add(gridDisplay);

        /**
         * Set the Other POI Panel to be visible
         */
        POIPanels.get(3).setVisible(true);
    }

    private void updateOtherPOIList() {
        /**
         * Get list of strings of other POIs from DataProcessor
         */
         otherPOIList = DataProcessor.getUniversalPOIs();

         /**
          * Empty out otherPOIStrings before adding updated list of other POIs
          */
        otherPOIStrings.clear();

        /**
         * Get the strings representing the name of each POI and put into otherPOIStrings
         */
        for (PointOfInterest poi : otherPOIList) {
            otherPOIStrings.add(poi.getName());
        }
    }

    /**
     * Function to loop through list of POIs and add them to the POI panel (showing their string that is visible and POI id invisible)
     * @param POIList
     * @return numRows to add
     */
    private int addPOIsToPanel(JPanel gridDisplay, ArrayList<PointOfInterest> POIList) {

        /**
         * Loop through all POIs in the list and add JPanels to the POI Panel on top of one another
         */
        for (PointOfInterest poi : POIList) {
            JPanel favouritePOI = new JPanel();
            JLabel favouritePOIName = new JLabel(poi.getName());
            favouritePOIName.setHorizontalAlignment(JLabel.CENTER);
            favouritePOIName.setFont(new Font("Georgia", Font.PLAIN, 15));
            favouritePOIName.setAlignmentX(Component.CENTER_ALIGNMENT);
            favouritePOI.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            favouritePOI.setBackground(Color.WHITE);
            favouritePOI.add(favouritePOIName);
            /**
             * Get the ID of the POI and add it to the name as invisible text
             */
            JLabel favouritePOIID = new JLabel(String.valueOf((poi.getID())));
            favouritePOIID.setVisible(false);
            favouritePOI.add(favouritePOIID);
            
            /**
             * Add listeners to each name to allow for clicking on the name to open the POI
             */
            favouritePOI.addMouseListener(this);
            gridDisplay.add(favouritePOI);
        }

        /**
         * Layout POI panel, adding two POIs per row
         */
        int numRows = POIList.size();
        if (numRows % 2 == 1) {
            numRows = numRows / 2 + 1;
        }
        else {
            numRows = numRows / 2;
        }
        return numRows;
    }

    /**
     * Getter to return the POIPanel
     * @return POIPanel
     */
    public JPanel getPOIPanel() {
        return POIPanel;
    }

    /**
     * Method to create toggle buttons for the POI layers panel that are styled and have action listeners for toggling
     * @return JToggleButton
     */
    private JToggleButton createPOILayerToggleButton(String text) {
        JToggleButton POILayerToggleButton = new JToggleButton(text);
        POILayerToggleButton.setBackground(Color.WHITE);
        POILayerToggleButton.setForeground(Color.BLACK);
        POILayerToggleButton.setOpaque(true);
        POILayerToggleButton.setPreferredSize(new Dimension(50, 30));
        POILayerToggleButton.setMaximumSize(new Dimension(75, 40));
        POILayerToggleButton.setMinimumSize(new Dimension(75, 40));
        POILayerToggleButton.setFont(new Font("Georgia", Font.PLAIN, 15));
        POILayerToggleButton.setFocusPainted(false);
        POILayerToggleButton.addActionListener(this);
        POILayerToggleButton.addMouseListener(this);
        POILayerToggleButton.setSelected(true);

        return POILayerToggleButton;
    }

    public void mouseEntered(MouseEvent e) {
        /**
         * When the mouse enters a POI Layer Toggle Button, change the colour of the button to light gray and make the cursor a hover cursor
         */
        if (e.getSource() instanceof JToggleButton) {
            JToggleButton button = (JToggleButton) e.getSource();
            button.setBackground(Color.LIGHT_GRAY);
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        /**
         * When the mouse enters the section of a POI name, change the colour of the POI to light gray and make the cursor a hover cursor
         */
        else if (e.getSource() instanceof JPanel) {
            JPanel panel = (JPanel) e.getSource();
            panel.setBackground(Color.LIGHT_GRAY);
            panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        /**
         * If hovering on one of the POI Layer Toggle Buttons, change the cursor to a hand cursor
         */
        else if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        
    }

    public void mouseExited(MouseEvent e) {
        /**
         * When the mouse exits a POI Layer Toggle Button, change the colour of the button to white and make the cursor a default cursor
         */
        if (e.getSource() instanceof JToggleButton) {
            JToggleButton button = (JToggleButton) e.getSource();
            button.setBackground(Color.WHITE);
            button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
        /**
         * When the mouse exits the section of a POI name, change the colour of the POI to white and make the cursor a default cursor
         */
        else if (e.getSource() instanceof JPanel) {
            JPanel panel = (JPanel) e.getSource();
            panel.setBackground(Color.WHITE);
            panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
        /**
         * If hovering on one of the POI Layer Toggle Buttons, change the cursor to a default cursor
         */
        else if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    
    }

    public void actionPerformed(ActionEvent e) {
        /**
         * When a POI Layer Toggle Button is pressed, toggle the layer on or off by sending the button's text to the MapPanel
         */
        if (e.getSource() instanceof JToggleButton) {
            JToggleButton button = (JToggleButton) e.getSource();
            if (button.isSelected()) {
                System.out.println("Selected POI Layer Being Sent To MapComponent: " + button.getText() + "");
                // TODO: mapPanel.enablePOILayer(button.getText());
            }
            else {
                System.out.println("Deselected POI Layer Being Sent To MapComponent: " + button.getText() + "");
                // TODO: mapPanel.disablePOILayer(button.getText());
            }
        }
        /**
         * When a POI button from createPOIButton is clicked, hide the gridDisplay panel associated with it
         */
        else if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            if (button.getText().equals("POI Layers")) {
                if (POIPanels.get(0).getComponent(1).isVisible()) {
                    /**
                     * Make everything but the button invisible and also resize the panel to be the size of the button
                     */
                    for (int i = 1; i < POIPanels.get(0).getComponentCount(); i++) {
                        POIPanels.get(0).getComponent(i).setVisible(false);
                    }
                    POIPanels.get(0).setPreferredSize(new Dimension(POIPanels.get(0).getPreferredSize().width, POIPanels.get(0).getComponent(0).getPreferredSize().height));

                    
                }
                else {
                    /**
                     * Restore to original state
                     */
                    for (int i = 1; i < POIPanels.get(0).getComponentCount(); i++) {
                        POIPanels.get(0).getComponent(i).setVisible(true);
                    }
                    POIPanels.get(0).setPreferredSize(new Dimension(POIPanels.get(0).getPreferredSize().width, POIPanels.get(0).getComponent(0).getPreferredSize().height + POIPanels.get(0).getComponent(1).getPreferredSize().height + POIPanels.get(0).getComponent(2).getPreferredSize().height + POIPanels.get(0).getComponent(3).getPreferredSize().height));
                }
            }
            else if (button.getText().equals("Favourite POIs")) {
                if (POIPanels.get(1).getComponent(1).isVisible()) {
                    for (int i = 1; i < POIPanels.get(1).getComponentCount(); i++) {
                        POIPanels.get(1).getComponent(i).setVisible(false);
                    }
                }
                else {
                    for (int i = 1; i < POIPanels.get(1).getComponentCount(); i++) {
                        POIPanels.get(1).getComponent(i).setVisible(true);
                    }
                }
            }
            else if (button.getText().equals("User POIs")) {
                if (POIPanels.get(2).getComponent(1).isVisible()) {
                    for (int i = 1; i < POIPanels.get(2).getComponentCount(); i++) {
                        POIPanels.get(2).getComponent(i).setVisible(false);
                    }
                }
                else {
                    for (int i = 1; i < POIPanels.get(2).getComponentCount(); i++) {
                        POIPanels.get(2).getComponent(i).setVisible(true);
                    }
                }
            }
            else if (button.getText().equals("All POIs")) {
                if (POIPanels.get(3).getComponent(1).isVisible()) {
                    for (int i = 1; i < POIPanels.get(3).getComponentCount(); i++) {
                        POIPanels.get(3).getComponent(i).setVisible(false);
                    }
                }
                else {
                    for (int i = 1; i < POIPanels.get(3).getComponentCount(); i++) {
                        POIPanels.get(3).getComponent(i).setVisible(true);
                    }
                }
            }
            /**
             * Refresh the POI Panel to show the changes
             */
            POIPanel.revalidate();
            POIPanel.repaint();
            
        }
    }

    /**
     * Method to handle mouse clicks on different POI names, sending the name of the POI to the MapPanel to be highlighted
     */
    public void mouseClicked(MouseEvent e) {
        /**
         * If the mouse is clicked on a POI name, send the name of the POI to the MapPanel to be highlighted
         */
        if (e.getSource() instanceof JPanel) {
            JPanel panel = (JPanel) e.getSource();
            JLabel label = (JLabel) panel.getComponent(1);
            String poiName = label.getText();
            int poiID = Integer.parseInt(poiName);

            System.out.println("Sending POI name to MapComponent: " + String.valueOf(poiID) + "");
            /**
             * Pass POI name to the MapPanel to be highlighted on the map
             */
            mapPanel.navigateToPOI(poiID);
        }
    }

    /**
     * Unused method from FocusListener
     */
    public void focusGained(FocusEvent e) {
    }

    /**
     * Unused method from FocusListener
     */
    public void focusLost(FocusEvent e) {
    }

    /**
     * Unused method from MouseListener
     */
    public void mousePressed(MouseEvent e) {
    }

    /**
     * Unused method from MouseListener
     */
    public void mouseReleased(MouseEvent e) { 
    }
}
