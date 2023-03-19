package com.javan.dev;

// Import Swing Components
import javax.swing.*;
import java.awt.*;


/**
 * @author: Riley Emma Gavigan <rgavigan@uwo.ca>
 * @version: 1.0
 * @since: 1.0
 */
public final class UserHelp extends JFrame {
    /**
     * Initialize private variables for the JFrame and its contents
     */
    private JFrame frame;
    private JTabbedPane tabbedPane;
    private JPanel general;
    private JPanel login;
    private JPanel navigate;
    private JPanel search;
    private JPanel favourite;
    private JPanel browse;
    private JLabel generalTitle;
    private JLabel loginTitle;
    private JLabel navigateTitle;
    private JLabel searchTitle;
    private JLabel favouriteTitle;
    private JLabel browseTitle;

    /**
     * Initialize singleton instance of UserHelp
     */
    private static UserHelp INSTANCE;

    /**
     * Constructor to create UserHelp JFrame Window with JPanels / tabs for each section of the help menu for the user to browse
     * @param None
     */
     private UserHelp() {
        /**
         * Create JFrame Window
         */
        frame = new JFrame("Help Menu");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 563);
        frame.setMinimumSize(new Dimension(800, 450));

        /**
         * Add icon to the UI frame (UWO Logo)
         */
        frame.setIconImage(new ImageIcon("data/images/icon.png").getImage());

        /**
         * JTabbedPane to hold JPanels for each menu section
         */
        tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(Color.WHITE);

        /**
         * JPanels for different menu sections (General, Logging In, Navigating, Searching, Favouiriting POIs, Browsing Maps)
         */
        general = new JPanel();
        login = new JPanel();
        navigate = new JPanel();
        search = new JPanel();
        favourite = new JPanel();
        browse = new JPanel();

        /**
         * Creating the tabs
         */
        createGeneralTab();
        createLoginTab();
        createNavigateTab();
        createSearchTab();
        createFavouritesTab();
        createBrowseTab();

        /**
         * Add JPanels to the JTabbedPane as well as the JTabbedPane to the JFrame
         */
        tabbedPane.addTab("General", general);
        tabbedPane.addTab("Logging In", login);
        tabbedPane.addTab("Navigating", navigate);
        tabbedPane.addTab("Searching", search);
        tabbedPane.addTab("Favouriting POIs", favourite);
        tabbedPane.addTab("Browsing Maps", browse);
        frame.add(tabbedPane);
     }

    /**
     * Getter for the Singleton Instance of UserHelp
     * @param None
     * @return UserHelp instance
    */
    public static UserHelp getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserHelp();
        }
        return INSTANCE;
    }

    /**
     * Getter for the JFrame created
     * @param None
     * @return Jframe frame
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Method to open the user help menu
     * @param None
     * @return None
     */
    public void openHelpMenu() {
        frame.setVisible(true);
    }

    /**
     * Method to set background
     * @param Jpanel panel
     * @return None
     */
    public void setBackground(JPanel panel) {
        panel.setBackground(Color.WHITE);
    }

    /**
     * Method to create the general tab
     * @param None
     * @return None
     */
    private void createGeneralTab() {
        /**
         * Setting layout of general tab and title
         */
        general.setLayout(new BoxLayout(general, BoxLayout.Y_AXIS));
        setBackground(general);
        generalTitle = new JLabel("Welcome to the Enhanced Campus Navigation System!");
        generalTitle.setFont(new Font("Georgia", Font.BOLD, 25));
        general.add(generalTitle);

        /**
         * Simply adding content via JLabels to the general tab
         */
        general.add(new JLabel("This application was created by Riley Gavigan, Jake Choi, Bradley McGlynn, Deep Shah, and Dylan Sta Ana"));
        general.add(Box.createVerticalStrut(25));
        general.add(new JLabel("This application is designed to help you navigate the Western University campus."));
        general.add(new JLabel("To begin, you must log in to the application with your username and password."));
        general.add(new JLabel("For information regarding logging in, creating an account, and retrieving your password: Navigate to the \"Logging In\" tab."));
        general.add(Box.createVerticalStrut(25));
        general.add(new JLabel("Once you have logged in, you will be able to navigate the campus using the map."));
        general.add(new JLabel("For information regarding navigating the campus: Navigate to the \"Navigating\" tab."));
        general.add(Box.createVerticalStrut(25));
        general.add(new JLabel("You are also able to search for different POIs within the Campus Map and Floor maps."));
        general.add(new JLabel("For information regarding searching for POIs: Navigate to the \"Searching\" tab."));
        general.add(Box.createVerticalStrut(25));
        general.add(new JLabel("You can also favourite POIs to easily access them later."));
        general.add(new JLabel("For information regarding favouriting POIs: Navigate to the \"Favouriting POIs\" tab."));
        general.add(Box.createVerticalStrut(25));
        general.add(new JLabel("You can also browse the campus map and floor maps."));
        general.add(new JLabel("For information regarding browsing the campus map and floor maps: Navigate to the \"Browsing Maps\" tab."));
    }

    /**
     * Method to create the login tab
     * @param None
     * @return None
     */
    private void createLoginTab() {
        /**
         * Setting layout of login tab and title
         */
        login.setLayout(new BoxLayout(login, BoxLayout.Y_AXIS));
        setBackground(login);
        loginTitle = new JLabel("Logging In");
        loginTitle.setFont(new Font("Georgia", Font.BOLD, 25));
        login.add(loginTitle);

        /**
         * Simply adding content via JLabels to the general tab
         */
        login.add(new JLabel("To log in to the application, you must enter your username and password."));
        login.add(new JLabel("If you do not have an account, you can create one by clicking the \"Create Account\" button."));
        login.add(new JLabel("If you have forgotten your password, you can retrieve it by clicking the \"Forgot Password\" button."));
        login.add(Box.createVerticalStrut(25));
        login.add(new JLabel("To create an account, you must enter your first name, last name, username, and password."));
        login.add(new JLabel("You must also enter your email address and phone number."));
        login.add(new JLabel("You can also enter your student number, but it is not required."));
        login.add(Box.createVerticalStrut(25));
        login.add(new JLabel("To retrieve your password, you must enter your username and email address."));
        login.add(new JLabel("You will then receive an email with your password."));
    }

    /**
     * Method to create the navigate tab
     * @param None
     * @return None
     */
    private void createNavigateTab() {
        /**
         * Setting layout of navigate tab and title
         */
        navigate.setLayout(new BoxLayout(navigate, BoxLayout.Y_AXIS));
        setBackground(navigate);
        navigateTitle = new JLabel("Navigating");
        navigateTitle.setFont(new Font("Georgia", Font.BOLD, 25));
        navigate.add(navigateTitle);

        /**
         * Simply adding content via JLabels to the general tab
         */
        navigate.add(new JLabel("To navigate the campus, you can either use the search feature or the navigation feature."));
        navigate.add(new JLabel("To use the search feature, you can either click on the search icon on the right side of the screen, or click on the search bar at the top of the screen."));
        navigate.add(new JLabel("You can then enter the name of the POI you wish to navigate to."));
        navigate.add(new JLabel("You can also use the search feature to navigate to a floor map."));
        navigate.add(Box.createVerticalStrut(25));
        navigate.add(new JLabel("To use the navigation feature, you can either click on the navigation icon on the right side of the screen, or click on the navigation bar at the top of the screen."));
        navigate.add(new JLabel("You can then enter the name of the POI you wish to navigate to."));
        navigate.add(new JLabel("You can also use the navigation feature to navigate to a floor map."));
        navigate.add(Box.createVerticalStrut(25));
        navigate.add(new JLabel("To navigate to a floor map, you can either click on the floor map icon on the right side of the screen, or click on the floor map bar at the top of the screen."));
        navigate.add(new JLabel("You can then enter the name of the floor map you wish to navigate to."));
        navigate.add(Box.createVerticalStrut(25));
        navigate.add(new JLabel("To navigate to a different building within a floor map, you can either click on the floor map icon on the right side of the screen, or click on the floor"));
    }

    /**
     * Method to create the search tab
     * @param None
     * @return None
     */
    private void createSearchTab() {
        /**
         * Setting layout of search tab and title
         */
        search.setLayout(new BoxLayout(search, BoxLayout.Y_AXIS));
        setBackground(search);
        searchTitle = new JLabel("Searching");
        searchTitle.setFont(new Font("Georgia", Font.BOLD, 25));
        search.add(searchTitle);

        /**
         * Simply adding content via JLabels to the general tab
         */
        search.add(new JLabel("To search for a POI, you can either click on the search icon on the right side of the screen, or click on the search bar at the top of the screen."));
        search.add(new JLabel("You can then enter the name of the POI you wish to search for."));
        search.add(Box.createVerticalStrut(25));
        search.add(new JLabel("To search for a floor within a floor map, you can either click on the floor map icon on the right side of the screen, or click on the floor map bar at the top of the screen."));
        search.add(new JLabel("You can then enter the name of the floor you wish to search for."));
        search.add(Box.createVerticalStrut(25));
        search.add(new JLabel("To search for a building within a floor map, you can either click on the floor map icon on the right side of the screen, or click on the floor map bar at the top of the screen."));
        search.add(new JLabel("You can then enter the name of the building you wish to search for."));
    }

    /**
     * Method to create the favourites tab
     * @param None
     * @return None
     */
    private void createFavouritesTab() {
        /**
         * Setting layout of favourites tab and title
         */
        favourite.setLayout(new BoxLayout(favourite, BoxLayout.Y_AXIS));
        setBackground(favourite);
        favouriteTitle = new JLabel("Favourites");
        favouriteTitle.setFont(new Font("Georgia", Font.BOLD, 25));
        favourite.add(favouriteTitle);

        /**
         * Simply adding content via JLabels to the general tab
         */
        favourite.add(new JLabel("To add a POI to your favourites, you can either click on the favourites icon on the right side of the screen, or click on the favourites bar at the top of the screen."));
        favourite.add(new JLabel("You can then enter the name of the POI you wish to add to your favourites."));
        favourite.add(Box.createVerticalStrut(25));
        favourite.add(new JLabel("To add a floor map to your favourites, you can either click on the favourites icon on the right side of the screen, or click on the favourites bar at the top of the screen."));
        favourite.add(new JLabel("You can then enter the name of the floor map you wish to add to your favourites."));
        favourite.add(Box.createVerticalStrut(25));
        favourite.add(new JLabel("To add a building within a floor map to your favourites, you can either click on the favourites icon on the right side of the screen, or click on the favourites bar at the top of the screen."));
        favourite.add(new JLabel("You can then enter the name of the building you wish to add to your favourites."));
        favourite.add(Box.createVerticalStrut(25));
        favourite.add(new JLabel("To remove a POI from your favourites, you can either click on the favourites icon on the right side of the screen, or click on the favourites bar at the top of the screen."));
        favourite.add(new JLabel("You can then enter the name of the POI you wish to remove from your favourites."));
        favourite.add(Box.createVerticalStrut(25));
        favourite.add(new JLabel("To favourite a POI within a floor map, you can click on the POI on the right side of the screen, use the search feature, or navigate to the POI."));
        favourite.add(new JLabel("You will then see a star icon that allows you to favourite the POI and store it in your favourites list.."));
    }

    /**
     * Method to create the browse tab
     * @param None
     * @return None
     */
    private void createBrowseTab() {
        /**
         * Setting layout of browse tab and title
         */
        browse.setLayout(new BoxLayout(browse, BoxLayout.Y_AXIS));
        setBackground(browse);
        browseTitle = new JLabel("Browsing Maps");
        browseTitle.setFont(new Font("Georgia", Font.BOLD, 25));
        browse.add(browseTitle);

        /**
         * Simply adding content via JLabels to the general tab
         */
        browse.add(new JLabel("You can browse the campus map and floor maps."));
        browse.add(new JLabel("To browse the campus map, you can either look at it as you open the application and log in, or go back from a floor map to the campus map by clicking the \"Back\" button"));
        browse.add(new JLabel("The options available for maps you are free to browse are displayed on the right side of your screen on the Campus Map tab, showing the available floor plans."));
    }
}   
