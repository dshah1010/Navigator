package com.javan.dev;

// Import Swing Components
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.io.IOException;



/**
 * @author: Riley Emma Gavigan <rgavigan@uwo.ca>
 * @version: 1.0
 * @since: 1.0
 */
public class POICreationWindow extends JFrame implements ActionListener, MouseListener {
    /**
     * Private instance variables
     */
    private JFrame frame;
    private JPanel panel;
    private JLabel title;
    private JLabel description;
    private int x;
    private int y;
    private JButton create;
    private JButton cancel;
    private JPanel buttonPanel;

    /**
     * Private variables to hold the instance of the data processor and user
     */
    private DataProcessor processor = DataProcessor.getInstance();
    private User user = User.getInstance();
    private MapComponent mapComponent = MapComponent.getInstance();
    private POIComponent poiComponent = POIComponent.getInstance();
    private SidebarComponent sidebar = SidebarComponent.getInstance();


    /**
     * Constructor for POICreationWindow given x and y coordinates
     */
    public POICreationWindow(int x, int y) {
        /**
         * Get Coordinates from click
         */
        this.x = x;
        this.y = y;

        /**
         * Create Frame and Panel
         */
        frame = new JFrame("Create a new POI");
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.setSize(350, 350);
        frame.setMinimumSize(new Dimension(200, 200));
        /**
         * Add icon to the UI frame (Flag Icon)
         */
        frame.setIconImage(new ImageIcon("data/images/flag.png").getImage());

        /**
         * Create ArrayList of different metadata String titles
         */
        ArrayList<String> metadata = new ArrayList<String>();
        metadata.add("Name");
        metadata.add("Room Number");
        metadata.add("Description");
        metadata.add("X-Value");
        metadata.add("Y-value");
        if (user.getIsAdmin()) {
            metadata.add("Layer Type");
        }
        

        /**
         * Loop 10 times, creating a JPanel that holds a JLabel and a JTextField
         */
        for (int i = 0; i < metadata.size(); i++) {
            JPanel tempPanel = new JPanel();
            tempPanel.setBackground(Color.WHITE);
            tempPanel.setLayout(new GridLayout());
            JPanel labelHolder = new JPanel();
            labelHolder.setBackground(Color.WHITE);
            JLabel tempLabel = new JLabel(metadata.get(i));
            labelHolder.add(tempLabel);
            if (i == 3) {
                JTextField tempField = createTextField(Integer.toString(x));
                tempPanel.add(labelHolder);
                tempPanel.add(tempField);
                panel.add(tempPanel);
                continue;
            }
            if (i == 4) {
                JTextField tempField = createTextField(Integer.toString(y));
                tempPanel.add(labelHolder);
                tempPanel.add(tempField);
                panel.add(tempPanel);
                continue;
            }
            JTextField tempField = createTextField("");
            tempPanel.add(labelHolder);
            tempPanel.add(tempField);
            panel.add(tempPanel);
        }

        /**
         * Create buttons for submit and cancel and add to panel
         */
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        create = createMapButton("Create POI");
        cancel = createMapButton("Cancel");
        buttonPanel.add(cancel);
        buttonPanel.add(create);

        panel.add(buttonPanel);

        /**
         * Add panel to frame
         */
        frame.add(panel);

        /**
         * Set frame properties
         */
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    public void setVisibleFrame() {
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    /**
     * Method to create JTextField
     * @param String text
     * @return JTextField of the text field
     */
    public JTextField createTextField(String text) {
        /**
         * Create the new text field
         */
        JTextField newTextField = new JTextField(30);
        newTextField.setText(text);
        newTextField.setPreferredSize(new Dimension(150, 40));
        newTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        newTextField.setFont(new Font("Georgia", 1, 15));
        return newTextField;
    }

    /**
     * Function to create a new button and style it
     */
    public JButton createMapButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        button.setFont(new Font("Georgia", Font.PLAIN, 17));
        button.addActionListener(this);
        button.addMouseListener(this);

        return button;
    }



    public void actionPerformed(ActionEvent e) {
        /**
         * When the create button is clicked, create a new POI
         */
        if (e.getSource() == create) {
            Component[] children = panel.getComponents();
            // iterate over all subPanels...
            ArrayList<String> newPOIData = new ArrayList<String>();
            for (Component sp : children) {
                if (sp instanceof JPanel) {
                    Component[] spChildren = ((JPanel)sp).getComponents();
                    // now iterate over all JTextFields...
                    for (Component spChild : spChildren) {
                        if (spChild instanceof JTextField) {
                            String text = ((JTextField)spChild).getText();
                            newPOIData.add(text);
                        } 
                    }
                }
            }
            String layerType; 
            if (user.getIsAdmin()) {
                layerType = newPOIData.get(5);
            }
            else {
                layerType = "USER";
            }
            PointOfInterest poi = new PointOfInterest(
                newPOIData.get(0), user.getUserID(), 
                !user.getIsAdmin(), layerType, 
                Integer.parseInt(newPOIData.get(3)), 
                Integer.parseInt(newPOIData.get(4)), 
                mapComponent.getMapObject().getMapID(), 
                mapComponent.getFloorMapObject().getBuildingID(), 
                false, newPOIData.get(2), 
                Integer.parseInt(newPOIData.get(1)), true
                );
            try {
                boolean addedSuccessfully = processor.addPointOfInterestToJsonFile(poi);
                /*
                 * Gives an error message if the POI already exists for the user
                 */
                if (!addedSuccessfully) {
                    JPanel errorPanel = new JPanel();
                    JLabel errorMessage = new JLabel("Error: POI already exists");
                    errorPanel.add(errorMessage);
                
                    JOptionPane.showMessageDialog(null, errorPanel, "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException err) {
                err.printStackTrace();
            }
            mapComponent.displayPOIs();
            poiComponent.changeDisplayIfCampusMap(mapComponent.getMapObject().getMapID());
            /**
             * Update the sidebar component to display the new POI
             */
            poiComponent.updatePOIComponent();
            frame.dispose();
        }
        /**
         * When the cancel button is clicked, close the window
         */
        if (e.getSource() == cancel) {
            frame.dispose();
        }
    }

    public void mouseEntered(MouseEvent e) {
        /**
         * Button hover
         */
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }

    public void mouseExited(MouseEvent e) {
        /**
         * Button hover off
         */
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
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