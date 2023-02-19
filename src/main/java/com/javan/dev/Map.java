package com.javan.dev;

public interface Map {

    /**
     * Getter for file path
     * @return file path string
     */
    String getFilePath();

    /**
     * Getter for map id
     * @return map id int
     */
    int getMapID();

    boolean checkfloorAbove(int currentMapID);

    boolean checkfloorBelow(int currentMapID);

    Map getfloorAbove(int currentMapID);

    Map getfloorBelow(int currentMapID);
    
}