package com.javan.dev;
import java.util.ArrayList;
import org.json.JSONObject;
import org.json.JSONArray;
/**
 * @author: Brad McGlynn <bmcglyn4@uwo.ca>
 * @version: 1.0
 * @since: 1.0
 */
public class FloorMap implements Map{
    /**
     * Declaring the mapID, buildindID, filePath, map type, Point of Interest array and layers array of the Floor.
     */
    private String filePath;
    private int mapID;
    private int buildingID;
    private String mapType;
    private ArrayList<PointOfInterest> POIs = new ArrayList<PointOfInterest>();
    private ArrayList<Integer> layers = new ArrayList<Integer>();
    
    /**
     * Private variable to hold the instance of the data processor
     */
    private DataProcessor processor = DataProcessor.getInstance();

    /**
     * Constructor for the FloorMap class to initialize the ID, type and filePath
     * @param floorId
     */
    public FloorMap(int buildingID, int mapID) {
        this.filePath = processor.loadMapFilePath(buildingID, mapID, "FLOOR");
        this.mapID = mapID;
        this.buildingID = buildingID;
        this.mapType = "FLOOR";
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
     * Getter for the mapID
     * @param None
     * @return mapID
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
     * Getter for the buildingID
     * @param None
     * @return mapID
     */
    public int getBuildingID() {
        return this.buildingID;
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
        json.put("buildingID", this.buildingID);
        JSONArray POIsJson = new JSONArray();
        for (PointOfInterest POI : this.POIs) {
            POIsJson.put(POI.toJSON());
        }
        json.put("POIs", POIsJson);
        return json;
    }

    /**
     * Adds PointOfInterest to POIs
     * @param PointOfInterest
     * @return POI
     */
    public void addPOI(PointOfInterest newPointOfInterest) {
        this.POIs.add(newPointOfInterest);
    }

    /**
     * Searches for the PointOfInterest that matches search string
     * @param PointOfInterest
     * @return POI
     */
    public PointOfInterest searchPOI(String PointOfInterest) {
        for (int i = 0; i < this.POIs.size(); i++) {
            if (this.POIs.get(i).getName().equalsIgnoreCase(PointOfInterest)) {
                return this.POIs.get(i);
            }
        }
        return null;
    }


    public boolean checkfloorAbove() {
        return this.processor.checkFloorAbove(this.mapID, this.buildingID);
    }

    public boolean checkFloorBelow() {
        return this.processor.checkFloorBelow(this.mapID, this.buildingID);
    }

    public FloorMap getFloorAbove() {
        return this.processor.getFloorAbove(this.mapID);
    }

    public FloorMap getFloorBelow() {
        return this.processor.getFloorBelow(this.mapID);
    }

}