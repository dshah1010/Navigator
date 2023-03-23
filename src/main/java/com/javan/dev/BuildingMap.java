package com.javan.dev;
import java.util.ArrayList;

/**
 * @author: Brad McGlynn <bmcglyn4@uwo.ca>
 * @version: 1.0
 * @since: 1.0
 */
public class BuildingMap implements Map {
    /**
     * Declaring the name, ID, filePath and FloorArray of the Building.
     */
    private String filePath;
    private int mapID;
    private String mapType;
    private String mapName;

    /**
     * Private variable to hold the instance of the data processor
     */
    private DataProcessor processor = DataProcessor.getInstance();

    /**
     * Constructor for the BuildingMap class to initialize the ID, type and filePath
     * @param mapID
     */
    public BuildingMap(int mapID) {
        this.filePath = processor.loadMapFilePath(mapID, 0, "BUILDING");
        this.mapID = mapID;
        this.mapType = "BUILDING";
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
     * Getter for buildingID id
     * @return map id int
     */
    public int getBuildingID() {
        return this.mapID;
    }


    /**
     * Getter for mapType
     * @param None
     * @return String
     */
    public String getMapType() {
        return this.mapType;
    }

    /**
     * Getter for map name
     */
    public String getMapName() {
        return this.mapName;
    }


    /*
     * Setter for building name
     */
    public void setName(String buildingName) {
        this.mapName = buildingName;
    }
    
}