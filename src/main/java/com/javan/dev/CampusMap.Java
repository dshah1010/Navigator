package com.javan.dev;
import java.util.ArrayList;

/**
 * @author: Brad McGlynn <bmcglyn4@uwo.ca>
 * @version: 1.0
 * @since: 1.0
 */
public class CampusMap implements Map{
    /**
     * Declaring the name and BuildingArray of the Campus.
     */
    private String filePath;
    private int mapID;
    private ArrayList<BuildingMap> BuildingArray = new ArrayList<>;
    // TODO: add variable to contain map of campus

    /**
     * Constructor for the CampusMap class to initialize the name/ ID
     * @param campusName
     */
    public CampusMap(String filePath, int mapID) {
        this.mapID = campusName;
        this.mapID = mapID;
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
    public String getBuilding(int BuildingId) {
        for (int i = 0; i < this.BuildingArray.size(); i++) {
            if (this.BuildingArray[i].buildingId == BuildingId) {
                return this.BuildingArray[i];
            }
        }
        return null;
    }
}