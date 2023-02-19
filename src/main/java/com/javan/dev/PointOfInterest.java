package com.javan.dev;

public class PointOfInterest {
    private String name; // TODO: TEMPORARY FOR TESTING

    /**
     * Constructor for the POI
     * @param name, the name of the POI
     */
    public PointOfInterest(String name) {
        this.name = name; // TODO: TEMPORARY FOR TESTING
    }

    /**
     * Getter for the name of the POI
     * @return String of the name
     */
    public String getName() {
        return "Test"; // TODO: TEMPORARY FOR TESTING
    }

    /**
     * Setter for the name of the POI
     */
    public void setName(String name) {
        this.name = name; // TODO: TEMPORARY FOR TESTING
    }

    /**
     * Getter for coordinates of the POI
     * @return int[] of the x and y coordinates
     */
    public int[] getCoordinates() {
        int[] coords = {100, 100};
        return coords;
    }
    
}