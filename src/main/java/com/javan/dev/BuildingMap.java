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

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("filePath", this.filePath);
        json.put("mapID", this.mapID);
        json.put("mapType", this.mapType);
        JSONArray floorArrayJson = new JSONArray();
        for (FloorMap floorMap : this.floorArray) {
            floorArrayJson.put(floorMap.toJSON());
        }
        json.put("floorArray", floorArrayJson);
        return json;
    }

    /**
     * Adds Floor to FloorArray
     * @param FloorMap
     */
    public void addBuilding(FloorMap newFloor) {
        this.floorArray.add(newFloor);
    }

    /**
     * Getter for the Floor
     * @param FloorId
     * @return FloorMap
     */
    public FloorMap getFloor(int floorID) {
        for (int i = 0; i < this.floorArray.size(); i++) {
            if (this.floorArray.get(i).getMapID() == floorID) {
                return this.floorArray.get(i);
            }
        }
        return null;
    }
    
}