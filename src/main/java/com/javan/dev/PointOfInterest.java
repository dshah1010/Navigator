package com.javan.dev;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.*;

/**
 * @author: Deep Shah <dshah228@uwo.ca>, Brad McGlynn <bmcglyn4@uwo.ca>
 * @version: 1.0
 * @since: 1.0
 */
public class PointOfInterest {
    
    private int ID;
    private String name; 
    private int userID;
    private Boolean isUserMade;
    private String poiType;
    private int[] coordinates = {0,0};
    private int floorID;
    private int buildingID;
    private ArrayList<Integer> userFavouritesList = new ArrayList<Integer> ();
    private String description;
    private String roomNumber;
    private String mapFilePath;
    private boolean isVisible;

    /**
     * Private variable to hold the instance of the data processor
     */
    private DataProcessor processor = DataProcessor.getInstance();


    /**
     * Constructor for the POI
     * @param name, the name of the POI
     */
    public PointOfInterest(String name, int userID, boolean isUsermade, String poiType, int coordinatesX, int coordinatesY, int floorID, int buildingID, ArrayList<Integer> userFavouritesList, String description, String roomNumber, boolean isVisible)  {
        this.name = name;
        this.userID = userID;
        this.isUserMade = isUsermade;
        this.poiType = poiType;
        this.coordinates[0] = coordinatesX;
        this.coordinates[1] = coordinatesY;
        this.floorID = floorID;
        this.buildingID = buildingID;
        this.userFavouritesList = userFavouritesList;
        this.description = description;
        this.roomNumber = roomNumber;
        this.ID = processor.makeNewPOIID();
        this.mapFilePath = processor.loadMapFilePath(this.buildingID, this.floorID, "FLOOR");
        this.isVisible = isVisible;
    }

    /**
     * Getter for the POI Id
     * @param None
     * @return int of the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * Getter for building / floor ID
     * @param None
     * @return String combination of building and floor ID
     */
    public String getBuildingFloorID() {
        return buildingID + " " + floorID;
    }


    /**
     * Getter for the name of the POI
     * @param None
     * @return String of the name
     */
    public String getName() {
        return this.name; 
    }

    /**
     * Getter for the file path of the map that the POI is on
     * @param None
     * @return String of the name
     */
    public String getMapFilePath() {
        return this.mapFilePath; 
    }

    /**
     * Getter for the ID of the user
     * @param None
     * @return int of the ID
     */
    public int getUserID() {
        return this.userID;
    }

    /**
     * Getter for the user made POI
     * @param None
     * @return boolean of user made
     */
    public boolean getIsUserMade() {
        return this.isUserMade;
        
    }

    /**
     * Getter for the type of the POI
     * @param None
     * @return String of the type
     */
    public String getPOItype() {
        return poiType;
    }

    /**
     * Getter for the coordinates of the POI 
     * @param None
     * @return int[] of the coordinates
     */
    public int[] getCoordinates() {
        return this.coordinates;
    }

    /** 
     * Getter for the floor ID of the POI
     * @param None
     * @return int of the floor ID
     */
    public int getFloorID() {
        return this.floorID;
    }

    /**
     * Getter for the building ID of the POI
     * @param None
     * @return int of the building ID
     */
    public int getBuildingID() {
        return this.buildingID;
    }

    /** 
     * Getter for the favourited POI
     * @param int userID
     * @return int of the favourited 
     */
    public Boolean getIsFavourited(int userID) {
        for (int id : this.userFavouritesList) {
            if(id == userID) {
                return true;
            }
        }
        return false;
    }

    /**
     * Getter for the description of the POI
     * @param None
     * @return String of the description 
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Getter for the room number of the POI
     * @param None
     * @return String of the room number 
     */
    public String getRoomNumber() {
        return this.roomNumber;
    }
    /**
     * Getter for the isVisible state of POI
     * @param None
     * @return boolenan of isVisible state
     */
    public Boolean getisVisible(){
        return this.isVisible;
    }

    /**
     * Method to make POI into a JSON object
     * @param None
     * @return JSONObject json
     */
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("name", this.name);
        json.put("ID", this.ID);
        json.put("userID", this.userID);
        json.put("isUserMade", this.isUserMade);
        json.put("poiType", this.poiType);
        json.put("coordinates", new JSONArray(this.coordinates));
        json.put("floorID", this.floorID);
        json.put("buildingID", this.buildingID);
        json.put("userFavouritesList", this.userFavouritesList);
        json.put("description", this.description);
        json.put("roomNumber", this.roomNumber);
        json.put("isVisible", this.isVisible);
        return json;
    }
    
    /**
     * Setter for POI X and Y position
     * @param int x
     * @param int y
     * @return None
     */
    public void setCoordinates(int x, int y) {
        this.coordinates[0] = x;
        this.coordinates[1] = y;
    }

    /**
     * Setter for POI building ID
     * @param int buildingID
     * @return None
     */
    public int setBuildingID(int buildingID) {
        return this.buildingID = buildingID;
    }

    /**
     * Setter for POI floor ID
     * @param int floorID
     * @return None
     */
    public void setFloorID(int floorID) {
        this.floorID = floorID;
    }

    /**
     * Setter for POI name
     * @param String newName
     * @return None
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Setter for POI room number
     * @param int newRoomNumber
     * @return None
     */
    public void setRoomNumber(String newRoomNumber) {
        this.roomNumber = newRoomNumber;
    }

    /**
     * Setter for POI description
     * @param String newDesc
     * @return None
     */
    public void setDescription(String newDesc) {
        this.description = newDesc;
    }

    /**
     * Setter for POI layer type
     * @param String newLayer
     * @return None
     */
    public void setPOItype(String newLayer) {
        this.poiType = newLayer;
    }

    
    /**
     * Setter for POI isFavourited status
     * @param int userID
     * @return None
     */
    public void setIsFavourited(int userID) {
        Boolean in = false;
        for (int i = 0; i < userFavouritesList.size(); i++) {
            if (userFavouritesList.get(i) == userID) {
                this.userFavouritesList.remove(i);
                in = true;
            }
        }
        if (!in) {
            this.userFavouritesList.add(userID);
        }
        
    }

    /**
     * Setter for POI ID
     * @param int ID
     * @return None
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Setter for POI visibility state
     * @param boolean isVisible
     * @return None
     */
    public void setisVisible(boolean isVisible) {
        this.isVisible= isVisible;
    }
}