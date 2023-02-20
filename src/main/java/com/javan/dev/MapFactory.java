package com.javan.dev;

public class MapFactory {
    public Map createMap(String mapType)
    {
        if (mapType == null || mapType.isEmpty())
            return null;
        
        if (mapType.equals("floor")) {
            return new FloorMap(null);
        }
        else if (mapType.equals("floor")) {
            return new BuildingMap(mapType, 0);
        }
        else if (mapType.equals("floor")) {
            return new CampusMap(mapType);
        }
        else 
            throw new IllegalArgumentException("Unknown map type " + mapType);
        }
}
