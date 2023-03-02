package com.javan.dev;

import java.io.FileReader;
import com.google.gson.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author: Brad McGlynn <bmcglyn4@uwo.ca>
 * @version: 1.0
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
}
    
