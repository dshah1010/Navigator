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
        /**
         * Get 2 random integers between 0 and 1000
         */
        int x = (int) (Math.random() * 2500);
        int y = (int) (Math.random() * 2500);

        int[] coords = {x, y};
        return coords;
    }

    /**
     * Getter for the ID of the POI
     * @return
     */
    public int getID() {
        return 12;
    }

    public String getDescription() {
        String test = "This is a test description";
        return test;
    }
    
}