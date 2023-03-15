package com.javan.dev;

import org.json.JSONObject;
import org.json.JSONArray;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author: Bradley McGlynn <bmcglyn4@uwo.ca>
 * @version: 1.0
 * @since: 1.0
 */

public class MapDirectoryReader {
    /**
     * Function to create map metadata based on images in directory
     * @param String directoryPath, directory path of the directory of directories containing map pngs
     * @param String jsonFilePath, the target path for the json file of metadata to be created at
     * @return None
     */
    public static void addMapInfoToJSON(String directoryPath, String jsonFilePath) {
        /**
         * Create a JSON array to hold the maps
         */
        JSONArray buildingsJson = new JSONArray();
    
        /**
         * Get a list of sub-directories in the specified directory
         */
        File directory = new File(directoryPath);
        File[] subdirectories = directory.listFiles();

        /**
         * Sort the subdirectories alphabetically
         */
        java.util.Arrays.sort(subdirectories);

        int mapID = 1;
        int buildingId = 1;
        /**
         * Loop through each subdirectory inside floorPlans (each Building's directory of maps)
         */
        for (File subdirectory : subdirectories) {
            /**
             * Set floor ID to 1 for each building
             */
            mapID = 1;

            /**
             * Create a new JSON object for this building
             */
            JSONObject buildingJson = new JSONObject();

            /**
             * Get the building name subdirectory
             */
            String buildingName = subdirectory.getName();
    
            /**
             * Set the building name, type and ID in the JSON object
             */
            buildingJson.put("mapName", buildingName);
            buildingJson.put("mapType", "BUILDING");
            buildingJson.put("buildingID", buildingId);
    
            /**
             * Get a list of all PNG files in this subdirectory (these are the FloorMap imaages)
             */
            File[] imageFiles = subdirectory.listFiles();
    
            /**
             * Create a JSON array to hold the floor maps for the building
             */
            JSONArray mapsJson = new JSONArray();

            /**
             * Loop through each image file and add it to the JSON array
             */
            for (File imageFile : imageFiles) {
                /**
                 * Create a new JSON object for this floor
                 */
                JSONObject mapJson = new JSONObject();

                /**
                 * Get the map name from the subdirectory 
                 */
                String mapName = imageFile.getName();
        
                /**
                 * Set the map name, map type and ID in the JSON object
                 */
                mapJson.put("mapName", mapName);
                mapJson.put("mapType", "FLOOR");
                mapJson.put("mapID", mapID);
                
                /**
                 * Loop through each image file and add it to the JSON array
                 */
                String imagePath = imageFile.getPath();
                mapJson.put("filePath", imagePath);
                mapsJson.put(mapJson);
                
                /**
                 * Increment mapID by 1 for next floor within the building
                 */
                mapID++;
            }
    
            /**
             * Set the floor maps in the JSON object
             */
            buildingJson.put("floorMaps", mapsJson);

            /**
             * Set the building path in the JSON object
             */
            String buildingPath = subdirectory.getPath();
            buildingJson.put("filePath", buildingPath);
    
            /**
             * Add the map JSON object to the buildings JSON array
             */
            buildingsJson.put(buildingJson);

            /**
             * Increment the building ID for next building
             */
            buildingId++;
        }

        /**
         * Write the maps to the JSON file
         */
        FileWriter file = null;
        try {
            file = new FileWriter(jsonFilePath);
            file.write(buildingsJson.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * Function to create map object metadata
     * @param String jsonFilePath, the target path for the json file of metadata to be created at
     * @param Map map, map object to create metadata for
     * @return None
     */
    public static void addMapObjectsToJSON(String jsonFilePath, Map map) {
        try {
            /**
             * Get the building ID from the BuildingMap
             */
            int mapID = map.getMapID();

            /**
             * Create a new JSONObject and add the building ID and BuildingMap to it
             */
            JSONObject mapData = new JSONObject();

            if (map.getMapType().equals("BUILDING")) {
                mapData.put("buildingID", mapID);
                mapData.put("buildingMap", map.toJSON());
            }
            else if (map.getMapType().equals("FLOOR")) {
                mapData.put("floorID", mapID);
                mapData.put("floorMap", map.toJSON());
            }
            
            FileWriter writer = new FileWriter(jsonFilePath);
            writer.write(mapData.toString());
            writer.close();

            System.out.println("Building data added to JSON file.");
        } catch (IOException e) {
            System.err.println("Error adding building data to JSON file: " + e.getMessage());
        }
    }
}
