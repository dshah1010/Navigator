package com.javan.dev;

import javax.imageio.ImageIO;
// Import Swing Components
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
public class SidebarComponent extends JPanel implements ActionListener, MouseListener {
    /**
     * Initialize private variables for the UI
     */
    private JPanel sidebarPanel;
    private JToggleButton poiLayers;
    private JToggleButton poiList;
    private JToggleButton weatherInfo;
    private JPanel poiLayersPanel;
    private JPanel poiListPanel;
    private JPanel weatherInfoPanel;
    private JPanel poiLayersContentPanel;
    private JPanel poiListContentPanel;
    private JPanel weatherInfoContentPanel;

    /**
     * Constructor to initialize the sidebar component
     * @throws IOException
     * @throws MalformedURLException
     */
    public SidebarComponent() throws MalformedURLException, IOException {
        /**
         * Create a new JPanel for the map
         */
        sidebarPanel = new JPanel();
        sidebarPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        /**
         * Create 3 buttons that can toggle their state on and off for display
         * 1. POI Layers
         * 2. Points of Interest List
         * 3. Weather Information
         */
        poiLayers = createToggleButton("POI Layers");
        poiList = createToggleButton("Points of Interest");
        weatherInfo = createToggleButton("Weather Information");
        weatherInfo.setHorizontalAlignment(SwingConstants.LEFT);

        /**
         * Create a JPanel for the POI Layers
         */
        poiLayersContentPanel = new JPanel();
        poiLayersPanel = new JPanel();
        poiLayersPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        poiLayersContentPanel.add(poiLayers);
        poiLayersContentPanel.add(poiLayersPanel);
        poiLayersContentPanel.setLayout(new BoxLayout(poiLayersContentPanel, BoxLayout.Y_AXIS));

        /**
         * Create a JPanel for the Points of Interest List
         */
        poiListContentPanel = new JPanel();
        poiListPanel = new JPanel();
        poiListPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        poiListContentPanel.add(poiList);
        poiListContentPanel.add(poiListPanel);
        poiListContentPanel.setLayout(new BoxLayout(poiListContentPanel, BoxLayout.Y_AXIS));

        /**
         * Create a JPanel for the Weather Information
         */
        weatherInfoContentPanel = new JPanel();
        weatherInfoPanel = new JPanel();
        addWeatherInfo();
        weatherInfoPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        weatherInfoPanel.setLayout(new BoxLayout(weatherInfoPanel, BoxLayout.Y_AXIS));
        weatherInfoPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
        weatherInfoContentPanel.add(weatherInfo);
        weatherInfoContentPanel.add(weatherInfoPanel);
        weatherInfoContentPanel.setLayout(new BoxLayout(weatherInfoContentPanel, BoxLayout.Y_AXIS));

        /**
         * Add the different panels to the sidebar
         */
        sidebarPanel.add(poiLayersContentPanel);
        sidebarPanel.add(poiListContentPanel);
        sidebarPanel.add(weatherInfoContentPanel);
        /**
         * Display the three panels on top of one another with layout manager
         */
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        /**
         * Set visible
         */
        sidebarPanel.setVisible(true);
    }

    /**
     * Getter for sidebarPanel
     */
    public JPanel getSidebarPanel() {
        return sidebarPanel;
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

    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
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
                 * Set the JToggleButton background colour and text colour
                 */
                button.setBackground(Color.BLACK);
                button.setForeground(Color.WHITE);
                /**
                 * Get the name of the button and show the JFrame associated with it
                 */
                String buttonName = button.getText();
                if (buttonName.equals("POI Layers")) {
                    poiLayersPanel.setVisible(true);
                } else if (buttonName.equals("Points of Interest")) {
                    poiListPanel.setVisible(true);
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
                if (buttonName.equals("POI Layers")) {
                    poiLayersPanel.setVisible(false);
                } else if (buttonName.equals("Points of Interest")) {
                    poiListPanel.setVisible(false);
                } else if (buttonName.equals("Weather Information")) {
                    weatherInfoPanel.setVisible(false);
                }
            }
        }


        
        
    }

    /**
     * Method to create a JToggleButton and style it, as well as add event listeners
     * @param text
     * @return
     */
    public JToggleButton createToggleButton(String text) {
        JToggleButton button = new JToggleButton(text);
        button.addActionListener(this);
        button.addMouseListener(this);
        button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Georgia", Font.PLAIN, 12));
        button.setPreferredSize(new Dimension(200, 30));
        button.setMaximumSize(new Dimension(200, 30));
        button.setMinimumSize(new Dimension(200, 30));
        button.setSelected(true);
        return button;
    }

    /**
     * Method to add information to the Weather Information panel using the Weather class getTempC and getConditionIcon
     * Reads getConditionIcon URL and convert to BufferedImage for use in UI.
     * @throws IOException
     * @throws MalformedURLException
     */
    private void addWeatherInfo() throws MalformedURLException, IOException {
        Weather weather = new Weather();
        weather.parseWeather();
        /**
         * Get the temperature and icon link from the Weather class
         */
        String temp = weather.getTempC();
        String iconLink = weather.getConditionIcon();
        iconLink = "http:" + iconLink;

        /**
         * Read the URL and convert to BufferedImage
         */
        URL url = new URL(iconLink);
        BufferedImage image = ImageIO.read(url);
        Image newImage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);

        /**
         * Add the image and temperature to the panel
         */
        JPanel tempFrame = new JPanel();
        JLabel tempLabel = new JLabel(temp);
        tempLabel.setFont(new Font("Georgia", Font.PLAIN, 35));
        tempFrame.add(tempLabel);

        JPanel imageFrame = new JPanel();
        JLabel label = new JLabel(new ImageIcon(newImage));
        imageFrame.add(label);

        weatherInfoPanel.add(imageFrame);
        weatherInfoPanel.add(tempFrame);
        weatherInfoPanel.setVisible(true);
    }
    

}