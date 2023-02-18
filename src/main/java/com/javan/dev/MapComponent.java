package com.javan.dev;

// Import Swing Components
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 * @author: Riley Emma Gavigan <rgavigan@uwo.ca>
 * @version: 1.0
 * @since: 1.0
 */
public class MapComponent extends JPanel implements ActionListener, MouseListener {
    /**
     * Initialize private variables for the UI
     */
    private JPanel mapPanel;

    /**
     * Constructor to initialize the map component
     */
    public MapComponent() {
        /**
         * Create a new JPanel for the map
         */
        mapPanel = new JPanel();
        mapPanel.addMouseListener(this);
        mapPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        /**
         * Fill with test content
         */
        JLabel testLabel = new JLabel("Map Component"); // TEMPORARY
        testLabel.setHorizontalAlignment(JLabel.CENTER); // TEMPORARY
        mapPanel.add(testLabel); // TEMPORARY
 
        /**
         * Set visible
         */
        mapPanel.setVisible(true);
    }

    /**
     * Getter for map panel
     * @return mapPanel
     */
    public JPanel getMapPanel() {
        return mapPanel;
    }

     /**
      * Constructor to create the MapComponent within the UI
      */

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

    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }
    
}
