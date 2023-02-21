package com.javan.dev;

public class MapFactory {
    public static Map createMap(String mapType, int mapID)
    {
        if (mapType == null || mapType.isEmpty())
            return null;        
        else if (mapType.equals("FLOOR")) {
            return new FloorMap(mapID);
        }
        else if (mapType.equals("BUILDING")) {
            return new BuildingMap(mapID);
        }
        else if (mapType.equals("CAMPUS")) {
            return new CampusMap(mapID);
        }
        else 
            throw new IllegalArgumentException("Unknown map type " + mapType);
        }
}
