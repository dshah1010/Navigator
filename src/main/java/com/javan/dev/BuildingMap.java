package com.javan.dev;
import java.util.ArrayList;
import org.json.JSONObject;
import org.json.JSONArray;

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
    private ArrayList<FloorMap> floorArray = new ArrayList<FloorMap>();
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
        this.mapName = null;
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


    /**
     * Adds Floor to FloorArray
     * @param FloorMap
     */
    public void addBuilding(FloorMap newFloor) {
        this.floorArray.add(newFloor);
    }

    /*
     * Setter for building name
     */
    public void setName(String buildingName) {
        this.mapName = buildingName;
    }
    
}