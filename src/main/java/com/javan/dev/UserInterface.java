package com.javan.dev;

// Import Swing Components
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.MalformedURLException;

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
    private SidebarComponent sidebarComponent;
    
    /**
     * Constructor to create Main Frame of UI. This will be the main frame that will be used throughout the session.
     * @param None
     * @return None
     * @throws IOException
     * @throws MalformedURLException
     */
    UserInterface() throws MalformedURLException, IOException {
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
        openLoginComponent();

        /**
         * Set the frame as visible after adding everything to it
        */
        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
        frame.requestFocusInWindow();

        /**
         * Loop throughout program lifespan, keeping UI open with the MapComponent if its open, but if the user hits
         * logout then go back to loginComponent and loop around again
         */
        while(true) {

            /**
             * If LoginComponent has user log in, then open the MapComponent
             */
            while (!loginComponent.getLoginStatus()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            /**
             * Open MapComponent once user has logged in
             */
            frame.getContentPane().removeAll();
            openMapComponent();
            frame.revalidate();
            frame.repaint();

            /**
             * Loop until user logs out, then go back to LoginComponent
             */
            while (loginComponent.getLoginStatus()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
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
            openLoginComponent();
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
    public void openLoginComponent() {
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
    }

    /**
     * Method that opens a MapComponent to the UI, allowing the user to view maps
     * This method will open the map component inside the same window as the main UI
     * @throws IOException
     * @throws MalformedURLException
     */
    public void openMapComponent() throws MalformedURLException, IOException {
        /**
         * Remove the login component from the UI
         */
        frame.getContentPane().removeAll();
        System.out.println("Login Successful! Opening Map...");


        /**
         * Create a new JPanel to hold the map component, then add the MapComponent to it
         */
        JPanel mapPanel = new JPanel();
        mapPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        /**
         * Have map component take up the leftmost majority
         */
        mapComponent = new MapComponent();
        gridConstraints.fill = GridBagConstraints.BOTH;
        gridConstraints.weightx = 0.95;
        gridConstraints.weighty = 1.0;
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        mapPanel.add(mapComponent.getMapPanel(), gridConstraints);

        /**
         * Have sidebar component take up rightmost remainder
         */
        sidebarComponent = new SidebarComponent();
        gridConstraints.fill = GridBagConstraints.BOTH;
        gridConstraints.weightx = 0.05;
        gridConstraints.weighty = 1.0;
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 0;
        mapPanel.add(sidebarComponent.getSidebarPanel(), gridConstraints);

        /**
         * Add the mapPanel to the UI
         */
        frame.add(mapPanel);
    }
}
