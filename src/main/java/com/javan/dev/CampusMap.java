package com.javan.dev;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONObject;

/**
 * @author: Brad McGlynn <bmcglyn4@uwo.ca>
 * @version: 1.0
 * @since: 1.0
 */
public final class CampusMap implements Map{
    /**
     * Declaring the file path, ID, type and BuildingArray of the Campus.
     */
    private String filePath;
    private int mapID;
    private String mapType;
    private ArrayList<BuildingMap> buildingArray = new ArrayList<BuildingMap>();

    /**
     * Private variable to hold instance of CampusMap
     */
    private static CampusMap INSTANCE;

    /**
     * Constructor for the CampusMap class to initialize the ID, type and filePath
     * @param campusName
     * @throws IOException
     */
    private CampusMap(int mapID) throws IOException {
        this.filePath = "data/images/maps/campusMap.png";
        this.mapID = mapID;
        this.mapType = "CAMPUS";

        /**
         * Add BuildingMaps to buildingArray
         */
        buildingArray = JsonReader.getBuildingMaps("data/images/maps/metadata/mapMetadata.json");
    }

    /**
     * Getter for CampusMap Instance
     * @throws IOException
     */
    public static CampusMap getInstance(int mapID) {
        if (INSTANCE == null) {
            try {
                INSTANCE = new CampusMap(mapID);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return INSTANCE;
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

    /**
     * Adds Bulding to BuildingArray
     * @param PointOfInterest
     * @return POI
     */
    public String addBuilding(BuildingMap newBuilding) {
        this.buildingArray.add(newBuilding);
        return null;
    }

    /**
     * Getter for the BuildingMap
     * @param PointOfInterest
     * @return POI
     */
    public BuildingMap getBuilding(int buildingId) {
        for (int i = 0; i < this.buildingArray.size(); i++) {
            if (this.buildingArray.get(i).getMapID() == buildingId) {
                return this.buildingArray.get(i);
            }
        }
        return null;
    }

    /**
     * Getter for the BuildingArray
     */
    public ArrayList<BuildingMap> getBuildingArray() {
        return this.buildingArray;
    }

    public JSONObject toJSON() {
        return null;
    }
}