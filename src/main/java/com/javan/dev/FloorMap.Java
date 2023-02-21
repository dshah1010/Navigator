package com.javan.dev;
import java.util.ArrayList;

/**
 * @author: Brad McGlynn <bmcglyn4@uwo.ca>
 * @version: 1.0
 * @since: 1.0
 */
public class FloorMap implements Map{
    /**
     * Declaring the Id, Point of Interest array and layers array of the Floor.
     */
    private String filePath;
    private int mapID;
    private String mapType;
    private ArrayList<PointOfInterest> POIs = new ArrayList<PointOfInterest>();
    private ArrayList<Integer> layers = new ArrayList<Integer>();
    
    /**
     * Private variable to hold the instance of the data processor
     */
    private DataProcessor processor = DataProcessor.getInstance();

    /**
     * Constructor for the FloorMap class to initialize the ID
     * @param floorId
     */
    public FloorMap(int mapID) {
        this.mapID = mapID;
        this.filePath = processor.loadMapFilePath(mapID, "FLOOR");
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
     * Getter for mapType
     * @param None
     * @return String
     */
    public String getMapType() {
        return this.mapType;
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
        return this.processor.checkfloorAbove(this.mapID);
    }

    public boolean checkFloorBelow() {
        return this.processor.checkFloorBelow(this.mapID);
    }

    public FloorMap getFloorAbove() {
        return this.processor.getFloorAbove(this.mapID);
    }

    public FloorMap getFloorBelow() {
        return this.processor.getFloorBelow(this.mapID);
    }

}