package com.javan.dev;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;

import com.google.gson.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author: Brad McGlynn <bmcglyn4@uwo.ca>, Dylan Sta Ana <dstaana@uwo.ca>, Deep Ashishkumar Shah <dshah228@uwo.ca>
 * @version: 1.1
 * @since: 1.0
 */
public class JsonReader {

    /**
     * Method to read the contents found at fileName and returns them as a string
     * @param String fileName, file to be read
     * @return String content, file content as a string
     * @throws IOException If there is an error reading the map metadata file.
     */
    public static String read(String fileName) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(fileName)));
        return content;
    }

    /**
     * Returns the floor maps associated with this map metadata file.
     * @return An ArrayList of FloorMap objects representing the floor maps.
     * @throws IOException If there is an error reading the map metadata file.
     */
    public static ArrayList<FloorMap> getFloorMaps(String mapJsonFilePath) throws IOException {
        /*
         * Initialize an ArrayList to store the floor maps.
         */
        ArrayList<FloorMap> floorMaps = new ArrayList<FloorMap>();
        /*
         * Use the JSONReader to read the map metadata file.
         */
        JSONArray buildings = new JSONArray(new String(JsonReader.read(mapJsonFilePath)));
        /*
         * Loop through each building in the JSON data.
         */
        for (int i = 0; i < buildings.length(); i++) {
            JSONObject buildingObj = (JSONObject) buildings.get(i);
            /*
             * Get the building ID and name.
             */
            int buildingID = ((Integer) buildingObj.get("buildingID")).intValue();
            String buildingName = (String) buildingObj.get("mapName");
            /*
             * Get the floor maps for this building.
             */
            JSONArray floors = (JSONArray) buildingObj.get("floorMaps");
            /*
             * Loop through each floor in the JSON data.
             */
            for (int j = 0; j < floors.length(); j++) {
                JSONObject floorObj = (JSONObject) floors.get(j);
                /*
                 * Get the floor map ID and name.
                 */
                int floorID = ((Integer) floorObj.get("mapID")).intValue();
                String floorName = (String) floorObj.get("mapName");
                /*
                 * Get the file path for the floor map image.
                 */
                String filePath = (String) floorObj.get("filePath");
                /*
                 * Create a new FloorMap object and add it to the ArrayList.
                 */
                FloorMap floorMap = new FloorMap(buildingID, floorID);
                //floorMap.setPOIs(processor.getPOIs(filePath)); TODO: implement both setPOIs in FloorMap and getPOIs in DataProcessor
                //floorMap.setLayers(processor.getLayers(filePath)); TODO: implement both setLayers in FloorMap and getLayers in DataProcessor
                floorMaps.add(floorMap);
            }
        }
        /*
         * Return the ArrayList of floor maps.
         */
        return floorMaps;
    }

    /**
     * Method to get all of the building maps from the JSON file and create BuildingMaps, adding them to CampusMap
     */
    public static ArrayList<BuildingMap> getBuildingMaps(String mapJsonFilePath) throws IOException {
        /*
         * Initialize an ArrayList to store the building maps.
         */
        ArrayList<BuildingMap> buildingMaps = new ArrayList<BuildingMap>();
        /*
         * Use the JSONReader to read the map metadata file.
         */
        JSONArray buildings = new JSONArray(new String(JsonReader.read(mapJsonFilePath)));
        /*
         * Loop through each building in the JSON data.
         */
        for (int i = 0; i < buildings.length(); i++) {
            JSONObject buildingObj = (JSONObject) buildings.get(i);
            /*
             * Get the building ID
             */
            int buildingID = ((Integer) buildingObj.get("buildingID")).intValue();

            /**
             * Get the building name
             */
            String buildingName = (String) buildingObj.get("mapName");
            /*
             * Create a new BuildingMap object and add it to the ArrayList.
             */
            BuildingMap buildingMap = new BuildingMap(buildingID);
            buildingMap.setName(buildingName);
            buildingMaps.add(buildingMap);
        }
        /*
         * Return the ArrayList of building maps.
         */
        return buildingMaps;
    }


    /**
     * Function to find filePath of FloorMap using Gson
     * @param int buildingID
     * @param int mapID
     * @return file path of floor map
     * @throws FileNotFoundException
     */
    public static String getFloorMapPathFromID(int buildingID, int mapID, String jsonFilePath) {
        FileReader reader = null;
        try {
            reader = new FileReader(jsonFilePath);
            JsonArray buildingMaps = JsonParser.parseReader(reader).getAsJsonArray();
            String mapPath;
            JsonArray floorMaps;
            int counter = 0;
            int floorCounter = 0;

            while (counter < buildingMaps.size()) {
                if (counter + 1 == buildingID){ // counter + 1 because buildingIDs start at 1 rather than 0, but 
                    // the JsonArray is 0 indexed
                    floorMaps = buildingMaps.get(counter).getAsJsonObject().get("floorMaps").getAsJsonArray();
                    while (floorCounter < floorMaps.size()) {
                        mapPath = floorMaps.get(floorCounter).getAsJsonObject().get("filePath").getAsString();
                        int id = floorMaps.get(floorCounter).getAsJsonObject().get("mapID").getAsInt();
                        if (id == mapID) {
                            return mapPath;
                        }
                        floorCounter++;
                    }
                }
                counter++;
                floorCounter = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * Function to find filePath of BuildingMap using Gson
     * @param int buildingID
     * @return file path of building map
     * @throws FileNotFoundException
     */
    public static String getBuildingMapPathFromID(int buildingID, String jsonFilePath) {
        FileReader reader = null;
        try {
            reader = new FileReader(jsonFilePath);
            JsonArray buildingMaps = JsonParser.parseReader(reader).getAsJsonArray();
            String mapPath;
            int counter = 0;

            while (counter < buildingMaps.size()) {
                mapPath = buildingMaps.get(counter).getAsJsonObject().get("filePath").getAsString();
                int id = buildingMaps.get(counter).getAsJsonObject().get("buildingID").getAsInt();
                if (id == buildingID) {
                    return mapPath;
                }
                counter++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * function to get userFavouites POI Data
     * @param int userID
     * @return ArrayList<PointOfInterest>
     */
    public static ArrayList<PointOfInterest> favouritesList(int userID) {

        FileReader reader = null;

        /*
         * attempts to read file 
         */
        try {
            
            reader = new FileReader("data/PointOfInterests/PointOfInterestMetadata.json");
            JsonArray POIDataArray = JsonParser.parseReader(reader).getAsJsonArray();
            ArrayList<PointOfInterest> arrayList = new ArrayList<PointOfInterest>();

            /*
             * loops through json file to find user made POIs
             */ 
            for (JsonElement POI : POIDataArray) {

                JsonObject poiObject = POI.getAsJsonObject();
                JsonArray userFavouritesArray = poiObject.getAsJsonArray("userFavouritesList");
                ArrayList<Integer> userFavouritesData = new ArrayList<Integer>();

                if (userFavouritesArray.size() > 0) {
                    for (int i = 0; i < userFavouritesArray.size(); i++) {
                        /**
                         * Get current value from the userFavouritesArray and store as int, then add to array
                         */
                        int userFavourite = userFavouritesArray.get(i).getAsInt();
                        userFavouritesData.add(userFavourite);
                    }
                }

                if (userFavouritesData.contains(userID)) {

                    /*
                    * declares all data from json file
                    * then creates a POI object
                    * which is then added to arraylist of type POI
                    */   

                    int poiID = poiObject.get("ID").getAsInt();  
                    String name  = poiObject.get("name").getAsString();
                    boolean isUserMade = poiObject.get("isUserMade").getAsBoolean();
                    String POI_Type = poiObject.get("POI_type").getAsString();
                    JsonArray jsoncoordinateArray = poiObject.get("coordinates").getAsJsonArray();
                    int[] coordinateArray = new int[2];

                    for (int i=0; i< coordinateArray.length; i++){
                        coordinateArray[i] = jsoncoordinateArray.get(i).getAsInt();
                    }

                    int floorID = poiObject.get("floorID").getAsInt();
                    int buildingID = poiObject.get("buildingID").getAsInt();
                    ArrayList<Integer> userFavouritesList = new ArrayList<Integer>();

                    for (int i = 0; i < userFavouritesData.size(); i++) {
                        userFavouritesList.add(i, userFavouritesData.get(i));
                    }

                    String description = poiObject.get("description").getAsString();
                    int roomNumber = poiObject.get("roomNumber").getAsInt();
                    Boolean isVisible = poiObject.get("isVisible").getAsBoolean();
                    PointOfInterest POIdata = new PointOfInterest(name, userID, isUserMade, POI_Type, coordinateArray[0], coordinateArray[1], floorID, buildingID, userFavouritesList, description, roomNumber, isVisible);
                    POIdata.setID(poiID);
                    arrayList.add(POIdata);
                }   
            }
            /**
             * Sort arraylist by the POI name alphabetically (Each POI object's name attribute)
             * Put it into a new arraylist that will be returned
             */
            return sortPOIArray(arrayList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


      /**
     * function to get buildinguserFavouites POI Data
     * @param int userID
     * @return ArrayList<PointOfInterest>
     */
    public static ArrayList<BuildingPointOfInterest> buildingfavouritesList(int userID) {

        FileReader reader = null;

        /*
         * attempts to read file 
         */
        try {
            
            reader = new FileReader("data/PointOfInterests/BuildingsPOIMetadata.json");
            JsonArray POIDataArray = JsonParser.parseReader(reader).getAsJsonArray();
            ArrayList<BuildingPointOfInterest> arrayList = new ArrayList<BuildingPointOfInterest>();

            /*
             * loops through json file to find user made POIs
             */ 
            for (JsonElement POI : POIDataArray) {

                JsonObject buildingpoiObject = POI.getAsJsonObject();
                JsonArray userFavouritesArray = buildingpoiObject.getAsJsonArray("userFavouritesList");
                ArrayList<Integer> userFavouritesData = new ArrayList<Integer>();

                if (userFavouritesArray != null) {
                    for (int i =0; i < userFavouritesArray.size(); i++) {
                        userFavouritesData.add(i, userFavouritesArray.getAsInt());
                    }
                }

                if (userFavouritesData.contains(userID)) {

                    /*
                    * declares all data from json file
                    * then creates a building POI object
                    * which is then added to arraylist of type building POI
                    */   

                    int poiID = buildingpoiObject.get("ID").getAsInt();  
                    String name  = buildingpoiObject.get("name").getAsString();
                    boolean isUserMade = buildingpoiObject.get("isUserMade").getAsBoolean();
                    String POI_Type = buildingpoiObject.get("POI_type").getAsString();
                    JsonArray jsoncoordinateArray = buildingpoiObject.get("coordinates").getAsJsonArray();
                    int[] coordinateArray = new int[2];

                    for (int i=0; i< coordinateArray.length; i++){
                        coordinateArray[i] = jsoncoordinateArray.get(i).getAsInt();
                    }

                   
                    int buildingID = buildingpoiObject.get("buildingID").getAsInt();
                    ArrayList<Integer> userFavouritesList = new ArrayList<Integer>();

                    for (int i = 0; i < userFavouritesData.size(); i++) {
                        userFavouritesList.add(i, userFavouritesData.get(i));
                    }
                    String description = buildingpoiObject.get("desciption").getAsString();
                    Boolean isVisible = buildingpoiObject.get("isVisible").getAsBoolean();
                    BuildingPointOfInterest POIdata = new BuildingPointOfInterest(name, userID, isUserMade, POI_Type, coordinateArray[0], coordinateArray[1],  buildingID, userFavouritesList, description, isVisible);
                    POIdata.setID(poiID);
                    arrayList.add(POIdata);
                }   
            }
            return arrayList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


     /**
     * function to get user made POI Data
     * @param int userID
     * @return ArrayList<PointOfInterest>
     */
    public static ArrayList<PointOfInterest> userPOIList(int userID) {

        FileReader reader = null;

        /*
         * attempts to read file 
         */
        try {

            reader = new FileReader("data/PointOfInterests/PointOfInterestMetadata.json");
            JsonArray POIDataArray= JsonParser.parseReader(reader).getAsJsonArray();
            ArrayList<PointOfInterest> arrayList = new ArrayList<PointOfInterest>();

            /*
             * loops through json file to find user made POIs
             */ 
            for (JsonElement POI : POIDataArray) {

                JsonObject poiObject = POI.getAsJsonObject();
                JsonArray userFavouritesArray = poiObject.getAsJsonArray("userFavouritesList");
                ArrayList<Integer> userFavouritesData = new ArrayList<Integer>();

                if (userFavouritesArray != null) {
                    for (int i =0; i < userFavouritesArray.size(); i++) {
                        /**
                         * Get current value from the userFavouritesArray and store as int, then add to array
                         */
                        int userFavourite = userFavouritesArray.get(i).getAsInt();
                        userFavouritesData.add(userFavourite);
                    }
                }

                if (poiObject.get("isUserMade").getAsBoolean() == true && poiObject.get("userID").getAsInt() == userID){

                    /*
                    * declares all data from json file
                    * then creates a POI object
                    * which is then added to arraylist of type POI
                    */

                    int poiID = poiObject.get("ID").getAsInt();
                    String name  = poiObject.get("name").getAsString();
                    boolean isUserMade = poiObject.get("isUserMade").getAsBoolean();
                    String POI_Type = poiObject.get("POI_type").getAsString();
                    JsonArray jsoncoordinateArray = poiObject.get("coordinates").getAsJsonArray();
                    int[] coordinateArray = new int[2];
                    
                    for (int i=0; i< coordinateArray.length; i++){
                        coordinateArray[i] = jsoncoordinateArray.get(i).getAsInt();
                    }

                    int floorID = poiObject.get("floorID").getAsInt();
                    int buildingID = poiObject.get("buildingID").getAsInt();
                    ArrayList<Integer> userFavouritesList = new ArrayList<Integer>();

                    for (int i = 0; i < userFavouritesData.size(); i++) {
                        userFavouritesList.add(i, userFavouritesData.get(i));
                    }

                    String description = poiObject.get("description").getAsString();
                    int roomNumber = poiObject.get("roomNumber").getAsInt();
                    Boolean isVisible = poiObject.get("isVisible").getAsBoolean();
                    PointOfInterest POIdata = new PointOfInterest(name, userID, isUserMade, POI_Type, coordinateArray[0], coordinateArray[1], floorID, buildingID, userFavouritesList, description, roomNumber, isVisible);
                    POIdata.setID(poiID);
                    arrayList.add(POIdata);
                }             
            }
            /**
             * Sort arraylist by the POI name alphabetically (Each POI object's name attribute)
             * Put it into a new arraylist that will be returned
             */
            return sortPOIArray(arrayList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    
     /**
     * function to get user made building POI Data
     * @param int userID
     * @return ArrayList<BuildingPointOfInterest>
     */
    public static ArrayList<BuildingPointOfInterest> buildingUserPOIList(int userID) {

        FileReader reader = null;

        /*
         * attempts to read file 
         */
        try {

            reader = new FileReader("data/PointOfInterests/BuildingsPOIMetadata.json");
            JsonArray POIDataArray= JsonParser.parseReader(reader).getAsJsonArray();
            ArrayList<BuildingPointOfInterest> arrayList = new ArrayList<BuildingPointOfInterest>();

            /*
             * loops through json file to find user made POIs
             */ 
            for (JsonElement POI : POIDataArray) {

                JsonObject poiObject = POI.getAsJsonObject();
                JsonArray userFavouritesArray = poiObject.getAsJsonArray("userFavouritesList");
                ArrayList<Integer> userFavouritesData = new ArrayList<Integer>();

                if (userFavouritesArray != null) {
                    for (int i =0; i < userFavouritesArray.size(); i++) {
                        userFavouritesData.add(i, userFavouritesArray.getAsInt());
                    }
                }

                if (poiObject.get("isUserMade").getAsBoolean() == true && poiObject.get("userID").getAsInt() == userID){

                    /*
                    * declares all data from json file
                    * then creates a POI object
                    * which is then added to arraylist of type POI
                    */

                    int poiID = poiObject.get("ID").getAsInt();
                    String name  = poiObject.get("name").getAsString();
                    boolean isUserMade = poiObject.get("isUserMade").getAsBoolean();
                    String POI_Type = poiObject.get("POI_type").getAsString();
                    JsonArray jsoncoordinateArray = poiObject.get("coordinates").getAsJsonArray();
                    int[] coordinateArray = new int[2];
                    
                    for (int i=0; i< coordinateArray.length; i++){
                        coordinateArray[i] = jsoncoordinateArray.get(i).getAsInt();
                    }

                    int buildingID = poiObject.get("buildingID").getAsInt();
                    ArrayList<Integer> userFavouritesList = new ArrayList<Integer>();

                    for (int i = 0; i < userFavouritesData.size(); i++) {
                        userFavouritesList.add(i, userFavouritesData.get(i));
                    }
                    String description = poiObject.get("description").getAsString();
                    Boolean isVisible = poiObject.get("isVisible").getAsBoolean();
                    BuildingPointOfInterest POIdata = new BuildingPointOfInterest(name, userID, isUserMade, POI_Type, coordinateArray[0], coordinateArray[1], buildingID, userFavouritesList, description, isVisible);
                    POIdata.setID(poiID);
                    arrayList.add(POIdata);
                }             
            }
            return arrayList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }    

    /**
     * function to get Floor POIs currently available to the user
     * @param int userID
     * @return ArrayList<PointOfInterest>
     */
    public static ArrayList<PointOfInterest> universalPOIs(int userID) {

        FileReader reader = null;

        /**
         * Get the current map ID
         */
        MapComponent mapComponent = MapComponent.getInstance();
        int mapID = mapComponent.getCurrentMapID();

        /*
         * attempts to read file 
         */
        try {

            reader = new FileReader("data/PointOfInterests/PointOfInterestMetadata.json");
            JsonArray POIDataArray= JsonParser.parseReader(reader).getAsJsonArray();
            ArrayList<PointOfInterest> arrayList = new ArrayList<PointOfInterest>();

            /*
             * loops through json file to find POIs available to this user
             */ 
            for (JsonElement POI : POIDataArray) {

                JsonObject poiObject = POI.getAsJsonObject();
                JsonArray userFavouritesArray = poiObject.getAsJsonArray("userFavouritesList");
                ArrayList<Integer> userFavouritesData = new ArrayList<Integer>();

                if (userFavouritesArray != null) {
                    for (int i =0; i < userFavouritesArray.size(); i++) {
                        /**
                         * Get current value from the userFavouritesArray and store as int, then add to array
                         */
                        int userFavourite = userFavouritesArray.get(i).getAsInt();
                        userFavouritesData.add(userFavourite);
                    }
                }
                
                // either developer made or user made POIs
                if (poiObject.get("userID").getAsInt() == 1 || poiObject.get("userID").getAsInt() == userID){

                    /*
                    * declares all data from json file
                    * then creates a POI object
                    * which is then added to arraylist of type POI
                    */

                    int poiID = poiObject.get("ID").getAsInt();
                    String name  = poiObject.get("name").getAsString();
                    boolean isUserMade = poiObject.get("isUserMade").getAsBoolean();
                    String POI_Type = poiObject.get("POI_type").getAsString();
                    JsonArray jsoncoordinateArray = poiObject.get("coordinates").getAsJsonArray();
                    int[] coordinateArray = new int[2];
                    for (int i=0; i< coordinateArray.length; i++){
                        coordinateArray[i] = jsoncoordinateArray.get(i).getAsInt();
                    }
                    int floorID = poiObject.get("floorID").getAsInt();
                    int buildingID = poiObject.get("buildingID").getAsInt();
                    ArrayList<Integer> userFavouritesList = new ArrayList<Integer>();

                    for (int i = 0; i < userFavouritesData.size(); i++) {
                        userFavouritesList.add(i, userFavouritesData.get(i));
                    }

                    String description = poiObject.get("description").getAsString();
                    int roomNumber = poiObject.get("roomNumber").getAsInt();
                    boolean isVisible = poiObject.get("isVisible").getAsBoolean();
                    if (mapID == floorID) {
                        PointOfInterest POIdata = new PointOfInterest(name, userID, isUserMade, POI_Type, coordinateArray[0], coordinateArray[1], floorID, buildingID, userFavouritesList, description, roomNumber, isVisible);
                        POIdata.setID(poiID);
                        arrayList.add(POIdata);
                    }
                }       
            }
            /**
             * Sort arraylist by the POI name alphabetically (Each POI object's name attribute)
             * Put it into a new arraylist that will be returned
             */
            return sortPOIArray(arrayList);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


        /**
     * function to get Floor POIs currently available to the user
     * @param int userID
     * @return ArrayList<PointOfInterest>
     */
    public static ArrayList<BuildingPointOfInterest> universalBuildingPOIs(int userID) {

        FileReader reader = null;

        /*
         * attempts to read file 
         */
        try {

            reader = new FileReader("data/PointOfInterests/BuildingsPOIMetadata.json");
            JsonArray POIDataArray= JsonParser.parseReader(reader).getAsJsonArray();
            ArrayList<BuildingPointOfInterest> arrayList = new ArrayList<BuildingPointOfInterest>();

            /*
             * loops through json file to find POIs available to this user
             */ 
            for (JsonElement POI : POIDataArray) {

                JsonObject poiObject = POI.getAsJsonObject();
                JsonArray userFavouritesArray = poiObject.getAsJsonArray("userFavouritesList");
                ArrayList<Integer> userFavouritesData = new ArrayList<Integer>();

                if (userFavouritesArray != null) {
                    for (int i =0; i < userFavouritesArray.size(); i++) {
                        /**
                         * Get current value from the userFavouritesArray and store as int, then add to array
                         */
                        int userFavourite = userFavouritesArray.get(i).getAsInt();
                        userFavouritesData.add(userFavourite);
                    }
                }
                

                // either developer made or user made POIs
                if (poiObject.get("userID").getAsInt() == 1 || poiObject.get("userID").getAsInt() == userID){

                    /*
                    * declares all data from json file
                    * then creates a POI object
                    * which is then added to arraylist of type POI
                    */

                    int poiID = poiObject.get("ID").getAsInt();
                    String name  = poiObject.get("name").getAsString();
                    boolean isUserMade = poiObject.get("isUserMade").getAsBoolean();
                    String POI_Type = poiObject.get("POI_type").getAsString();
                    JsonArray jsoncoordinateArray = poiObject.get("coordinates").getAsJsonArray();
                    int[] coordinateArray = new int[2];
                    for (int i=0; i< coordinateArray.length; i++){
                        coordinateArray[i] = jsoncoordinateArray.get(i).getAsInt();
                    }
                    int buildingID = poiObject.get("buildingID").getAsInt();
                    ArrayList<Integer> userFavouritesList = new ArrayList<Integer>();

                    for (int i = 0; i < userFavouritesData.size(); i++) {
                        userFavouritesList.add(i, userFavouritesData.get(i));
                    }

                    String description = poiObject.get("description").getAsString();
                    boolean isVisible = poiObject.get("isVisible").getAsBoolean();
                    {
                        BuildingPointOfInterest POIdata = new BuildingPointOfInterest(name, userID, isUserMade, POI_Type, coordinateArray[0], coordinateArray[1], buildingID, userFavouritesList, description, isVisible);
                        POIdata.setID(poiID);
                        arrayList.add(POIdata);
                    }
                }       
            }
            /**
             * Sort arraylist by the POI name alphabetically (Each POI object's name attribute)
             * Put it into a new arraylist that will be returned
             */
            return sortBuildingPOIArray(arrayList);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * function to get Current Building POIs currently available to the user
     * @param int userID
     * @param int currBuilding
     * @return ArrayList<PointOfInterest>
     */
    public static ArrayList<PointOfInterest> currentBuildingPOIS(int userID, int currBuilding) {

        FileReader reader = null;

        /**
         * Get the current map ID
         */
        MapComponent mapComponent = MapComponent.getInstance();
        int mapID = mapComponent.getCurrentMapID();

        /*
         * attempts to read file 
         */
        try {

            reader = new FileReader("data/PointOfInterests/PointOfInterestMetadata.json");
            JsonArray POIDataArray= JsonParser.parseReader(reader).getAsJsonArray();
            ArrayList<PointOfInterest> arrayList = new ArrayList<PointOfInterest>();

            /*
             * loops through json file to find POIs available to this user
             */ 
            for (JsonElement POI : POIDataArray) {

                JsonObject poiObject = POI.getAsJsonObject();
                JsonArray userFavouritesArray = poiObject.getAsJsonArray("userFavouritesList");
                ArrayList<Integer> userFavouritesData = new ArrayList<Integer>();

                if (userFavouritesArray != null) {
                    for (int i =0; i < userFavouritesArray.size(); i++) {
                        /**
                         * Get current value from the userFavouritesArray and store as int, then add to array
                         */
                        int userFavourite = userFavouritesArray.get(i).getAsInt();
                        userFavouritesData.add(userFavourite);
                    }
                }
                
                // either developer made or user made POIs
                if (poiObject.get("userID").getAsInt() == 1 || poiObject.get("userID").getAsInt() == userID){

                    /*
                    * declares all data from json file
                    * then creates a POI object
                    * which is then added to arraylist of type POI
                    */

                    int poiID = poiObject.get("ID").getAsInt();
                    String name  = poiObject.get("name").getAsString();
                    boolean isUserMade = poiObject.get("isUserMade").getAsBoolean();
                    String POI_Type = poiObject.get("POI_type").getAsString();
                    JsonArray jsoncoordinateArray = poiObject.get("coordinates").getAsJsonArray();
                    int[] coordinateArray = new int[2];
                    for (int i=0; i< coordinateArray.length; i++){
                        coordinateArray[i] = jsoncoordinateArray.get(i).getAsInt();
                    }
                    int floorID = poiObject.get("floorID").getAsInt();
                    int buildingID = poiObject.get("buildingID").getAsInt();
                    ArrayList<Integer> userFavouritesList = new ArrayList<Integer>();

                    for (int i = 0; i < userFavouritesData.size(); i++) {
                        userFavouritesList.add(i, userFavouritesData.get(i));
                    }

                    String description = poiObject.get("description").getAsString();
                    int roomNumber = poiObject.get("roomNumber").getAsInt();
                    boolean isVisible = poiObject.get("isVisible").getAsBoolean();
                    if (mapID != floorID && currBuilding == buildingID) {
                        PointOfInterest POIdata = new PointOfInterest(name, userID, isUserMade, POI_Type, coordinateArray[0], coordinateArray[1], floorID, buildingID, userFavouritesList, description, roomNumber, isVisible);
                        POIdata.setID(poiID);
                        arrayList.add(POIdata);
                    }
                }       
            }
            /**
             * Sort arraylist by the POI name alphabetically (Each POI object's name attribute)
             * Put it into a new arraylist that will be returned
             */
            return arrayList;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method that sorts an arraylist of POIs by their name alphabetically
     * @param arrayList
     * @return
     */
    private static ArrayList<PointOfInterest> sortPOIArray(ArrayList<PointOfInterest> arrayList) {
        ArrayList<PointOfInterest> sortedArrayList = new ArrayList<PointOfInterest>();
        ArrayList<String> nameArray = new ArrayList<String>();
        for (int i = 0; i < arrayList.size(); i++) {
            nameArray.add(arrayList.get(i).getName());
        }
        Collections.sort(nameArray);
        for (int i = 0; i < nameArray.size(); i++) {
            for (int j = 0; j < arrayList.size(); j++) {
                if (nameArray.get(i).equals(arrayList.get(j).getName())) {
                    sortedArrayList.add(arrayList.get(j));
                }
            }
        }
        return sortedArrayList;
    }

/**
     * Method that sorts an arraylist of POIs by their name alphabetically
     * @param arrayList
     * @return
     */
    private static ArrayList<BuildingPointOfInterest> sortBuildingPOIArray(ArrayList<BuildingPointOfInterest> arrayList) {
        ArrayList<BuildingPointOfInterest> sortedArrayList = new ArrayList<BuildingPointOfInterest>();
        ArrayList<String> nameArray = new ArrayList<String>();
        for (int i = 0; i < arrayList.size(); i++) {
            nameArray.add(arrayList.get(i).getName());
        }
        Collections.sort(nameArray);
        for (int i = 0; i < nameArray.size(); i++) {
            for (int j = 0; j < arrayList.size(); j++) {
                if (nameArray.get(i).equals(arrayList.get(j).getName())) {
                    sortedArrayList.add(arrayList.get(j));
                }
            }
        }
        return sortedArrayList;
    }


    /**
     * method to update isVisble state in POI JSON file and its objects
     * @param nameofPOI
     */
    public void updateisVisble(String nameofPOI){
        FileReader reader = null;
        /*
         * attempts to read file 
         */
        try {
            reader = new FileReader("data/PointOfInterests/PointOfInterestMetadata.json");
            JsonArray POIDataArray= JsonParser.parseReader(reader).getAsJsonArray();
            /*
             * loops through json file to find user made POIs
             */ 
            for (JsonElement POI : POIDataArray) {
                JsonObject poiObject = POI.getAsJsonObject();
                if (poiObject.get("name").getAsString() == nameofPOI){
                    if(poiObject.get("isVisible").getAsBoolean() == false){
                        /*
                         * changes isVisible property to false if true
                         */
                        poiObject.addProperty("isVisible", false);
                        break;
                    }
                        /*
                         * changes isVisible property to true if false
                         */
                    else{
                        poiObject.addProperty("isVisible", true);
                    }
                }     

            }

        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
    