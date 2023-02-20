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

    public boolean checkfloorAbove(int currentMapID);

    public boolean checkfloorBelow(int currentMapID);

    public Map getfloorAbove(int currentMapID);

    public Map getfloorBelow(int currentMapID);
}