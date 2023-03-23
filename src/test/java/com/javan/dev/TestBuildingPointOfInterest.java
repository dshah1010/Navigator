package com.javan.dev;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;

/**
 * @author: Brad McGlynn <bmcglyn4@uwo.ca>
 * @version: 1.0
 * @since: 1.0
 */




public class TestBuildingPointOfInterest {
    DataProcessor processor = DataProcessor.getInstance();
    private BuildingPointOfInterest poi;
    @BeforeEach
    public void setup() {
        poi = new BuildingPointOfInterest("POI 1", 1, true, "type", 10, 20, 1, new ArrayList<Integer>(), "description", true);
    }
    
    /**
     * Method to test that the POI was constructed properly
     */
    @Test
    @DisplayName("Should confirm that all poi variables were constructed properly, and return the appropriate values for all getter methods")
    public void testGetters() {
        assertEquals("POI 1", poi.getName());
        assertEquals(1, poi.getUserID());
        assertEquals(true, poi.getIsUserMade());
        assertEquals("type", poi.getPOItype());
        assertEquals(10, poi.getCoordinates()[0]);
        assertEquals(20, poi.getCoordinates()[1]);
        assertEquals(1, poi.getBuildingID());
        assertEquals(1, poi.getUserID());
        assertEquals("description", poi.getDescription());
        assertFalse(poi.getIsFavourited(poi.getUserID()));
        assertEquals(true, poi.getisVisible());
        assertEquals(processor.makeNewBuildingPOIID(), poi.getID());
        

    }
    
    /**
     * Method to test that setter methods work properly
     */
    @Test
    @DisplayName("Should confirm that setter functions work as intended")
    public void testSetters() {
        poi.setName("POI 2");
        poi.setPOItype("new type");
        poi.setCoordinates(15, 25);
        poi.setDescription("new description");
        assertEquals("POI 2", poi.getName());
        assertEquals("new type", poi.getPOItype());
        assertArrayEquals(new int[]{15, 25}, poi.getCoordinates());
        assertEquals("new description", poi.getDescription());
    }
    
    
    /**
     * Method to test that getIsFavourited returns the appropriate values based on the provided userIDs
     */
    @Test
    @DisplayName("Should confirm that user")
    public void testGetIsFavourited() {
        ArrayList<Integer> userFavouritesList = new ArrayList<Integer>();
        userFavouritesList.add(1);
        userFavouritesList.add(2);
        for (Integer i : userFavouritesList)
            {poi.setIsFavourited(i);
            assertTrue(poi.getIsFavourited(i));
        }
        assertFalse(poi.getIsFavourited(3));
    }

    
}
