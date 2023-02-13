package com.javan.dev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * @author: Riley Emma Gavigan <rgavigan@uwo.ca>
 * @version: 1.0
 * @since: 1.0
 */
public class Weather {
    /**
     * API Key for Weather API [Too lazy to hide it + we won't be using it regularly so won't use a .gitignore]
     */
    String API_key = "04decf0c05e44c028f211659231302";
    /**
     * URL for Weather API GET request for our purposes (simple current weather request)
     */
    String GET_URL = "http://api.weatherapi.com/v1/current.json?key=" + API_key + "&q=N6A3K7";
    /**
     * String to hold JSON object retrieved from constructor
     */
    StringBuffer json = new StringBuffer();
    /**
     * Variables to hold weather data
     */
    String temp_c;
    String condition;
    String conditionIcon;

    /**
     * Constructor for Weather object
     * @throws IOException
     * @throws MalformedURLException
     */
    public Weather() throws IOException, MalformedURLException {
        /**
         * Turn GET request URL into a URL object
         */
        URL url = new URL(GET_URL);

        /**
         * Open HTTP connection and set to GET request
         */
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        /**
         * Check if the connection was successful. If it wasnt, a RuntimeException will be thrown.
         * This will ensure that when a user is offline, undefined behaviour does not occur.
         */
        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("Weather API Could Not Be Accessed");
        }

        /**
         * Open a BufferedReader for reading API response for the current weather at Western University
         */
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String input;
        while ((input = reader.readLine()) != null) {
            json.append(input);
        }

        /**
         * Close the connection to the API [Data has been received]
         */
        reader.close();
        connection.disconnect();
    }

    /**
     * Getter for the JSON object retrieved from the constructor
     * @return String json, the unprocessed JSON object
     */
    public StringBuffer getJSON() {
        return this.json;
    }

    /**
     * Function to use DataProcessor to parse the weather JSON object
     */
    public void parseWeather() {
        DataProcessor processor = new DataProcessor();
        /**
         * Get the parsed data and store it in the current Weather objet
         */
        ArrayList<String> parsedData = processor.parseWeather(this.json);
        this.temp_c = parsedData.get(0);
        this.condition = parsedData.get(1);
        this.conditionIcon = parsedData.get(2);
    }

    /**
     * Getter for the temperature in celsius
     * @return String temp_c, the temperature in celsius
     */
    public String getTempC() {
        return this.temp_c;
    }

    /**
     * Getter for the textual repreesntation of current weather condition
     * @return String condition
    */
    public String getCondition() {
        return this.condition;
    }

    /**
     * Getter for the link to the icon representing the current weather condition
     * @return String conditionIcon, link to icon
     */
    public String getConditionIcon() {
        return this.conditionIcon;
    }
}