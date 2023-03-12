package com.javan.dev;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.google.gson.*;
import com.google.gson.JsonIOException;

/**
 * @author: Riley Emma Gavigan <rgavigan@uwo.ca>, Dylan Sta Ana <dstaana@uwo.ca>, Brad McGlynn <bmcglyn4@uwo.ca>, Deep Ashishkumar Shah <dshah228@uwo.ca>
 * @version: 1.1
 * @since: 1.0
 */
public final class DataProcessor {
    /**
     * Private variable to hold the DataProcessor singleton instance
     */
    private static DataProcessor INSTANCE;
    private String mapJsonFilePath = "data/images/maps/metadata/mapMetadata.json";

    /**
     * Getter for the DataProcessor singleton instance
     */
    public static DataProcessor getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataProcessor();
        }
        return INSTANCE;
    }


    /**
     * Function to store user info as a JSON object using Gson
     * @param user, the user object to be stored as a JSON string
     * @return None
     * @throws IOException
     * @throws JsonIOException
     */
    public void storeUser(User user) throws JsonIOException, IOException {
        Gson gson = new Gson();
        /**
         * Writes the user object to JSON file in the users folder under data
         */
        FileWriter writer = new FileWriter("data/users/userMetadata.json");
        gson.toJson(user, writer);
        writer.flush();
        writer.close();
    }

    /**
     * Function to load user info from a JSON object using Gson
     * @param String json, the json string to be loaded and stored as a user object
     * @return the User object that was loaded from the JSON string
     * @throws FileNotFoundException
     */
    public User loadUser(String name) throws FileNotFoundException {
        Gson gson = new Gson();
        FileReader reader = new FileReader("group1/data/users" + "/" + name + ".json");
        User user = gson.fromJson(reader, User.class);
        return user;
    }

    /**
     * Function to load map filePaths from our metadata JSON object using Gson
     * @param int buildingID, buildingID of floorMap or BuilindMap object
     * @param int mapID, mapID of associated object
     * @param String mapType, differentiates between BuildingMap and FloorMap
     * @return String filePath
     * @throws FileNotFoundException
     */
    public String loadMapFilePath(int buildingID, int mapID, String mapType) {
        if (mapType.equalsIgnoreCase("FLOOR")){
            return JsonReader.getFloorMapPathFromID(buildingID, mapID, this.mapJsonFilePath);
        }
        else if (mapType.equalsIgnoreCase("BUILDING")){
            return JsonReader.getBuildingMapPathFromID(buildingID, this.mapJsonFilePath);
        }
        else {
            return null;
        }
    }


    /**
     * Function to parse JSON string to get the current weather at Western University
     * @param String json, the json string to be parsed
     * @return the current weather at Western University (temperature and condition) as a String
     */
    public ArrayList<String> parseWeather(StringBuffer json) {
        /**
         * Initialize ArrayList to hold the two strings
         */
        ArrayList<String> weather = new ArrayList<String>();

        /**
         * If null just add null and return null arraylist to pass back to Weather
         */
        if (json.toString() == null) {
            weather.add(null);
            weather.add(null);
            weather.add(null);
            return weather;
        }
        /**
         * Create JSON object with org.json library
         */
        String data = json.toString();
        JSONObject obj = new JSONObject(data);

        /**
         * Get temperature and condition
         */
        JSONObject current = obj.getJSONObject("current");
        JSONObject condition = current.getJSONObject("condition");
        Number temp = current.getNumber("temp_c");

        /**
         * Add temperature (as a string -> number in celsius) to weather ArrayList.
         * Add condition (text) to weather ArrayList
         * Add condition (icon) link to weather ArrayList
         */
        weather.add(temp.toString());
        weather.add(condition.getString("text"));
        weather.add(condition.getString("icon"));

        return weather;
    }

    /**
     * @param int userID - the ID of the user
     * @return List of favourite POIs
     */
    public ArrayList<PointOfInterest> getFavouritePOIs(int userID) {
        return JsonReader.favouritesList(userID);
    }

    /**
     * Method to return the POIs created by a user 
     * @param int userID - the ID of the user
     * @return List of user-created POIs
     */
    public ArrayList<PointOfInterest> getUserPOIs(int userID) {
        return JsonReader.userPOIList(userID);
    }  

    public ArrayList<PointOfInterest> getUniversalPOIs(boolean isCampusMap, int userID) {
        CampusMap campusMap = CampusMap.getInstance(0);
        ArrayList<PointOfInterest> universalPOIs = new ArrayList<PointOfInterest>();

        /**
         * Campus Map Condition: Get all Campus Map POIs for Building Directory
         */
        if (isCampusMap == true) {
            universalPOIs.clear();
            /**
             * Go through campusMap object building Array and create POIs for each building
             */
            for (int i = 0; i < campusMap.getBuildingArray().size(); i++) {
                BuildingMap building = campusMap.getBuildingArray().get(i);
                PointOfInterest poi = new PointOfInterest(building.getMapName(), building.getMapID(), false, "BUILDING", 0, 0, 1, building.getMapID(), new ArrayList<Integer>(), "", 0, true);
                universalPOIs.add(poi);
            }
        }
        else {
            universalPOIs.clear();
            /**
             * Floor Map Condition: Get Floor POIs within the current floor map
             */
            universalPOIs = JsonReader.universalPOIs(userID);
        }

        return universalPOIs;
    }

    /**
     * @param poiID - the ID of the POI
     * @return coords, int[] of x and y coordinates
     */
    public int[] getPOIPosition(int poiID) {
        PointOfInterest poi = getPOI(poiID);
        return poi.getCoordinates();
    }

    
    /**
     * Method to add a new poi created to the PointOfInterestMetadata.json file array
     * @param poi PointOfInterest object
     */
    public boolean addPointOfInterestToJsonFile(PointOfInterest POI) throws IOException {
        
        String jsonString = new String(Files.readAllBytes(Paths.get("data/PointOfInterests/PointOfInterestMetadata.json")));
        JSONArray jsonArray = new JSONArray(jsonString);
        
        JSONObject poiJson = POI.toJSON();
        for (Object poi : jsonArray) {
            JSONObject currentPoi = (JSONObject) poi;
            /*
             * Checks to see if the POI user + floorNumber already exists
             */
            if (currentPoi.get("userID") == poiJson.get("userID") 
            && currentPoi.get("roomNumber") == poiJson.get("roomNumber") && currentPoi.get("floorID") == poiJson.get("floorID")) {
                return false;
            }
        }
        jsonArray.put(poiJson);
        
        FileWriter fileWriter = new FileWriter("data/PointOfInterests/PointOfInterestMetadata.json");
        fileWriter.write(jsonArray.toString());
        fileWriter.flush();
        fileWriter.close();
        return true;
    }

    /**
     * Method to edit the point of interest data for a POI given its ID in the PointOfInterestMetadata.json file
     * @param poi
     * @return
     */
    public boolean editPointOfInterestInJsonFile(PointOfInterest poi) throws IOException {
        String jsonString = new String(JsonReader.read("data/PointOfInterests/PointOfInterestMetadata.json"));
        JSONArray jsonArray = new JSONArray(jsonString);
        
        JSONObject poiJson = poi.toJSON();
        int counter = 0;
        for (Object poiObject : jsonArray) {
            JSONObject currentPoi = (JSONObject) poiObject;

            /**
             * Check to see if the POI ID matches a POI
             */
            if (currentPoi.get("ID") == poiJson.get("ID")) {
                /**
                 * Remove the current POI from the JSON Array
                 */
                jsonArray.remove(counter);

                /**
                 * Add the new POI to the JSON Array
                 */
                jsonArray.put(poiJson);
                FileWriter fileWriter = new FileWriter("data/PointOfInterests/PointOfInterestMetadata.json");
                fileWriter.write(jsonArray.toString());
                fileWriter.flush();
                fileWriter.close();
                return true;
            }
            counter += 1;
        }
        return false;
    }

    /**
     * Method to delete a point of interest from the PointOfInterestMetadata.json file
     * @param poi
     * @return
     * @throws IOException
     */
    public boolean deletePointOfInterestFromJsonFile(PointOfInterest poi) throws IOException {
        String jsonString = new String(JsonReader.read("data/PointOfInterests/PointOfInterestMetadata.json"));
        JSONArray jsonArray = new JSONArray(jsonString);
        
        JSONObject poiJson = poi.toJSON();
        boolean isDeleted = false;
        JSONArray newJsonArray = new JSONArray();

        for (Object poiObject : jsonArray) {
            JSONObject currentPoi = (JSONObject) poiObject;

            /**
             * Check to see if the POI ID matches a POI
             */
            if (currentPoi.get("ID") == poiJson.get("ID")) {
                isDeleted = true;
            }
            else if (isDeleted == true) {
                /**
                 * Change the POI ID of every POI following to ID - 1 and update jsonArray
                 */
                currentPoi.put("ID", currentPoi.getInt("ID") - 1);
                newJsonArray.put(currentPoi);
            }
            else {
                /**
                 * Add the POI to the new JSON Array
                 */
                newJsonArray.put(currentPoi);
            }
        }
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter("data/PointOfInterests/PointOfInterestMetadata.json");
            fileWriter.write(newJsonArray.toString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isDeleted;
    }


    /**
     * @param currentMapID
     * @return boolean indicating if there is a floor above or not
     */
    public boolean checkFloorAbove(int currentMapID, int currentBuildingID) {
        try {
            /**
             * Read the JSON file
             */
            JSONArray metadataArray = new JSONArray(new String(JsonReader.read(mapJsonFilePath)));
            /**
             * Loop through the metadata array
             */
            for (int i = 0; i < metadataArray.length(); i++) {
                JSONObject metadata = metadataArray.getJSONObject(i);
                if (metadata.getInt("buildingID") == currentBuildingID) {
                    JSONArray floorMaps = metadata.getJSONArray("floorMaps");
                    for (int j = 0; j < floorMaps.length(); j++) {
                        JSONObject floorMap = floorMaps.getJSONObject(j);
                        if (floorMap.getInt("mapID") == currentMapID + 1) {
                            return true;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Method that checks if there is a floor below the current one.
     * @param int currentMapID
     * @param int currentBuildingID
     * @return boolean indicating if there is a floor below 
     */
    
    public boolean checkFloorBelow(int currentMapID, int currentBuildingID) {
    try {
        /**
         * Read the JSON file
         */
        JSONArray metadataArray = new JSONArray(new String(JsonReader.read(mapJsonFilePath)));

        /**
         * Loop through the metadata array
         */
        for (int i = 0; i < metadataArray.length(); i++) {
            JSONObject metadata = metadataArray.getJSONObject(i);
            if (metadata.getInt("buildingID") == currentBuildingID) {
                JSONArray floorMaps = metadata.getJSONArray("floorMaps");
                for (int j = 0; j < floorMaps.length(); j++) {
                    JSONObject floorMap = floorMaps.getJSONObject(j);
                    if (floorMap.getInt("mapID") == currentMapID - 1) {
                        return true;
                    }
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
    }

    /**
     * @param int currentMapID
     * @param int currentBuildingID
     * @return Map object of the floor above
     * @throws IOException If there is an error reading the map metadata file.
     */
    public FloorMap getFloorAbove(int currentMapID, int currentBuildingID) throws IOException {
        ArrayList<FloorMap> floorMaps = JsonReader.getFloorMaps(mapJsonFilePath);
        for (int i = 0; i < floorMaps.size(); i++) {
            FloorMap floor = floorMaps.get(i);
            if (floor.getBuildingID() == currentBuildingID && floor.getMapID() == currentMapID + 1) {
                return new FloorMap(currentBuildingID, currentMapID + 1);
            }
        }
        return null;
    }

    /**
     * @param currentMapID
     * @return Map object of the floor below
     * @throws IOException If there is an error reading the map metadata file.
     */
    public FloorMap getFloorBelow(int currentMapID, int currentBuildingID) throws IOException {
        ArrayList<FloorMap> floorMaps = JsonReader.getFloorMaps(mapJsonFilePath);
        for (int i = 0; i < floorMaps.size(); i++) {
            FloorMap floor = floorMaps.get(i);
            if (floor.getBuildingID() == currentBuildingID && floor.getMapID() == currentMapID - 1) {
                return new FloorMap(currentBuildingID, currentMapID - 1);
            }
        }
        return null;
    }


    public int makeNewPOIID() {
        FileReader reader = null;

        /*
         * attempts to read file 
         */
        try {
            reader = new FileReader("data/PointOfInterests/PointOfInterestMetadata.json");
            JsonArray POIDataArray= JsonParser.parseReader(reader).getAsJsonArray();
            return POIDataArray.size() + 1;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public PointOfInterest getPOI(int poiID) {
        FileReader reader = null;

        /*
         * attempts to read file 
         */
        try {

            reader = new FileReader("data/PointOfInterests/PointOfInterestMetadata.json");
            JsonArray POIDataArray= JsonParser.parseReader(reader).getAsJsonArray();

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
                if (poiObject.get("ID").getAsInt() == poiID){
                    /*
                    * declares all data from json file
                    * then creates a POI object
                    * which is then added to arraylist of type POI
                    */
                    String name  = poiObject.get("name").getAsString();
                    int userID  = poiObject.get("userID").getAsInt();
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
                        /**
                         * Get current value from the userFavouritesArray and store as int, then add to array
                         */
                        int userFavourite = userFavouritesData.get(i);
                        userFavouritesList.add(userFavourite);
                    }

                    String description = poiObject.get("description").getAsString();
                    int roomNumber = poiObject.get("roomNumber").getAsInt();
                    boolean isVisible = poiObject.get("isVisible").getAsBoolean();
                    PointOfInterest POIdata = new PointOfInterest(name, userID, isUserMade, POI_Type, coordinateArray[0], coordinateArray[1], floorID, buildingID, userFavouritesList, description, roomNumber, isVisible);
                    POIdata.setID(poiID);
                    return POIdata;
                }       
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Method that encrypts user password using the Caesar Cipher algorithm with a key of 6
     * @param String userPassword
     * @return String encrypted, the encrypted userPassword
     */
    public static String encrypt(String userPassword) {
        int key = 6;
        String encrypted = "";
        for(int i = 0; i < userPassword.length(); i++) {
        char c = userPassword.charAt(i);
        if(Character.isLetter(c)) {
            if(Character.isUpperCase(c)) {
            encrypted += (char)('A' + (c + key - 'A') % 26);
            } else {
            encrypted += (char)('a' + (c + key - 'a') % 26);
            }
        } else {
            encrypted += c;
        }
        }
        return encrypted;
    }
    
    /**
     * Method that decypts stored user password using the Caesar Cipher algorithm with a key of 6
     * @param String encrypted, the stored encrypted password
     * @return String decrypted, the decrypted userPassword
     */
    public static String decrypt(String encrypted) {
        int key = 6;
        String decrypted = "";
        for(int i = 0; i < encrypted.length(); i++) {
        char c = encrypted.charAt(i);
        if(Character.isLetter(c)) {
            if(Character.isUpperCase(c)) {
            decrypted += (char)('A' + (c - key - 'A' + 26) % 26);
            } else {
            decrypted += (char)('a' + (c - key - 'a' + 26) % 26);
            }
        } else {
            decrypted += c;
        }
        }
        return decrypted;
    }

    /**
     * Method authenticates the login attempt made by the user, checking if the account exists
     * @param String username
     * @param String password, unencrypted password attempt
     * @return boolean
     */
    public Integer authenticateLogin(String username, String password) {
        /**
         * JSON file location
         */
        String filePath = "data/users/usersMetadata.json";

        try {
            /** 
             * Read the JSON file
             */
            FileReader fileReader = new FileReader(filePath);
            JSONTokener jsonTokener = new JSONTokener(fileReader);
            JSONArray jsonArray = new JSONArray(jsonTokener);

            /** 
             * Check each user in the JSON array
             */
            for (Iterator<Object> iterator = jsonArray.iterator(); iterator.hasNext();) {
                JSONObject user = (JSONObject) iterator.next();

                /** 
                 * Decrypt the password from the JSON file
                 */
                String encryptedPassword = user.getString("encryptedPassword");
                String decryptedPassword = decrypt(encryptedPassword);

                /** 
                 * Check if the username and decrypted password match
                 */
                if (username.equals(user.getString("username")) && password.equals(decryptedPassword)) {
                    String userType = user.getString("userType");
                    return (Integer) user.get("userID");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        /**
         * If no match was found, return false
         */
        return -1;
    }

    /**
     * Method to get a user's password from their username and return a string of the password
     * @param String username
     * @return String password
     */
    public String getPasswordFromUsername(String username) {
        /**
         * JSON file location
         */
        String filePath = "data/users/usersMetadata.json";

        try {
            /** 
             * Read the JSON file
             */
            FileReader fileReader = new FileReader(filePath);
            JSONTokener jsonTokener = new JSONTokener(fileReader);
            JSONArray jsonArray = new JSONArray(jsonTokener);
            
            /** 
             * Check each user in the JSON array
             */
            for (Iterator<Object> iterator = jsonArray.iterator(); iterator.hasNext();) {
                JSONObject user = (JSONObject) iterator.next();

                /** 
                 * Check if the usernames match
                 */
                if (username.equals(user.getString("username"))) {
                    String encryptedPassword = user.getString("encryptedPassword");
                    String decryptedPassword = decrypt(encryptedPassword);
                    return decryptedPassword;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method to create a new user account provided that a user with the same username and password doesn't exist already
     * @param String username
     * @param String password, unencrypted password attempt
     * @return boolean
     */
    public boolean createAccount(String username, String password) {
        /** 
         * JSON file location
         */
        String filePath = "data/users/usersMetadata.json";

        try {
            /** 
             * Read the JSON file
             */
            FileReader fileReader = new FileReader(filePath);
            JSONTokener jsonTokener = new JSONTokener(fileReader);
            JSONArray jsonArray = new JSONArray(jsonTokener);
            
            /** 
             * Check if user account exists already
             */
            for (Iterator<Object> iterator = jsonArray.iterator(); iterator.hasNext();) {
                JSONObject user = (JSONObject) iterator.next();

                /** 
                 * Check if the usernames match
                 */
                if (username.equals(user.getString("username"))) {                  System.out.println("Error: account already exists");
                    return false;
                }
            }
            /** 
             * Find the next available userID
             */
            int nextUserID = 1;
            for (Iterator<Object> iterator = jsonArray.iterator(); iterator.hasNext();) {
                JSONObject user = (JSONObject) iterator.next();
                int userID = user.getInt("userID");
                if (userID >= nextUserID) {
                    nextUserID = userID + 1;
                }
            }
            /** 
             * Encrypt the password
             */
            String encryptedPassword = encrypt(password);
            /** 
             * Create a new user object
             */
            JSONObject newUser = new JSONObject();
            newUser.put("userType", "USER");
            newUser.put("userID", nextUserID);
            newUser.put("username", username);
            newUser.put("encryptedPassword", encryptedPassword);
            /** 
             * Add the new user object to the JSON array
            */
            jsonArray.put(newUser);
            /** 
             * Write the updated JSON array to the file
             */
            FileWriter fileWriter = new FileWriter(filePath);
            jsonArray.write(fileWriter);
            fileWriter.flush();
            fileWriter.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } 
    }

    /**
     * Method to get a FloorMap object from MapID and Building ID
     * @param mapID integer and buildingID integer
     * @return Map object retrieved from ID
     */
    public FloorMap getFloorMapFromMapID(int buildingID, int mapID) {
        FloorMap mapObject = new FloorMap(buildingID, mapID);
        return mapObject;
    }
}