package com.javan.dev;

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

    public void updateMap(int mapID);
}