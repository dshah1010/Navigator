package com.javan.dev;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;

/**
 * @author: Brad McGlynn <bmcglyn4@uwo.ca>
 * @version: 1.0
 * @since: 1.0
 */

public class TestCampusMap {



    private CampusMap campusMap;

    @BeforeEach
    public void setUp() {
        campusMap = CampusMap.getInstance(1);
    }

    @Test
    public void testGetInstance() {
        assertNotNull(campusMap);
        assertEquals(CampusMap.getInstance(1), campusMap);
    }

    @Test
    public void testGetFilePath() {
        assertEquals("data/images/maps/campusMap.png", campusMap.getFilePath());
    }

    @Test
    public void testGetMapID() {
        assertEquals(1, campusMap.getMapID());
    }

    @Test
    public void testGetBuildingID() {
        assertEquals(-1, campusMap.getBuildingID());
    }

    @Test
    public void testGetMapType() {
        assertEquals("CAMPUS", campusMap.getMapType());
    }

    @Test
    public void testGetBuildingArray() {
        ArrayList<BuildingMap> buildingArray = campusMap.getBuildingArray();
        assertNotNull(buildingArray);
        assertFalse(buildingArray.isEmpty());
        assertTrue(buildingArray.get(0) instanceof BuildingMap);
    }
}

