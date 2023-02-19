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
    private String buildingName;
    private int buildingId;
    private ArrayList<FloorMap> FloorArray = new ArrayList<>;
    // TODO: add variable to contain map of building

    /**
     * Constructor for the BuildingMap class to initialize the name/ ID
     * @param buildingName
     * @param buildingId
     */
    public BuildingMap(String buildingName, int buildingId) {
        this.buildingName = buildingName;
        this.buildingId = buildingId;
    }


    /**
     * Getter for the buildingName
     * @param None
     * @return buildingName
     */
    public String getBuildingName() {
        return this.buildingName;
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
    
    public static void main (int mapID) {
        Map buiilding = new Map();
        DataProcessor processor = DataProcessor.getInstance();
        building.BuildingMap(processor.loadMap(mapID));
    }
}