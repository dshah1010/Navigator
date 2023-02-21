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

    public static void addMapsToJSON(String directoryPath, String jsonFilePath) {
        // Create a JSON array to hold the maps
        JSONArray buildingsJson = new JSONArray();
    
        // Get a list of subdirectories in the specified directory
        File directory = new File(directoryPath);
        File[] subdirectories = directory.listFiles();

        int mapID = 2;
        int buildingId = 1;
        // Loop through each subdirectory
        for (File subdirectory : subdirectories) {
            // Create a new JSON object for this building
            JSONObject buildingJson = new JSONObject();

            // Get the building name subdirectory
            String buildingName = subdirectory.getName();
    
            // Set the building name, type and ID in the JSON object
            buildingJson.put("mapName", buildingName);
            buildingJson.put("mapType", "BUILDING");
            buildingJson.put("buildingId", buildingId);
    
            // Get a list of all PNG files in this subdirectory
            File[] imageFiles = subdirectory.listFiles();
    
            // Create a JSON array to hold the floor maps
            JSONArray mapsJson = new JSONArray();

            // Loop through each image file and add it to the JSON array
            for (File imageFile : imageFiles) {
                // Create a new JSON object for this floor
                JSONObject mapJson = new JSONObject();

                // Get the map name from the subdirectory 
                String mapName = imageFile.getName();
        
                // Set the map name, map type and ID in the JSON object
                mapJson.put("mapName", mapName);
                mapJson.put("mapType", "FLOOR");
                mapJson.put("mapID", mapID);
                
                // Loop through each image file and add it to the JSON array
                String imagePath = imageFile.getAbsolutePath();
                mapJson.put("filePath", imagePath);

                mapsJson.put(mapJson);
                mapID++;
            }
    
            // Set the floor maps in the JSON object
            buildingJson.put("floorMaps", mapsJson);

            // Set the building path in the JSON object
            String buildingPath = subdirectory.getAbsolutePath();
            buildingJson.put("filePath", buildingPath);
    
            // Add the map JSON object to the buildings JSON array
            buildingsJson.put(buildingJson);

            buildingId++;

            
        }
        // Write the maps to the JSON file
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
}
