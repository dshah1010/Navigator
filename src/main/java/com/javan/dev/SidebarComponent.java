package com.javan.dev;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author: Riley Emma Gavigan <rgavigan@uwo.ca>, Jake Choi <jchoi492@uwo.ca>
 * @version: 1.1
 * @since: 1.0
 */
public final class SidebarComponent extends JPanel implements ActionListener, MouseListener, FocusListener, KeyListener {
    /**
     * Initialize private variables for the UI
     */
    private JPanel sidebarPanel;
    private JToggleButton poiList;
    private JToggleButton weatherInfo;
    private JPanel poiListPanel;
    private JPanel poiListContentPanel;
    private JPanel weatherInfoContentPanel;
    private JPanel weatherInfoPanel;
    private JTextField searchText;
    private JButton searchButton;
    private Weather weather;
    private JPanel searchBar;

    /**
     * DataProcessor instance to be used
     */
    private DataProcessor processor = DataProcessor.getInstance();

    /**
     * Private Singleton instance of the SidebarCOmponent
     */
    private static SidebarComponent INSTANCE;

    /**
     * private instance of MapComponent to receive information from
     */
    private MapComponent mapComponent = MapComponent.getInstance();

    /**
     * Constructor to initialize the sidebar component
     * @throws IOException
     * @throws MalformedURLException
     */
    private SidebarComponent() throws MalformedURLException, IOException {
        /**
         * Create a new JPanel for the map
         */
        sidebarPanel = new JPanel();
        sidebarPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        sidebarPanel.setBackground(Color.WHITE);

        /**
         * Create 2 buttons that can toggle their state on and off for display
         * 1. POI Information
         * 2. Weather Information
         */
        poiList = createToggleButton("Points of Interest");
        weatherInfo = createToggleButton("Weather Information");

        /**
         * Create a JPanel for the Search Bar, Points of Interest Content and Weather Content
         */
        searchBar = createSearchBar();
        createPOIListPanel();
        createWeatherInfoPanel();

        /**
         * Display the three panels on top of one another with GridBagLayout, with the search bar at the top, POI Content in the middle and Weather Content at the bottom. Let them take up all the horizontal and vertical space they need, with the search bar just taking up its size.
         */
        sidebarPanel.setLayout(new GridBagLayout());
        GridBagConstraints gridConstraints = new GridBagConstraints();

        /**
         * Adding search bar
         */
        gridConstraints.fill = GridBagConstraints.BOTH;
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.weightx = 1;
        gridConstraints.weighty = 0;
        sidebarPanel.add(searchBar, gridConstraints);

        /**
         * Adding POI Content
         */
        gridConstraints.gridy = 1;
        gridConstraints.weighty = 1;
        sidebarPanel.add(poiListContentPanel, gridConstraints);

        /**
         * Adding Weather Content
         */
        gridConstraints.gridy = 2;
        gridConstraints.weighty = 0;
        if (weatherInfoContentPanel != null) {
            sidebarPanel.add(weatherInfoContentPanel, gridConstraints);
        }



        /**
         * Set visible
         */
        sidebarPanel.setVisible(true);
    }

    /**
     * Getter for the instance of SidebarComponent
     * @return SidebarComponent instance
     */
    public static SidebarComponent getInstance() {
        if (INSTANCE == null) {
            try {
                INSTANCE = new SidebarComponent();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return INSTANCE;
    }


    /**
     * Getter for sidebarPanel
     * @param None
     * @return JPanel of the sidebar
     */
    public JPanel getSidebarPanel() {
        return sidebarPanel;
    }

    /**
     * Method to change cursor when search button is entered
     * @param e
     * @return None
     */
    public void mouseEntered(MouseEvent e) {
        /**
         * If mouse enters the search button, change to a hold icon
         */
        if (e.getSource() == searchButton) {
            searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        /**
         * If the mouse hovers over either Points of Interest or Weather Information buttons, change cursor to hold and background to gray
         */
        if (e.getSource() == poiList || e.getSource() == weatherInfo) {
            ((Component) e.getSource()).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            ((JComponent) e.getSource()).setBackground(Color.LIGHT_GRAY);
        }
        
    }

    /**
     * Method to create a search bar for searching within the MapComponent for different Points of Interest in the application
     * @param None
     * @return JPanel
     */
    public JPanel createSearchBar() {
        /**
         * JPanel to hold the entire search bar
         */
        JPanel searchBarPanel = new JPanel();
        searchBarPanel.setLayout(new BoxLayout(searchBarPanel, BoxLayout.X_AXIS));
        searchBarPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        searchBarPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30)); // Occupy entire horizontal space but limit vertical

        /**
         * Create a JTextField for the search bar
         */
        searchText = new JTextField();
        searchText.addKeyListener(this);
        searchText.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30)); // Occupy entire horizontal space but limit vertical
        /**
         * Style the text field
         */
        searchText.setFont(new Font("Georgia", Font.PLAIN, 12));
        searchText.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        searchText.setText("Search for a Point of Interest");

        /**
         * Add listeners to the search bar text field
         */
        searchText.addMouseListener(this);
        searchText.addFocusListener(this);

        /**
         * Create a JButton for the search bar
         */
        searchButton = new JButton();
        ImageIcon searchIcon = new ImageIcon("data/images/search.png");
        /**
         * Limit size of search icon and then button
         */
        searchIcon = new ImageIcon(searchIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        searchButton.setIcon(searchIcon);
        searchButton.setMaximumSize(new Dimension(30, 30));
        searchButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        searchButton.setBackground(Color.WHITE);

        /**
         * Add listeners to the search button
         */
        searchButton.addActionListener(this);
        searchButton.addMouseListener(this);

        searchBarPanel.add(searchText);
        searchBarPanel.add(searchButton);

        /**
         * Return the JPanel
         */
        return searchBarPanel;
    }
    
    /**
     * Method to create POI List Panel
     * @param None
     * @return None
     */
    public void createPOIListPanel() {
        poiListContentPanel = new JPanel();
        poiListContentPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        POIComponent poiComponent = POIComponent.getInstance();
        poiListPanel = poiComponent.getPOIPanel();
        poiListPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        poiListContentPanel.setBackground(Color.WHITE);

        /**
         * Create GridBagLayout to add content
         */
        createPOIListGridBagLayout();
    }

    /**
     * Method to make it so the poiList button takes up the entire vertical space of the sidebar
     */
    public void increasePOIButtonSize() {
        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.weighty = 1;
        gridConstraints.weightx = 1;
        gridConstraints.fill = GridBagConstraints.BOTH;
        poiListContentPanel.add(poiList, gridConstraints);
    }

    /**
     * Method to create GridLayout for POI List Panel
     */
    public void createPOIListGridBagLayout() {
        poiListContentPanel.setLayout(new GridBagLayout());
        /**
         * Create a GridBagConstraints object to set constraints for the GridBagLayout
         */
        GridBagConstraints gridConstraints = new GridBagConstraints();

        /**
         * Add poiList to GridLayout
         */
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.weightx = 1;
        gridConstraints.weighty = 0;
        gridConstraints.fill = GridBagConstraints.HORIZONTAL;
        poiListContentPanel.add(poiList, gridConstraints);

        /**
         * Add poiListPanel to GridLayout
         */
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        gridConstraints.weightx = 1;
        gridConstraints.weighty = 1;
        gridConstraints.fill = GridBagConstraints.BOTH;
        poiListContentPanel.add(poiListPanel, gridConstraints);
    }


    /**
     * Method to create Weather Info Panel
     * @throws IOException
     * @throws MalformedURLException
     * @param None
     * @return None
     */
    public void createWeatherInfoPanel() throws MalformedURLException, IOException {
        weatherInfoContentPanel = new JPanel();
        weatherInfoPanel = new JPanel();
        weather = Weather.getInstance();
        weather.parseWeather();
        JPanel weatherContent = weather.addWeatherInfo();

        /**
         * If there is no weather information, do not add the weather panel to the sidebar
         */
        if (weatherContent == null) {
            weatherInfoContentPanel = null;
            weatherInfoPanel = null;
            return;
        }

        /**
         * Add content to weather panel
         */
        weatherInfoPanel.add(weatherContent);
        weatherInfoPanel.setLayout(new BoxLayout(weatherInfoPanel, BoxLayout.Y_AXIS));
        weatherInfoPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
        weatherInfoContentPanel.add(weatherInfo);
        weatherInfoContentPanel.add(weatherInfoPanel);
        weatherInfoContentPanel.setBackground(Color.WHITE);
        weatherInfoContentPanel.setLayout(new GridLayout(2, 1));
    }

    /**
     * Method to handle the action of a button being toggled on or off
     * @param e
     * @return None
     */
    public void actionPerformed(ActionEvent e) {
        /**
         * Button is a JToggleButton
         */
        if (e.getSource() instanceof JToggleButton) {
            JToggleButton button = (JToggleButton) e.getSource();
            if (button.isSelected()) {
                /**
                 * Get the name of the button and show the JFrame associated with it
                 */
                String buttonName = button.getText();
                if (buttonName.equals("Points of Interest")) {
                    poiListPanel.setVisible(true);
                    createPOIListGridBagLayout();
                } else if (buttonName.equals("Weather Information")) {
                    weatherInfoPanel.setVisible(true);
                }
            } else {
                /**
                 * Set the JToggleButton background colour and text colour
                 */
                button.setBackground(Color.WHITE);
                button.setForeground(Color.BLACK);
                /**
                 * Get the name of the button and hide the JFrame associated with it
                 */
                String buttonName = button.getText();
                if (buttonName.equals("Points of Interest")) {
                    poiListPanel.setVisible(false);
                    increasePOIButtonSize();
                } else if (buttonName.equals("Weather Information")) {
                    weatherInfoPanel.setVisible(false);
                }
            }
        }
        /**
         * Button is the search button
         */
        else if (e.getSource() instanceof JButton) {
            searchPOI();
        }
    }

    /**
     * Method to create a JToggleButton and style it, as well as add event listeners
     * @param text
     * @return
     */
    public JToggleButton createToggleButton(String text) {
        JToggleButton button = new JToggleButton(text);
        /**
         * Add listeners to the button for interactive UI
         */
        button.addActionListener(this);
        button.addMouseListener(this);
        /**
         * Add styling (border, background/foreground, font, size, etc.)
         */
        button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        button.setFocusPainted(false);
        button.setFont(new Font("Georgia", Font.PLAIN, 12));
        button.setPreferredSize(new Dimension(150, 30));
        button.setMaximumSize(new Dimension(150, 30));
        button.setMinimumSize(new Dimension(150, 30));
        button.setSelected(true);

        return button;
    }

    /**
     * Method to change search bar text when it is focused on
     * @param e
     * @return None
     */
    public void focusGained(FocusEvent e) {
        /**
         * Set to empty string when the search bar is clicked
         */
        if (e.getSource() == searchText) {
            ((JTextField) e.getSource()).setText("");
        }
    }

    /**
     * Method to perform search
     */
    public void searchPOI() {
        JButton button = searchButton;
        /**
         * Get the text from the search bar and then empty the search bar
         */
        String text = searchText.getText();
        searchText.setText("");
        /**
         * If the text is not empty / the default text, search for the POI
         */
        if ((!text.equals("")) && (!text.equals("Search for a Point of Interest"))) {
            /**
             * Check if user is currently on campus map or a floor map.
             */
            ArrayList<PointOfInterest> searchMatch = new ArrayList<PointOfInterest>();
            if (mapComponent.getIsCampusMap()) {
                /**
                 * Temporary solution for getting the list of all buildings (as POIs haven't been created for them yet).
                 */
                ArrayList<PointOfInterest> buildingList = processor.getUniversalPOIs(true, mapComponent.getUser().getUserID());
                /**
                 * Compare the search to the available buildings on the campus.
                 */
                for (PointOfInterest building : buildingList) {
                    if (building.getName().toLowerCase().contains(text.toLowerCase())) {
                        searchMatch.add(building);
                    }
                }
            }
            else {
                /**
                 * Get current building ID and floor ID to know where we are on the map for poi searching purposes.
                 * These will be used when searching for POIs.
                 */
                int currBuildingID = mapComponent.getFloorMapObject().getBuildingID();
                int currFloorID = mapComponent.getFloorMapObject().getMapID();
                /**
                 * Get the list of all POIs. Initialize new ArrayList of POIs to store the current building's POIs only, and the searchResults POI list.
                 */
                ArrayList<PointOfInterest> pois = processor.getUniversalPOIs(false, mapComponent.getUser().getUserID());
                for (PointOfInterest poi : pois) {
                    /**
                     * Convert the room number of the current poi to a string.
                     */
                    String roomNumString = String.valueOf(poi.getRoomNumber());
                    /**
                     * First check if the current poi is on the current floor of the current building.
                     * Check if any of its metadata matches the search. If so, add it. If not, do not add it to the searchResults array.
                     */
                    if (poi.getBuildingID() == currBuildingID && poi.getFloorID() == poi.getFloorID()){
                        /**
                         * Compare search with the name, description, and room number of the POI's metadata.
                         */
                        if (poi.getName().toLowerCase().contains(text.toLowerCase()) 
                            || poi.getDescription().toLowerCase().contains(text.toLowerCase()) 
                            || roomNumString.toLowerCase().contains(text)) {
                                searchMatch.add(poi);
                        }
                    }
                }

                if (searchMatch.size() == 0) {
                    ArrayList<PointOfInterest> currBuildingPOIS = JsonReader.buildingPOIS(mapComponent.getUser().getUserID(), currBuildingID);
                    for (PointOfInterest poi : currBuildingPOIS) {
                        /**
                         * Convert the room number of the current poi to a string.
                         */
                        String roomNumString = String.valueOf(poi.getRoomNumber());
                        if (poi.getName().toLowerCase().contains(text.toLowerCase()) 
                            || poi.getDescription().toLowerCase().contains(text.toLowerCase()) 
                            || roomNumString.toLowerCase().contains(text)) {
                                searchMatch.add(poi);
                        }
                    }
                }

            }
            /**
             * If there are at most 20 POIs found on the current map, or within the building.
             */
            if (searchMatch.size() > 0 && searchMatch.size() <= 20) {
                /**
                 * Create new Search Results Window listning all the results.
                 */
                SearchResultsWindow resultWindow = new SearchResultsWindow(searchMatch, text.toLowerCase(), mapComponent, processor);
                resultWindow.getFrame().setLocationRelativeTo(mapComponent.getMapPanel());
                resultWindow.openSearchResults();
            }
            /**
             * If there are more than 20 POIs found on the current map, or within the building.
             */
            else if (searchMatch.size() > 20) {
                /**
                 * Display a JOptionPane to the user to inform them that the search was too broad.
                 */
                JOptionPane.showMessageDialog(null, "Too broad of a search, please narrow down your query.", "Point of Interest Not Found", JOptionPane.ERROR_MESSAGE);
            }
            else {
                /**
                 * Display a JOptionPane to the user to inform them that the POI was not found
                 */
                JOptionPane.showMessageDialog(null, "The Point of Interest you searched for was not found.", "Point of Interest Not Found", JOptionPane.ERROR_MESSAGE);
            }
        }
        else {
            /**
             * Inform user that the search bar is empty
             */
            JOptionPane.showMessageDialog(null, "The search bar is empty. Please enter a Point of Interest to search for.", "Search Bar Empty", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Method to change search bar text when it is no longer focused on
     * @param e
     * @return None
     */
    public void focusLost(FocusEvent e) {
        /**
         * Set to "Search for a Point of Interest" when the search bar is no longer clicked and is empty
         */
        if (e.getSource() == searchText) {
            if (((JTextField) e.getSource()).getText().length() == 0) {
                ((JTextField) e.getSource()).setText("Search for a Point of Interest");
            }
        }   
    }

    public void mouseExited(MouseEvent e) {
        /**
         * When the mouse exits from the POI or Weather button, change the background colour to white
        */
        if (e.getSource() instanceof JToggleButton) {
            JToggleButton button = (JToggleButton) e.getSource();
            button.setBackground(Color.WHITE);
        }
    }
    /**
     * Getter for POI list content panel
     * @return poiListContentPanel
     */
    public JPanel getPOIListContentPanel() {
        return poiListContentPanel;
    }

    /**
     * Getter for Weather info content panel
     * @return weatherInfoContentPane
     */
    public JPanel getWeatherInfoContentPanel() {
        return weatherInfoContentPanel;
    }

    /**
     * Getter for POI list button
     * @return poiList
     */
    public JToggleButton getPOIListBtn() {
        return poiList;
    }

    /**
     * Getter for weather info toggle button
     * @return weatherInfo
     */
    public JToggleButton getWeatherInfoBtn() {
        return weatherInfo;
    }

    /**
     * Unused method from MouseListener interface
     */
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * Unused method from MouseListener interface
     */
    public void mousePressed(MouseEvent e) {
    }

    /**
     * Unused method from MouseListener interface
     */
    public void mouseReleased(MouseEvent e) {
    }

    /**
     * Complete search when Enter hit
     */
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            searchPOI();
        }
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }
}