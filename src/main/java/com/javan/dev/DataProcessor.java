package com.javan.dev;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;

import org.json.JSONObject;
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
        Writer writer = new FileWriter("group1\\data\\users" + "\\" + user.getUsername() + ".json");
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
        Reader reader = new FileReader("group1\\data\\users" + "\\" + name + ".json");
        User user = gson.fromJson(reader, User.class);
        return user;
    }

    /**
     * Function to parse JSON string to get the current weather at Western University
     * @param String json, the json string to be parsed
     * @return the current weather at Western University (temperature and condition) as a String
     */
    public ArrayList<String> parseWeather(StringBuffer json) {
        /**
         * Create JSON object with org.json library
         */
        String data = json.toString();
        JSONObject obj = new JSONObject(data);
        /**
         * Initialize ArrayList to hold the two strings
         */
        ArrayList<String> weather = new ArrayList<String>();

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
     * @return List of favourite POIs
     */
    public static ArrayList<PointOfInterest> getFavouritePOIs() {
        ArrayList<PointOfInterest> favouritePOIs = new ArrayList<PointOfInterest>();

        // TODO: THIS IS TEMPORARY FOR TESTING
        for (int i = 0; i < 10; i++) {
            favouritePOIs.add(new PointOfInterest("Favourite Test"));
        }
        return favouritePOIs;
    }

    /**
     * Method to return the POIs created by a user // TODO: TEMPORARY FOR RESTING
     * @return List of user-created POIs
     */
    public static ArrayList<PointOfInterest> getUserPOIs() {
        ArrayList<PointOfInterest> userPOIs = new ArrayList<PointOfInterest>();

        // TODO: THIS IS TEMPORARY FOR TESTING
        for (int i = 0; i < 10; i++) {
            userPOIs.add(new PointOfInterest("User Test"));
        }
        return userPOIs;
    }  

    public ArrayList<PointOfInterest> getUniversalPOIs() {
        ArrayList<PointOfInterest> universalPOIs = new ArrayList<PointOfInterest>();

        // TODO: THIS IS TEMPORARY FOR TESTING
        for (int i = 0; i < 40; i++) {
            universalPOIs.add(new PointOfInterest("Universal Test"));
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
     * TODO: Method that checks if there is a floor above the current one.
     * @param currentMapID
     * @return boolean indicating if there is a floor above or not
     */
    public boolean checkfloorAbove(int currentMapID) {
        return false;
    }

    /**
     * TODO: Method that checks if there is a floor below the current one.
     * @param currentMapID
     * @return boolean indicating if there is a floor below or not
     */
    public boolean checkfloorBelow(int currentMapID) {
        return false;
    }

    /**
     * TODO: Method that gets the Map of the floor above the current one
     * @param currentMapID
     * @return Map object of the floor above
     */
    public Map getfloorAbove(int currentMapID) {
        return null;
    }

    /**
     * TODO: Method that gets the Map of the floor below the current one
     * @param currentMapID
     * @return Map object of the floor below
     */
    public Map getfloorBelow(int currentMapID) {
        return null;
    }


    public PointOfInterest getPOI(int parseInt) {
        return new PointOfInterest("Test");
    }
}
