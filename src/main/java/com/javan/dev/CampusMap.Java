package com.javan.dev;
import java.util.ArrayList;

/**
 * @author: Brad McGlynn <bmcglyn4@uwo.ca>
 * @version: 1.0
 * @since: 1.0
 */
public class CampusMap {
    /**
     * Declaring the username and password of the user.
     */
    private String campusName;
    private ArrayList<BuildingMap> BuildingArray = new ArrayList<>;
    // TODO: add variable to contain map of campus

    /**
     * Constructor for the CampusMap class to initialize the name/ ID
     * @param campusName
     */
    public CampusMap(String campusName) {
        this.campusName = campusName;
    }


    /**
     * Getter for the campusName
     * @param None
     * @return campusName
     */
    public String getCampusName() {
        return this.campusName;
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
     * Getter for the PointOfInterest
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