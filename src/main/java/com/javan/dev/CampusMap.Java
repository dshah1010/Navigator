package com.javan.dev;
import java.util.ArrayList;
import org.json.JSONObject;

/**
 * @author: Brad McGlynn <bmcglyn4@uwo.ca>
 * @version: 1.0
 * @since: 1.0
 */
public class CampusMap implements Map{
    /**
     * Declaring the file path, ID, type and BuildingArray of the Campus.
     */
    private String filePath;
    private int mapID;
    private String mapType;
    private ArrayList<BuildingMap> BuildingArray = new ArrayList<BuildingMap>();
    // TODO: add variable to contain map of campus

    /**
     * Constructor for the CampusMap class to initialize the ID, type and filePath
     * @param campusName
     */
    public CampusMap(int mapID) {
        this.filePath = "data/images/maps/campusMap.png";
        this.mapID = mapID;
        this.mapType = "CAMPUS";
    }

    /**
     * Getter for filePath
     * @param None
     * @return String
     */
    public String getFilePath() {
        return this.filePath;
    }

    /**
     * Getter for mapID
     * @param None
     * @return int
     */
    public int getMapID() {
        return this.mapID;
    }

    /**
     * Updates Map (WIP)
     * @param None
     * @return int
     */
    public void updateMap(int mapID) {
        this.mapID = mapID;
        // Perform update of Campus Map
    }

    /**
     * Getter for mapType
     * @param None
     * @return String
     */
    public String getMapType() {
        return this.mapType;
    }

    // TODO: either keep this as a required thing to bring over from Map.java
    // or implement it like in BuildingMap and FloorMap
    public JSONObject toJSON() {
        return null;
    }

    /**
     * Adds Bulding to BuildingArray
     * @param PointOfInterest
     * @return POI
     */
    public String addBuilding(BuildingMap newBuilding) {
        this.BuildingArray.add(newBuilding);
        return null;
    }

    /**
     * Getter for the BuildingMap
     * @param PointOfInterest
     * @return POI
     */
    public BuildingMap getBuilding(int buildingId) {
        for (int i = 0; i < this.BuildingArray.size(); i++) {
            if (this.BuildingArray.get(i).getMapID() == buildingId) {
                return this.BuildingArray.get(i);
            }
        }
        return null;
    }
}
