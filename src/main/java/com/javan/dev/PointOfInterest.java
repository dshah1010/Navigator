package com.javan.dev;
import org.json.JSONObject;
import org.json.JSONArray;
import com.google.gson.*;

/**
 * @author: Deep Shah <dshah228@uwo.ca>
 * @version: 1.0
 * @since: 1.0
 */
public class PointOfInterest {
    
    private int ID;
    private String name; 
    private int userID;
    private Boolean isUserMade;
    private String POI_type;
    private int[] coordinates = {0,0};
    private int floorID;
    private int buildingID;
    private Boolean isFavourited;
    private String description;
    private int roomNumber;
    private String mapFilePath;

    /**
     * Private variable to hold the instance of the data processor
     */
    private DataProcessor processor = DataProcessor.getInstance();


    /**
     * Constructor for the POI
     * @param name, the name of the POI
     */
    public PointOfInterest(String name, int userID, boolean isUsermade, String POI_Type, int coordinatesX, int coordinatesY, int floorID, int buildingID, Boolean isFavourited, String description, int roomNumber)  {
        this.name = name;
        this.userID = userID;
        this.isUserMade = isUsermade;
        this.POI_type = POI_Type;
        this.coordinates[0] = coordinatesX;
        this.coordinates[1] = coordinatesY;
        this.floorID = floorID;
        this.buildingID = buildingID;
        this.isFavourited = isFavourited;
        this.description = description;
        this.roomNumber = roomNumber;
        this.ID = processor.makeNewPOIID();
        this.mapFilePath = processor.loadMapFilePath(this.buildingID, this.floorID, "FLOOR");
    }

    /**
     * Getter for the POI Id
     * @return int of the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * Getter for building / floor ID
     * @return
     */
    public String getBuildingFloorID() {
        return buildingID + " " + floorID;
    }

    /**
     * Set building ID
     * @return
     */
    public int setBuildingID(int buildingID) {
        return this.buildingID = buildingID;
    }

    /**
     * Set floor ID
     * @return
     */
    public int setFloorID(int floorID) {
        return this.floorID = floorID;
    }


    /**
     * Getter for the name of the POI
     * @return String of the name
     */
    public String getName() {
        return this.name; 
    }

    /**
     * Getter for the file path of the map that the POI is on
     * @return String of the name
     */
    public String getMapFilePath() {
        return this.mapFilePath; 
    }

    /**
     * Getter for the ID of the user
     * @return int of the ID
     */
    public int getUserID() {
        return this.userID;
    }

    /**
     * Getter for the user made POI
     * @return boolean of user made
     */
    public boolean getIsUserMade() {
        return this.isUserMade;
    }

    /**
     * Getter for the type of the POI
     * @return String of the type
     */
    public String getPOItype() {
        return POI_type;
    }

    /**
     * Getter for the coordinates of the POI 
     * @return int[] of the coordinates
     */
    public int[] getCoordinates() {
        return this.coordinates;
    }

    /** 
     * Getter for the floor ID of the POI
     * @return int of the floor ID
     */
    public int getFloorID() {
        return this.floorID;
    }

    /**
     * Getter for the building ID of the POI
     * @return int of the building ID
     */
    public int getBuildingID() {
        return this.buildingID;
    }

    /** 
     * Getter for the favourited POI
     * @return int of the favourited 
     */
    public Boolean getIsFavourited() {
        return this.isFavourited;
    }

    /**
     * Getter for the description of the POI
     * @return String of the description 
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Getter for the room number of the POI
     * @return int of the room number 
     */
    public int getRoomNumber() {
        return this.roomNumber;
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("name", this.name);
        json.put("ID", this.ID);
        json.put("userID", this.userID);
        json.put("isUserMade", this.isUserMade);
        json.put("POI_type", this.POI_type);
        json.put("coordinates", new JSONArray(this.coordinates));
        json.put("floorID", this.floorID);
        json.put("buildingID", this.buildingID);
        json.put("isFavourited", this.isFavourited);
        json.put("description", this.description);
        json.put("roomNumber", this.roomNumber);
        return json;
    }

    /**
     * TODO: This should be temporary. Not sure how we want to set favourited for a particular user but also know how to get favourite
     * for a specific user because getIsFavourited() is for an entire POI. not sure how it will work for a POI that is not user-created and
     * favourited by multiple users
     */
    public void setIsFavourited(boolean isFavourited) {
        this.isFavourited = isFavourited;
    }

    /**
     * Setter for ID
     */
    public void setID(int ID) {
        this.ID = ID;
    }
    
    /**
     * Setter for POI X and Y position
     */
    public void setCoordinates(int x, int y) {
        this.coordinates[0] = x;
        this.coordinates[1] = y;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setRoomNumber(int newRoomNumber) {
        this.roomNumber = newRoomNumber;
    }

    public void setDescription(String newDesc) {
        this.description = newDesc;
    }

    public void setPOItype(String newLayer) {
        this.POI_type = newLayer;
    }
}