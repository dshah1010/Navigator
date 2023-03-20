package com.javan.dev; 

/**
 * Include necessary libraries
 */
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 * @author: Brad McGlynn <bmcglyn4@uwo.ca>
 * @version: 1.0
 * @since: 1.0
 */


public class TestBuildingMap {
  
    
    BuildingMap buildingMap;

    @BeforeEach
    public void setUp() throws Exception {
        buildingMap = new BuildingMap(1);
    }

    @Test
    public void testGetFilePath() {
        String filePath = buildingMap.getFilePath();
        assertNotNull(filePath);
    }

    @Test
    public void testGetMapID() {
        int mapID = buildingMap.getMapID();
        assertEquals(1, mapID);
    }

    @Test
    public void testGetBuildingID() {
        int buildingID = buildingMap.getBuildingID();
        assertEquals(1, buildingID);
    }

    @Test
    public void testGetMapType() {
        String mapType = buildingMap.getMapType();
        assertEquals("BUILDING", mapType);
    }

    @Test
    public void testSetName() {
        buildingMap.setName("Test Building");
        assertEquals("Test Building", buildingMap.getMapName());
    }

    @Test
    public void testAddBuilding() {
        FloorMap floorMap = new FloorMap(buildingMap.getBuildingID(), 1);
        buildingMap.addBuilding(floorMap);
        assertEquals(1, buildingMap.getFloors().size());
    }

}

