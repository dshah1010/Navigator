package com.javan.dev; 

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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
        String expected = "Hello, World!";
        String actual = JsonReader.read("data/images/maps/metadata/mapMetadata.json");
        assertEquals(expected, actual);
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
        String actual = "[{\"mapName\":\"Building1\",\"mapType\":\"BUILDING\",\"buildingID\":1,\"floorMaps\":[{\"mapName\":\"Floor1.png\",\"mapType\":\"FLOOR\",\"mapID\":1,\"filePath\":\"src/test/resources/floorPlans/Building1/Floor1.png\"}],\"filePath\":\"src/test/resources/floorPlans/Building1\"}]";
        assertEquals(expected, actual);
    }


    @Test
    public void testUserPOIList() {
        // admin POIs
        ArrayList<PointOfInterest> userPOIs = JsonReader.userPOIList(1);
        assertNotNull(userPOIs);
        assertEquals(2, userPOIs.size());
        assertEquals("UserPOI1", userPOIs.get(0).getName());
        assertEquals(1, userPOIs.get(0).getUserID());
        assertTrue(userPOIs.get(0).getIsUserMade());
        assertEquals("Type1", userPOIs.get(0).getPOItype());
        assertArrayEquals(new int[]{0,0}, userPOIs.get(0).getCoordinates());
        assertEquals(1, userPOIs.get(0).getFloorID());
        assertEquals(1, userPOIs.get(0).getBuildingID());
        assertFalse(userPOIs.get(0).getIsFavourited(1));
        assertEquals("Description1", userPOIs.get(0).getDescription());
        assertEquals("Room1", userPOIs.get(0).getRoomNumber());
        assertTrue(userPOIs.get(0).getIsVisible());
        assertEquals("UserPOI2", userPOIs.get(1).getName());
        assertEquals(1, userPOIs.get(1).getUserID());
        assertTrue(userPOIs.get(1).getIsUserMade());
        assertEquals("Type2", userPOIs.get(1).getPOItype());
        assertArrayEquals(new int[]{1,1}, userPOIs.get(1).getCoordinates());
        assertEquals(2, userPOIs.get(1).getFloorID());
        assertEquals(2, userPOIs.get(1).getBuildingID());
        assertFalse(userPOIs.get(1).getIsFavourited(1));
        assertEquals("Description2", userPOIs.get(1).getDescription());
        assertEquals("Room2", userPOIs.get(1).getRoomNumber());
        assertFalse(userPOIs.get(1).getIsVisible());
    }

    @Test
    public void testUniversalPOIs() {
        
        FloorMap newMap = new FloorMap(4, 21);
        mapComponent.changeMap(newMap);
        ArrayList<PointOfInterest> userPOIs = JsonReader.universalPOIs(1);
        assertNotNull(userPOIs);
        System.out.println(null);

        /*
         * testing first admin POI
         */
        assertEquals(13, userPOIs.size());
        assertEquals("Men's Bathroom", userPOIs.get(0).getName());
        assertEquals(1, userPOIs.get(0).getUserID());
        assertTrue(userPOIs.get(0).getIsUserMade());
        assertEquals("Washrooms", userPOIs.get(0).getPOItype());
        assertArrayEquals(new int[]{1488,1053}, userPOIs.get(0).getCoordinates());
        assertEquals(4, userPOIs.get(0).getFloorID());
        assertEquals(21, userPOIs.get(0).getBuildingID());
        assertFalse(userPOIs.get(0).getIsFavourited(1));
        assertEquals("Men's Bathroom on the 3rd floor of Middlesex College", userPOIs.get(0).getDescription());
        assertEquals("311", userPOIs.get(0).getRoomNumber());
        assertTrue(userPOIs.get(0).getIsVisible());

        /*
         * testing last admin POI
         */
        assertEquals("UserPOI2", userPOIs.get(-1).getName());
        assertEquals(1, userPOIs.get(1).getUserID());
        assertTrue(userPOIs.get(1).getIsUserMade());
        assertEquals("Type2", userPOIs.get(1).getPOItype());
        assertArrayEquals(new int[]{1,1}, userPOIs.get(1).getCoordinates());
        assertEquals(2, userPOIs.get(1).getFloorID());
        assertEquals(2, userPOIs.get(1).getBuildingID());
        assertFalse(userPOIs.get(1).getIsFavourited(1));
        assertEquals("Description2", userPOIs.get(1).getDescription());
        assertEquals("Room2", userPOIs.get(1).getRoomNumber());
        assertFalse(userPOIs.get(1).getIsVisible());
    }

}
