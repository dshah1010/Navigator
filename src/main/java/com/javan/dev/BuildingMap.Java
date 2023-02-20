package com.javan.dev;
import java.util.ArrayList;

/**
 * @author: Brad McGlynn <bmcglyn4@uwo.ca>
 * @version: 1.0
 * @since: 1.0
 */
public class BuildingMap implements Map {
    /**
     * Declaring the name, Id and FloorArray of the Building.
     */
    private String filePath;
    private int buildingId;
    private ArrayList<FloorMap> FloorArray = new ArrayList<>;
    // TODO: add variable to contain map of building

    /**
     * Constructor for the BuildingMap class to initialize the name/ ID
     * @param buildingName
     * @param buildingId
     */
    public BuildingMap(String filePath, int buildingId) {
        this.filePath = buildingName;
        this.buildingId = buildingId;
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
     * Adds Floor to FloorArray
     * @param PointOfInterest
     */
    public String addBuilding(FloorMap newFloor) {
        this.FloorArray.add(newFloor);
    }

    /**
     * Getter for the Floor
     * @param FloorId
     * @return FloorMap
     */
    public String getFloor(int floorId) {
        for (int i = 0; i < this.FloorArray.size(); i++) {
            if (this.FloorArray[i].floorId == floorId) {
                return this.FloorArray[i];
            }
        }
        return null;
    }
    
}