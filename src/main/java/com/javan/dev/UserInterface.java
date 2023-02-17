package com.javan.dev;

// Import Swing Components
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author: Riley Emma Gavigan <rgavigan@uwo.ca>
 * @version: 1.0
 * @since: 1.0
 */
public class UserInterface extends JFrame implements ActionListener {
    /**
     * Initialize private variables for the UI
     */
    private JFrame frame = new JFrame("Enhanced Campus Navigation");
    private JMenuBar menuBar;
    private JMenu helpMenu;
    private JMenuItem helpItem;
    private JMenuItem logout;
    private JMenuItem minimize;
    private JMenuItem exit;
    private JPanel loginPanel;
    private LoginComponent loginComponent;
    private MapComponent mapComponent;
    
    /**
     * Constructor to create Main Frame of UI. This will be the main frame that will be used throughout the session.
     * @param None
     * @return None
     */
    UserInterface() {
        /**
         * Create JFrame Window
         */
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 675);
        frame.setMinimumSize(new Dimension(800, 450));

        /**
         * Add icon to the UI frame (UWO Logo)
         */
        frame.setIconImage(new ImageIcon("data\\images\\icon.png").getImage());

        /**
         * Create a menu bar with a help menu contained in it
         */
        menuBar = new JMenuBar();
        helpMenu = new JMenu("Help");
        /**
         * Create the items for the help menu (helpItem, logout, minimize, exit)
         * Add action listeners to each item, as well as mnemonics for accessibility / shortcuts for faster use
         */
        helpItem = new JMenuItem("Help Menu");
        helpItem.setMnemonic(KeyEvent.VK_H);
        helpItem.addActionListener(this);

        logout = new JMenuItem("Logout");
        logout.setMnemonic(KeyEvent.VK_L);
        logout.addActionListener(this);

        minimize = new JMenuItem("Minimize");
        minimize.setMnemonic(KeyEvent.VK_M);
        minimize.addActionListener(this);

        exit = new JMenuItem("Exit");
        exit.setMnemonic(KeyEvent.VK_E);
        exit.addActionListener(this);

        /**
         * Add the help menu and its items to the menu bar
         */
        menuBar.add(helpMenu);
        helpMenu.add(helpItem);
        helpMenu.add(logout);
        helpMenu.add(minimize);
        helpMenu.add(exit);

        /**
         * Application initally opens with LoginComponent panel visible
         */
        loginComponent = openLoginComponent();

        /**
         * Set the frame as visible after adding everything to it
         */
        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
        frame.requestFocusInWindow();
    }

    /**
     * Method to handle the actions of the menu items and other components
     * @param e, the event that is triggered when a menu item is clicked
     * @return None
     */
    public void actionPerformed(ActionEvent e) {
        /**
         * Go to UserHelp page when help menu is clicked
         */
        if (e.getSource() == helpItem) {
            UserHelp helpMenu = new UserHelp();
            helpMenu.getFrame().setLocationRelativeTo(frame);
        }
        /**
         * Go to LoginComponent page when logout is clicked
         */
        else if (e.getSource() == logout) {
            /**
             * Remove any other frames in the UI, then add login component to UI
             */
            frame.getContentPane().removeAll();
            loginComponent = openLoginComponent();
            frame.revalidate();
            frame.repaint();
        }
        /**
         * Minimize the UI when minimize is clicked
         */
        else if (e.getSource() == minimize) {
            frame.setState(JFrame.ICONIFIED);
        }
        /**
         * Exit the UI when exit is clicked
         */
        else if (e.getSource() == exit) {
            System.exit(0);
        }
    }

    /**
     * Method that opens a LoginComponent to the UI, allowing the user to login
     * This method will open the login component inside the same window as the main UI
     */
    public LoginComponent openLoginComponent() {
        /**
         * Create a new JPanel to hold the login component, then add the LoginComponent to it
         */
        loginPanel = new JPanel();
        loginComponent = new LoginComponent();
        loginPanel.add(loginComponent);

        /**
         * Add the loginPanel to the UI
         */
        frame.add(loginPanel);
        return loginComponent;
    }

    /**
     * Method that opens a MapComponent to the UI, allowing the user to view maps
     * This method will open the map component inside the same window as the main UI
     */
    public MapComponent openMapComponent() {
        /**
         * Remove the login component from the UI
         */
        frame.getContentPane().removeAll();

        /**
         * Create a new JPanel to hold the map component, then add the MapComponent to it
         */
        JPanel mapPanel = new JPanel();
        mapComponent = new MapComponent();
        mapPanel.add(mapComponent);

        /**
         * Add the mapPanel to the UI
         */
        frame.add(mapPanel);
        frame.revalidate();
        frame.repaint();
        return mapComponent;
}
