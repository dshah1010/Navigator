package com.javan.dev;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestMap {

    @Test
    public void testGetFilePath() {
        Map map = new FloorMap(1, 1);
        assertNotNull(map.getFilePath());
    }

    @Test
    public void testGetMapID() {
        Map map = new FloorMap(1, 1);
        assertEquals(1, map.getMapID());
    }

    @Test
    public void testGetMapType() {
        Map map = new FloorMap(1, 1);
        assertNotNull(map.getMapType());
    }

    @Test
    public void testGetBuildingID() {
        Map map = new FloorMap(1, 1);
        assertEquals(1, map.getBuildingID());
    }
}
