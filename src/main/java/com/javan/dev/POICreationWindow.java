package com.javan.dev;

// Import Swing Components
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;


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
        frame.setIconImage(new ImageIcon("data\\images\\flag.png").getImage());

        /**
         * Create ArrayList of different metadata String titles
         */
        ArrayList<String> metadata = new ArrayList<String>();
        metadata.add("Name");
        metadata.add("Description");
        metadata.add("Layer Type");
        metadata.add("X-Value");
        metadata.add("Y-value");
        metadata.add("Other");
        metadata.add("Other");
        metadata.add("Other");
        metadata.add("Other");
        metadata.add("Other");

        /**
         * Loop 10 times, creating a JPanel that holds a JLabel and a JTextField
         */
        for (int i = 0; i < 10; i++) {
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
            PointOfInterest poi = new PointOfInterest(title.getText());
            // TODO: Create the POI with all the metadata filled in (add via methods below)
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