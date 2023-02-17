package com.javan.dev;

// Import Swing Components
import javax.swing.*;
import java.awt.*;


/**
 * @author: Riley Emma Gavigan <rgavigan@uwo.ca>
 * @version: 1.0
 * @since: 1.0
 */
public class UserHelp extends JFrame {
    /**
     * Initialize private variables for the UI component
     */
    private JFrame frame;

    /**
     * Constructor to create UserHelp JFrame Window with JPanels / tabs for each section of the help menu for the user to browse
     */
     public UserHelp() {
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
        frame.setIconImage(new ImageIcon("data\\images\\icon.png").getImage());

        /**
         * JTabbedPane to hold JPanels for each menu section
         */
        JTabbedPane tabbedPane = new JTabbedPane();

        /**
         * JPanels for different menu sections (General, Logging In, Navigating, Searching, Favouiriting POIs, Browsing Maps)
         */
        JPanel general = new JPanel();
        JPanel login = new JPanel();
        JPanel navigate = new JPanel();
        JPanel search = new JPanel();
        JPanel favourite = new JPanel();
        JPanel browse = new JPanel();

        /**
         * Setting layout of general tab and title
         */
        BoxLayout box = new BoxLayout(general, BoxLayout.Y_AXIS);
        general.setLayout(box);
        JLabel generalTitle = new JLabel("Welcome to the Enhanced Campus Navigation System!");
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

        /**
         * Setting layout of login tab and title
         */
        BoxLayout box2 = new BoxLayout(login, BoxLayout.Y_AXIS);
        login.setLayout(box2);
        JLabel loginTitle = new JLabel("Logging In");
        loginTitle.setFont(new Font("Georgia", Font.BOLD, 25));
        login.add(loginTitle);

        /**
         * Simply adding content via JLabels to the general tab
         */
        login.add(new JLabel("To log in to the application, you must have an account."));
        login.add(Box.createVerticalStrut(25));
        login.add(new JLabel("If you do not have an account, you can create one by clicking the \"New User? Create Account\" text on the login screen."));
        login.add(Box.createVerticalStrut(25));
        login.add(new JLabel("If you have an account, you can retrieve your password by clicking the \"Forgot Password\" button on the login screen."));
        login.add(new JLabel("Remember to enter your username before hitting the \"Forgot Password\" button."));
        login.add(Box.createVerticalStrut(25));
        login.add(new JLabel("Once you have your username and password, you can log in to the application."));
        login.add(new JLabel("To log in, enter your username and password into the text fields on the login screen."));
        login.add(new JLabel("Then, click the \"Login\" button."));
        login.add(Box.createVerticalStrut(25));
        login.add(new JLabel("If you have entered the correct username and password, you will be logged in to the application."));
        login.add(new JLabel("If you have entered the incorrect username and password, you will be told the login was invalid and will be prompted to try again."));

        /**
         * Setting layout of navigate tab and title
         */
        BoxLayout box3 = new BoxLayout(navigate, BoxLayout.Y_AXIS);
        navigate.setLayout(box3);
        JLabel navigateTitle = new JLabel("Navigating");
        navigateTitle.setFont(new Font("Georgia", Font.BOLD, 25));
        navigate.add(navigateTitle);

        /**
         * Simply adding content via JLabels to the general tab
         */
        navigate.add(new JLabel("Once you have logged in to the application, you will be able to navigate the campus using the map."));
        navigate.add(Box.createVerticalStrut(25));
        navigate.add(new JLabel("To navigate the campus map, you can click on buildings on the right side of the screen to go to the Floor Maps for that building."));
        navigate.add(new JLabel("You can also use the search bar to search for a POI within the campus map (find a building)"));
        navigate.add(new JLabel("Furthermore, you can scroll around the map with the scroll bars and simply click on the POI to navigate to a building."));
        navigate.add(Box.createVerticalStrut(25));
        navigate.add(new JLabel("To navigate a building, you can switch floors by pressing the \"Next Floor\" and \"Previous Floor\" buttons."));
        navigate.add(new JLabel("If there is no floor above or below the current floor, you will not be able to navigate that direction"));
        navigate.add(Box.createVerticalStrut(25));
        navigate.add(new JLabel("You can navigate the current floor you are on and find POIs by clicking on the POI on the right side of the screen"));
        navigate.add(new JLabel("You can also simplify the display of the floor map for navigation by toggling / untoggling different POI layers"));
        navigate.add(new JLabel("You can scroll around the floor map with the scroll bars and simply click on the POI to navigate to a POI."));

        /**
         * Setting layout of search tab and title
         */
        BoxLayout box4 = new BoxLayout(search, BoxLayout.Y_AXIS);
        search.setLayout(box4);
        JLabel searchTitle = new JLabel("Searching");
        searchTitle.setFont(new Font("Georgia", Font.BOLD, 25));
        search.add(searchTitle);

        /**
         * Simply adding content via JLabels to the general tab
         */
        search.add(new JLabel("You can search for POIs within the campus map and floor maps."));
        search.add(Box.createVerticalStrut(25));
        search.add(new JLabel("To search for a POI within the campus map, you can use the search bar on the top right of the screen."));
        search.add(new JLabel("Simply enter the name of the POI you are looking for"));
        search.add(new JLabel("This will take you to the building that you searhed for and click the \"Search\" button."));
        search.add(Box.createVerticalStrut(25));
        search.add(new JLabel("To search for a POI within a floor map, you can use the search bar on the top right of the screen."));
        search.add(new JLabel("Simply enter the name of the POI you are looking for and click the \"Search\" button."));
        search.add(Box.createVerticalStrut(25));
        search.add(new JLabel("If you are searching for a POI within a floor map, you can also search for a POI by clicking on the POI on the right side of the screen."));
        search.add(new JLabel("Simply click on the POI you are looking for and it will be highlighted on the map."));
        search.add(Box.createVerticalStrut(25));
        search.add(new JLabel("If you are searching for a POI within a floor map, you can also search for a POI by clicking on the POI on the map."));

        /**
         * Setting layout of favourite tab and title
         */
        BoxLayout box5 = new BoxLayout(favourite, BoxLayout.Y_AXIS);
        favourite.setLayout(box5);
        JLabel favouriteTitle = new JLabel("Favouriting POIs");
        favouriteTitle.setFont(new Font("Georgia", Font.BOLD, 25));
        favourite.add(favouriteTitle);

        /**
         * Simply adding content via JLabels to the general tab
         */
        favourite.add(new JLabel("You can favourite POIs within the campus map and floor maps."));
        favourite.add(Box.createVerticalStrut(25));
        favourite.add(new JLabel("To favourite a POI within the campus map, you can click on the POI on the right side of the screen."));
        favourite.add(new JLabel("You will then see a star icon that allows you to favourite the POI and store it in your favourites list."));
        favourite.add(Box.createVerticalStrut(25));
        favourite.add(new JLabel("To favourite a POI within a floor map, you can click on the POI on the right side of the screen, use the search feature, or navigate to the POI."));
        favourite.add(new JLabel("You will then see a star icon that allows you to favourite the POI and store it in your favourites list.."));

        /**
         * Setting layout of browse tab and title
         */
        BoxLayout box6 = new BoxLayout(browse, BoxLayout.Y_AXIS);
        browse.setLayout(box6);
        JLabel browseTitle = new JLabel("Browsing Maps");
        browseTitle.setFont(new Font("Georgia", Font.BOLD, 25));
        browse.add(browseTitle);

        /**
         * Simply adding content via JLabels to the general tab
         */
        browse.add(new JLabel("You can browse the campus map and floor maps."));
        browse.add(new JLabel("To browse the campus map, you can either look at it as you open the application and log in, or go back from a floor map to the campus map by clicking the \"Back\" button"));
        browse.add(new JLabel("The options available for maps you are free to browse are displayed on the right side of your screen on the Campus Map tab, showing the available floor plans."));



        

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

        /**
         * Set the JFrame to visible
         */
        frame.setVisible(true);

     }

    /**
     * Getter for the JFrame created
     */
    public JFrame getFrame() {
        return frame;
    }
}   
