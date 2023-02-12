package com.javan.dev;
import java.util.ArrayList;

/**
 * @author: Brad McGlynn <bmcglyn4@uwo.ca>
 * @version: 1.0
 * @since: 1.0
 */
public class FloorMap {
    /**
     * Declaring the username and password of the user.
     */
    private String floorName;
    private int floorId;
    private ArrayList<PointOfInterest> POIs = new ArrayList<>;
    // TODO: add variale to contain map of floor

    /**
     * Constructor for the FloorMap class to initialize the name/ ID
     * @param floorName
     * @param floorId
     */
    public FloorMap(String floorName, int floorId) {
        this.floorName = floorName;
        this.floorId = floorId;
    }


    /**
     * Getter for the floorName
     * @param None
     * @return floorName
     */
    public String getFloorName() {
        return this.floorName;
    }

    /**
     * Getter for the floorId
     * @param None
     * @return floorId
     */
    public int getFloorId() {
        return this.floorId;
    }

    /**
     * Adds PointOfInterest to POIs
     * @param PointOfInterest
     * @return POI
     */
    public String addPOI(PointOfInterest newPointOfInterest) {
        this.POIs.add(newPointOfInterest);
        return null;
    }

    /**
     * Getter for the PointOfInterest
     * @param PointOfInterest
     * @return POI
     */
    public String getPOI(String PointOfInterest) {
        for (int i = 0; i < this.POIs.size(); i++) {
            if (this.POIs[i].name.equalsIgnoreCase(PointOfInterest)) {
                return this.POIs[i];
            }
        }
        return null;
    }
}