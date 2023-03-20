package com.javan.dev;

import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author: Brad McGlynn <bmcglyn4@uwo.ca>
 * @version: 1.0
 * @since: 1.0
 */

class TestFloorMap {
    @Test
    void testConstructor() {
        FloorMap floorMap = new FloorMap(1, 1);
        assertNotNull(floorMap);
        assertEquals("FLOOR", floorMap.getMapType());
        assertEquals(1, floorMap.getBuildingID());
        assertEquals(1, floorMap.getMapID());
    }

    @Test
    void testGetFilePath() {
        FloorMap floorMap = new FloorMap(1, 1);
        assertNotNull(floorMap.getFilePath());
    }

    @Test
    void testGetFloorAbove() throws IOException {
        FloorMap floorMap = new FloorMap(1, 1);
        assertNotNull(floorMap.getFloorAbove());
    }

    @Test
    void testGetFloorBelow() throws IOException {
        FloorMap floorMap = new FloorMap(1, 1);
        assertNull(floorMap.getFloorBelow());
    }

    @Test
    void testCheckFloorAbove() {
        FloorMap floorMap = new FloorMap(1, 1);
        assertTrue(floorMap.checkfloorAbove());
    }

    @Test
    void testCheckFloorBelow() {
        FloorMap floorMap = new FloorMap(1, 1);
        assertFalse(floorMap.checkFloorBelow());
    }
}
