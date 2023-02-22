package com.javan.dev;

/**
 * Include necessary libraries
 */
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import javax.swing.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author: Riley Emma Gavigan <rgavigan@uwo.ca>
 * @version: 1.0
 * @since: 1.0
 */
public class TestMapComponent {

    private MapComponent mapComponent;

    /**
     * Before each test method, get the Map instance
     */
    @BeforeEach
    void setUp() {
        mapComponent = MapComponent.getInstance();
    }

    /**
     * This method is called to check that the MapComponent singleton instance is not null and that it is the same instance
     */
    @Test
    void testGetInstanceReturnsSameInstance() {
        MapComponent instance1 = MapComponent.getInstance();
        MapComponent instance2 = MapComponent.getInstance();
        assertEquals(instance1, instance2);
    }

    /**
     * This method is called to check that the map panel is not null
     */
    @Test
    void testGetMapPanelReturnsNonNullJPanel() {
        JPanel mapPanel = mapComponent.getMapPanel();
        assertNotNull(mapPanel);
    }

    /**
     * This method is called to check that the current map ID is 1, meaning the campusMap is open on default
     */
    @Test
    void testGetCurrentMapIDReturns1() {
        int currentMapID = mapComponent.getCurrentMapID();
        assertEquals(1, currentMapID);
    }

    /**
     * This method is called to check that the current map is the campus map
     */
    @Test
    void testGetIsCampusMapReturnsTrue() {
        boolean isCampusMap = mapComponent.getIsCampusMap();
        assertTrue(isCampusMap);
    }
}