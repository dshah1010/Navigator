package com.javan.dev;

import java.io.FileReader;
import com.google.gson.*;

/**
 * @author: Brad McGlynn <bmcglyn4@uwo.ca>
 * @version: 1.0
 * @since: 1.0
 */
public class JsonReader {

    public static String getFloorMapPathFromID(int mapID, String jsonFilePath) {
        FileReader reader = null;
        try {
            reader = new FileReader(jsonFilePath);
            JsonArray buildingMaps = JsonParser.parseReader(reader).getAsJsonArray();
            String mapPath;
            JsonArray floorMaps;
            int counter = 0;
            int floorCounter = 0;

            while (counter < buildingMaps.size() - 1) {
                floorMaps = buildingMaps.get(counter).getAsJsonObject().get("floorMaps").getAsJsonArray();
                while (floorCounter < floorMaps.size() - 1) {
                    mapPath = floorMaps.get(floorCounter).getAsJsonObject().get("filePath").getAsString();
                    int id = floorMaps.get(floorCounter).getAsJsonObject().get("mapID").getAsInt();
                    if (id == mapID) {
                        return mapPath;
                    }
                    floorCounter++;
                }
                counter++;
                floorCounter = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getBuildingMapPathFromID(int mapID, String jsonFilePath) {
        FileReader reader = null;
        try {
            reader = new FileReader(jsonFilePath);
            JsonArray buildingMaps = JsonParser.parseReader(reader).getAsJsonArray();
            String mapPath;
            int counter = 0;

            while (counter < buildingMaps.size() - 1) {
                mapPath = buildingMaps.get(counter).getAsJsonObject().get("filePath").getAsString();
                int id = buildingMaps.get(counter).getAsJsonObject().get("buildingID").getAsInt();
                if (id == mapID) {
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
    
