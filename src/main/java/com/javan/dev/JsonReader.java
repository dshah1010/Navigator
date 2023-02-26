package com.javan.dev;

import java.io.FileReader;
import com.google.gson.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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
     */
    public static String read(String fileName) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(fileName)));
        return content;
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
    
