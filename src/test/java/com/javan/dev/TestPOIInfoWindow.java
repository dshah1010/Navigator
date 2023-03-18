package com.javan.dev;

/**
 * Import necessary libraries
 */
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;


/**
 * @author: Riley Emma Gavigan <rgavigan@uwo.ca>
 * @version: 1.0
 * @since: 1.0
 */
@DisplayName("POIInfoWindow Tests")
class TestPOIInfoWindow {
    /**
     * Private variables for POIInfoWindow tests
     */
    private PointOfInterest poi;
    private POIInfoWindow poiInfoWindow;

    /**
     * Create new POIInfoWindow object before each test with POI name "Test"
     */
    @BeforeEach
    void setUp() {
        poi = new PointOfInterest(null, 0, false, null, 0, 0, 0, 0, null, null, null, false);
        poiInfoWindow = new POIInfoWindow(poi.getID());
    }

    /**
     * This method tests that the POIInfoWindow frame title is correct
     */
    @Test
    @DisplayName("Test POIInfoWindow Frame Title")
    void testFrameTitle() {
        assertEquals("Test", poiInfoWindow.getFrame().getTitle(), "Frame title is incorrect");
    }

    /**
     * This method tests that the POIInfoWindow labels are correct
     */
    @Test
    @DisplayName("Test POIInfoWindow Labels")
    void testLabels() {
        ArrayList<JLabel> labels = poiInfoWindow.getLabels();

        assertEquals("Test", labels.get(0).getText(), "POI name label is incorrect");
        assertEquals("ID: 12", labels.get(1).getText(), "POI ID label is incorrect");
        assertEquals("Description: This is a test description", labels.get(2).getText(), "POI description label is incorrect");
    }

    /**
     * This method tests that the POIInfoWindow frame is visible
     */
    @Test
    @DisplayName("Test POIInfoWindow Frame Visibility")
    void testFrameVisibility() {
        poiInfoWindow.setVisibleFrame();
        assertTrue(poiInfoWindow.getFrame().isVisible(), "Frame visibility is incorrect");
    }

}
