package com.javan.dev;

public class MapFactory {
    public Map createMap(String mapType)
    {
        if (mapType == null || mapType.isEmpty())
            return null;
        
        if (mapType.equals("floor")) {
            return new FloorMap();
        }
        else if (mapType.equals("floor")) {
            return new BuildingMap();
        }
        else if (mapType.equals("floor")) {
            return new CampusMap();
        }
        else 
            throw new IllegalArgumentException("Unknown map type " + mapType);
        }
}
