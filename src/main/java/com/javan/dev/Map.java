package com.javan.dev;

import org.json.JSONObject;

/**
 * @author: Brad McGlynn <bmcglyn4@uwo.ca>
 * @version: 1.0
 * @since: 1.0
 */
public interface Map {

    /**
     * Getter for file path
     * @return file path string
     */
    public String getFilePath();

    /**
     * Getter for map id
     * @return map id int
     */
    public int getMapID();

    /**
     * (WIP) Updates map
     * @return None
     */
    public void updateMap(int mapID);

    /**
     * Getter for map type
     * @return String map type
     */
    public String getMapType();

    public JSONObject toJSON();
}