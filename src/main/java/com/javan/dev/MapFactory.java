package com.javan.dev;

public class MapFactory {
    public Map createMap(String mapType, String filePath, int mapID)
    {
        if (mapType == null || mapType.isEmpty())
            return null;        
        else if (mapType.equals("floor")) {
            return new FloorMap(filePath, mapID);
        }
        else if (mapType.equals("floor")) {
            return new BuildingMap(filePath, mapID);
        }
        else if (mapType.equals("floor")) {
            return new CampusMap(filePath, mapID);
        }
        else 
            throw new IllegalArgumentException("Unknown map type " + mapType);
        }
}