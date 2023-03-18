package com.javan.dev;

/**
 * @author: Brad McGlynn <bmcglyn4@uwo.ca>
 * @version: 1.0
 * @since: 1.0
 */
public class MapFactory {
    /**
     * Function to create map metadata based on images in directory
     * @param String mapyType, type of map to be created
     * @param int buildingID, building ID of relevant floor or building map
     * @param int mapID, id of the map
     * @return Map object
     */
    public static Map createMap(String mapType, int buildingID, int mapID)
    {
        if (mapType == null || mapType.isEmpty())
            return null;        
        else if (mapType.equals("FLOOR")) {
            return new FloorMap(buildingID, mapID);
        }
        else if (mapType.equals("BUILDING")) {
            return new BuildingMap(mapID);
        }
        else if (mapType.equals("CAMPUS")) {
            return CampusMap.getInstance(mapID);
        }
        else {
            throw new IllegalArgumentException("Unknown map type " + mapType);
        }
    }
}
