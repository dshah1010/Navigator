package com.javan.dev;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestMapFactory {

    @Test
    public void testCreateFloorMap() {
        int buildingID = 1;
        int mapID = 1;
        Map floorMap = MapFactory.createMap("FLOOR", buildingID, mapID);
        assertTrue(floorMap instanceof FloorMap);
        assertEquals(buildingID, floorMap.getBuildingID());
        assertEquals(mapID, floorMap.getMapID());
        assertEquals("FLOOR", floorMap.getMapType());
    }
    
    @Test
    public void testCreateBuildingMap() {
        int mapID = 1;
        Map buildingMap = MapFactory.createMap("BUILDING", 0, mapID);
        assertTrue(buildingMap instanceof BuildingMap);
        assertEquals(mapID, buildingMap.getMapID());
        assertEquals("BUILDING", buildingMap.getMapType());
    }
    
    @Test
    public void testCreateCampusMap() {
        int mapID = 1;
        Map campusMap = MapFactory.createMap("CAMPUS", 0, mapID);
        assertTrue(campusMap instanceof CampusMap);
        assertEquals(mapID, campusMap.getMapID());
        assertEquals("CAMPUS", campusMap.getMapType());
    }
    
    @Test
    public void testCreateInvalidMap() {
        try {
            MapFactory.createMap("INVALID_MAP_TYPE", 0, 0);
            fail("Expected IllegalArgumentException was not thrown.");
        } catch (IllegalArgumentException e) {
            assertEquals("Unknown map type INVALID_MAP_TYPE", e.getMessage());
        }
}


}
