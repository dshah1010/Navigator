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
    private int Id;
    private ArrayList<PointOfInterest> POIs = new ArrayList<>;
    private ArrayList<int> layers = new ArrayList<>;
    private String filePath;
    /**
     * Private variable to hold the instance of the data processor
     */
    private DataProcessor processor = DataProcessor.getInstance();

    /**
     * Constructor for the FloorMap class to initialize the ID
     * @param floorId
     */
    public FloorMap(Map mapInfo) {
        this.floorId = mapInfo.floorId;
        this.filePath = mapInfo.filePath;
    }


    /**
     * Getter for the floorId
     * @param None
     * @return floorId
     */
    public int getId() {
        return this.floorId;
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
    public String searchPOI(String PointOfInterest) {
        for (int i = 0; i < this.POIs.size(); i++) {
            if (this.POIs[i].name.equalsIgnoreCase(PointOfInterest)) {
                return this.POIs[i];
            }
        }
        return null;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public boolean checkfloorAbove() {
        return this.processor.checkfloorAbove(this.floorId);
    }

    public boolean checkfloorBelow() {
        return this.processor.checkfloorBelow(this.floorId)
    }

    public Map getfloorAbove() {
        return this.processor.getfloorAbove(this.floorID);
    }

    public Map getfloorBelow() {
        return this.processor.getfloorBelow(this.floorID);
    }

    public static void main (int mapID) {
        Map floor = new Map();
        DataProcessor processor = DataProcessor.getInstance();
        floor.FloorMap(processor.loadMap(mapID));
    }
}