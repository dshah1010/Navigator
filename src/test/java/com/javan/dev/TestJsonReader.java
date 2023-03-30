package com.javan.dev; 

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.json.JSONArray;
/**
 * Include necessary libraries
 */
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;


public class TestJsonReader {
    private DataProcessor processor = DataProcessor.getInstance();
    private MapComponent mapComponent = MapComponent.getInstance();

    @Test
    public void testRead() throws IOException {
        String expected = "{\"filePath\":\"data/images/maps/floorPlans/3M, Thames, and Somerville\",\"floorMaps\":[{\"filePath\":\"data/images/maps/floorPlans/3M, Thames, and Somerville/3M, Thames and Somerville Floor Plans-1.png\",\"mapType\":\"FLOOR\",\"mapID\":1,\"mapName\":\"3M, Thames and Somerville Floor Plans-1.png\"},{\"filePath\":\"data/images/maps/floorPlans/3M, Thames, and Somerville/3M, Thames and Somerville Floor Plans-2.png\",\"mapType\":\"FLOOR\",\"mapID\":2,\"mapName\":\"3M, Thames and Somerville Floor Plans-2.png\"},{\"filePath\":\"data/images/maps/floorPlans/3M, Thames, and Somerville/3M, Thames and Somerville Floor Plans-3.png\",\"mapType\":\"FLOOR\",\"mapID\":3,\"mapName\":\"3M, Thames and Somerville Floor Plans-3.png\"},{\"filePath\":\"data/images/maps/floorPlans/3M, Thames, and Somerville/3M, Thames and Somerville Floor Plans-4.png\",\"mapType\":\"FLOOR\",\"mapID\":4,\"mapName\":\"3M, Thames and Somerville Floor Plans-4.png\"}],\"mapType\":\"BUILDING\",\"mapName\":\"3M, Thames, and Somerville\",\"buildingID\":1}";
        String actual = JsonReader.read("data/images/maps/metadata/mapMetadata.json");
        JSONArray jsonArray = new JSONArray(actual);
        assertEquals(expected, jsonArray.get(0).toString());
    }

    @Test
    public void testGetFloorMaps() throws IOException {
        String expected = "Arts and Humanities";
        ArrayList<FloorMap> floorMaps = JsonReader.getFloorMaps("data/images/maps/metadata/mapMetadata.json");
        BuildingPointOfInterest building = processor.getBuildingPOI(floorMaps.get(0).getBuildingID());
        assertEquals(expected, building.getName());
    }

    @Test
    public void testAddMapInfoToJSON() throws IOException {
        String directoryPath = "data/images/maps/floorPlans";
        String jsonFilePath = "data/images/maps/metadata/mapMetadata.json";
        JsonReader.addMapInfoToJSON(directoryPath, jsonFilePath);
        String expected = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
        assertNotNull(expected);
    }


    @Test
    public void testUserPOIList() {
        /*
         * tests the user POIs available
         */

        ArrayList<PointOfInterest> userPOIs = JsonReader.userPOIList(7);
        assertNotNull(userPOIs);

        /*
         * testing first user POI for demo user
         */
        assertEquals(6, userPOIs.size());
        assertEquals("Group Meeting Room", userPOIs.get(0).getName());
        assertEquals(7, userPOIs.get(0).getUserID());
        assertTrue(userPOIs.get(0).getIsUserMade());
        assertEquals("User POI", userPOIs.get(0).getPOItype());
        assertArrayEquals(new int[]{683,1429}, userPOIs.get(0).getCoordinates());
        assertEquals(3, userPOIs.get(0).getFloorID());
        assertEquals(21, userPOIs.get(0).getBuildingID());
        assertFalse(userPOIs.get(0).getIsFavourited(1));
        assertEquals("The room we book for our group meetings in CS 2212", userPOIs.get(0).getDescription());
        assertEquals("220", userPOIs.get(0).getRoomNumber());
        assertTrue(userPOIs.get(0).getIsVisible());

        /*
         * testing last user POI
         */
        assertEquals("UC Study Spot", userPOIs.get(userPOIs.size() - 1).getName());
        assertEquals(7, userPOIs.get(userPOIs.size() - 1).getUserID());
        assertTrue(userPOIs.get(userPOIs.size() - 1).getIsUserMade());
        assertEquals("User POI", userPOIs.get(userPOIs.size() - 1).getPOItype());
        assertArrayEquals(new int[]{1784,1726}, userPOIs.get(userPOIs.size() - 1).getCoordinates());
        assertEquals(2, userPOIs.get(userPOIs.size() - 1).getFloorID());
        assertEquals(38, userPOIs.get(userPOIs.size() - 1).getBuildingID());
        assertFalse(userPOIs.get(1).getIsFavourited(1));
        assertEquals("Riley's favourite study spot in UC - it has good couches", userPOIs.get(userPOIs.size() - 1).getDescription());
        assertEquals("2133", userPOIs.get(userPOIs.size() - 1).getRoomNumber());
        assertTrue(userPOIs.get(userPOIs.size() - 1).getIsVisible());
    }

    @Test
    public void testUniversalPOIs() {
        /*
         * tests the universal POIs available on the first floor of the University College building
         */
        FloorMap newMap = new FloorMap(38, 1);
        mapComponent.changeMap(newMap);
        ArrayList<PointOfInterest> userPOIs = JsonReader.universalPOIs(1);
        assertNotNull(userPOIs);

        /*
         * testing first admin POI
         */
        assertEquals(25, userPOIs.size());
        assertEquals("Accessibility Bathroom", userPOIs.get(0).getName());
        assertEquals(1, userPOIs.get(0).getUserID());
        assertFalse(userPOIs.get(0).getIsUserMade());
        assertEquals("Washrooms", userPOIs.get(0).getPOItype());
        assertArrayEquals(new int[]{1452,1794}, userPOIs.get(0).getCoordinates());
        assertEquals(1, userPOIs.get(0).getFloorID());
        assertEquals(38, userPOIs.get(0).getBuildingID());
        assertFalse(userPOIs.get(0).getIsFavourited(1));
        assertEquals("Accessible bathroom on the 1st floor of UC", userPOIs.get(0).getDescription());
        assertEquals("1131", userPOIs.get(0).getRoomNumber());
        assertTrue(userPOIs.get(0).getIsVisible());

        /*
         * testing last admin POI
         */
        assertEquals("Women's Bathroom", userPOIs.get(userPOIs.size() - 1).getName());
        assertEquals(1, userPOIs.get(userPOIs.size() - 1).getUserID());
        assertFalse(userPOIs.get(userPOIs.size() - 1).getIsUserMade());
        assertEquals("Washrooms", userPOIs.get(userPOIs.size() - 1).getPOItype());
        assertArrayEquals(new int[]{2718,1702}, userPOIs.get(userPOIs.size() - 1).getCoordinates());
        assertEquals(1, userPOIs.get(userPOIs.size() - 1).getFloorID());
        assertEquals(38, userPOIs.get(userPOIs.size() - 1).getBuildingID());
        assertFalse(userPOIs.get(1).getIsFavourited(1));
        assertEquals("Women's bathroom on the first floor of University College", userPOIs.get(userPOIs.size() - 1).getDescription());
        assertEquals("1201", userPOIs.get(userPOIs.size() - 1).getRoomNumber());
        assertTrue(userPOIs.get(userPOIs.size() - 1).getIsVisible());
    }

    @Test
    public void testFavouritePOIs() {
        /*
         * tests the favourited POIs 
         */
        ArrayList<PointOfInterest> userPOIs = JsonReader.favouritesList(7);
        assertNotNull(userPOIs);

        /*
         * testing first favourited POI by userID 7
         */
        assertEquals(4, userPOIs.size());
        assertEquals("Classroom 110", userPOIs.get(0).getName());
        assertEquals(7, userPOIs.get(0).getUserID());
        assertFalse(userPOIs.get(0).getIsUserMade());
        assertEquals("Classrooms", userPOIs.get(0).getPOItype());
        assertArrayEquals(new int[]{1814,798}, userPOIs.get(0).getCoordinates());
        assertEquals(2, userPOIs.get(0).getFloorID());
        assertEquals(21, userPOIs.get(0).getBuildingID());
        assertTrue(userPOIs.get(0).getIsFavourited(7));
        assertEquals("Large classroom on the 1st floor of Middlesex College", userPOIs.get(0).getDescription());
        assertEquals("110", userPOIs.get(0).getRoomNumber());
        assertTrue(userPOIs.get(0).getIsVisible());

        /*
         * testing last favourited POI by userID 7
         */
        assertEquals("Tims Express", userPOIs.get(userPOIs.size() - 1).getName());
        assertEquals(7, userPOIs.get(0).getUserID());
        assertFalse(userPOIs.get(0).getIsUserMade());
        assertEquals("User POI", userPOIs.get(userPOIs.size() - 1).getPOItype());
        assertArrayEquals(new int[]{1913,582}, userPOIs.get(userPOIs.size() - 1).getCoordinates());
        assertEquals(2, userPOIs.get(userPOIs.size() - 1).getFloorID());
        assertEquals(39, userPOIs.get(userPOIs.size() - 1).getBuildingID());
        assertTrue(userPOIs.get(userPOIs.size() - 1).getIsFavourited(7));
        assertEquals("because why wait in the 30 minute tim's line", userPOIs.get(userPOIs.size() - 1).getDescription());
        assertEquals("196", userPOIs.get(userPOIs.size() - 1).getRoomNumber());
        assertTrue(userPOIs.get(userPOIs.size() - 1).getIsVisible());

    }

}
