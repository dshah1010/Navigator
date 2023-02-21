package com.javan.dev;

import java.io.FileReader;
import com.google.gson.*;
import java.util.Iterator;

public class JsonReader {

    public static String getMapPathFromID(int mapID, String jsonFilePath) {
        FileReader reader = null;
        Gson g = new Gson();
        try {
            reader = new FileReader(jsonFilePath);
            JsonElement root = JsonParser.parseReader(reader);
            JsonArray floorMaps = root.getAsJsonObject().get("floorMaps").getAsJsonArray();
            String mapPath;

            Iterator<JsonElement> iterator = floorMaps.iterator();
            while (iterator.hasNext()) {
                JsonElement map = iterator.next();
                mapPath = g.fromJson("filePath", String);
                mapPath = map.get(0).getAsJsonObject().get("filePath").getAsString();
                int id = map.get("mapID").intValue();
                if (id == mapID) {
                    return mapPath;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
    
