package com.javan.dev;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;

/**
 * @author: Riley Emma Gavigan <rgavigan@uwo.ca>
 * @version: 1.0
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
     * Method to return the favourite POIs of a user // TODO: TEMPORARY FOR TESTING
     * @param int userID - the ID of the user
     * @return List of favourite POIs
     */
    public ArrayList<PointOfInterest> getFavouritePOIs(int userID) {
        ArrayList<PointOfInterest> favouritePOIs = new ArrayList<PointOfInterest>();

        // TODO: THIS IS TEMPORARY FOR TESTING
        for (int i = 0; i < 10; i++) {
            favouritePOIs.add(new PointOfInterest("Favourite Test"));
        }
        return favouritePOIs;
    }

    /**
     * Method to return the POIs created by a user // TODO: TEMPORARY FOR RESTING
     * @param int userID - the ID of the user
     * @return List of user-created POIs
     */
    public ArrayList<PointOfInterest> getUserPOIs(int userID) {
        ArrayList<PointOfInterest> userPOIs = new ArrayList<PointOfInterest>();

        // TODO: THIS IS TEMPORARY FOR TESTING
        for (int i = 0; i < 10; i++) {
            userPOIs.add(new PointOfInterest("User Test"));
        }
        return userPOIs;
    }  

    public ArrayList<PointOfInterest> getUniversalPOIs(boolean isCampusMap) {
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
                PointOfInterest poi = new PointOfInterest(building.getMapName());
                /**
                 * Set building ID and floor ID to 1
                 */
                poi.setBuildingID(building.getMapID());
                poi.setFloorID(1);
                universalPOIs.add(poi);
            }
        }
        else {
            universalPOIs.clear();
            /**
             * Floor Map Condition: Get all POIs within the current floor map
             */
            for (int i = 0; i < 10; i++) {
                universalPOIs.add(new PointOfInterest("Universal Test"));
            }
        }

        return universalPOIs;
    }

    /**
     * TODO: Method to get the POI's x and y coordinates when given the POI ID
     * @param poiID - the ID of the POI
     * @return coords, int[] of x and y coordinates
     */
    public int[] getPOIPosition(int poiID) {
        int x = (int) (Math.random() * 1000);
        int y = (int) (Math.random() * 1000);
        int[] coords = {x, y};
        return coords;
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
     * TODO: Method that gets the Map of the floor below the current one
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


    public PointOfInterest getPOI(int parseInt) {
        return new PointOfInterest("Test");
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
    public boolean authenticateLogin(String username, String password) {
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
                    System.out.println("User " + username + " logged in as " + userType + ".");
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        /**
         * If no match was found, return false
         */
        System.out.println("Invalid username or password.");
        return false;
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
                if (username.equals(user.getString("username"))) {
                    System.out.println("Error: account already exists");
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

            System.out.println("New account created for " + username + " with userID " + nextUserID + ".");
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
