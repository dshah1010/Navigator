package com.javan.dev;

/**
 * Include necessary libraries
 */
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;

import java.awt.Point;
import java.util.ArrayList;

/**
 * @author: Jake Choi <jchoi492@uwo.ca>
 * @version: 1.0
 * @since: 1.0
 */

public class TestDataProcessor {
    
    DataProcessor dataProcessor;

    @BeforeEach
    public void setUp(){
        dataProcessor = DataProcessor.getInstance();
    }

    @Test
    @DisplayName("Should confirm that the correct map file path returns based on the input.")
    public void testLoadMapFilePath() {
        /**
         * Test for floor map.
         */
        assertEquals("data/images/maps/floorPlans/3M, Thames, and Somerville/3M, Thames and Somerville Floor Plans-1.png", dataProcessor.loadMapFilePath(1, 1, "floor"));

        /**
         * Test for building map.
         */
        assertEquals("data/images/maps/floorPlans/3M, Thames, and Somerville", dataProcessor.loadMapFilePath(1, 1, "bUiLdiNG"));

        /**
         * Test for null.
         */
        assertNull(dataProcessor.loadMapFilePath(1, 1, ""));
    }

    @Test
    @DisplayName("Should confirm that the correct ArrayList is returned based on the input to the method.")
    public void testParseWeather() {
        /**
         * Test if the method catches a null StringBuffer correctly.
         */
        ArrayList<String> parsedData = dataProcessor.parseWeather(null);
        assertNull(parsedData);

        /**
         * Test if the method is correctly parsing.
         * NOTE: I don't even know if this actually fully tests, since parseWeather method in Weather uses the parseWeather in DataProcessor...
         */
        Weather weather = Weather.getInstance();
        weather.parseWeather();
        parsedData = dataProcessor.parseWeather(weather.getJSON());
        assertNotNull(parsedData);
        assertFalse(parsedData.isEmpty());
        assertTrue(parsedData.get(0) instanceof String);
    }

    /**
     * Tested in TestJsonReader.java
     * This method in DataProcessor returns the value returned by a method in the JsonReader class.
     */
    public void testGetFavouritePOIs() {
    }

    /**
     * Tested in TestJsonReader.java
     * This method in DataProcessor returns the value returned by a method in the JsonReader class.
     */    
    public void testGetBuildingFavouritePOIs() {
    }

    /**
     * Tested in TestJsonReader.java
     * This method in DataProcessor returns the value returned by a method in the JsonReader class.
     */
    public void testGetUserPOIs() {
    }

    /**
     * Tested in TestJsonReader.java
     * This method in DataProcessor returns the value returned by a method in the JsonReader class.
     */
    public void testGetBuildingUserPOIs() {
    }

    /**
     * Floor map test (when isCampusMap is passed as false) for getUniversalPOIs will be done in TestJsonReader.java, as this method returns the value returned by a method in the JsonReader class.
     */
    @Test
    @DisplayName("Should confirm if the correct list of POIs is returned for when it is the campus map.")
    public void testGetUniversalPOIs() {
        ArrayList<PointOfInterest> universalPOIS;
        /**
         * Test for for campus map.
         */
        universalPOIS = dataProcessor.getUniversalPOIs(true, 1);
        assertNotNull(universalPOIS);
        assertFalse(universalPOIS.isEmpty());
        assertTrue(universalPOIS.get(0) instanceof PointOfInterest);
        assertEquals("3M, Thames, and Somerville", universalPOIS.get(0).getName());
    }

    /**
     * Tested in TestJsonReader.java
     * This method in DataProcessor returns the value returned by a method in the JsonReader class.
     */
    public void testGetBuildingUniversalPOIs() {
    }

    @Test
    @DisplayName("Should confirm if the correct POI coordinates are returned based on a POI ID.")
    public void testGetPOIPosition() {
        /**
         * Check actual existing POI.
         */
        int[] expectedPOIPos = {1452, 1794};
        assertEquals(expectedPOIPos[0], dataProcessor.getPOIPosition(0)[0]);
        assertEquals(expectedPOIPos[1], dataProcessor.getPOIPosition(0)[1]);
        
        /**
         * Check if a non-existent POI ID input returns null.
         */
        assertNull(dataProcessor.getPOIPosition(1000));
    }

    @Test
    @DisplayName("Should confirm that either a POI was added successfully or unsuccessfully to the Json File.")
    public void testAddPointOfInterestToJsonFile() {
        
    }

    @Test
    public void testAddBuildingPointOfInterestToJsonFile() {

    }

    @Test
    public void testEditPointOfInterestInJsonFile() {

    }

    @Test
    public void testEditBuildingPointOfInterestInJsonFile() {

    }

    @Test
    public void testDeletePointOfInterestFromJsonFile() {

    }

    @Test
    public void testDeleteBuildingPointOfInterestFromJsonFile() {

    }

    @Test
    public void testCheckFloorAbove() {

    }

    @Test
    public void testCheckFloorBelow() {

    }

    @Test
    public void testGetFloorAbove() {

    }

    @Test
    public void testGetFloorBelow() {

    }

    @Test
    public void testMakeNewPOIID() {

    }

    @Test
    public void testMakeNewBuildingPOIID() {

    }

    @Test
    public void testGetPOI() {

    }

    @Test
    public void testGetBuildingPOI() {

    }

    @Test
    public void testEncrypt() {

    }

    @Test
    public void testDecrypt() {

    }

    @Test
    public void testAuthenticateLogin() {

    }

    @Test
    public void testGetPasswordFromUsername() {

    }

    @Test
    public void testCreateAccount() {

    }

    @Test
    public void testGetFloorMapFromMapID() {

    }

}
