package com.javan.dev;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import com.google.gson.JsonIOException;

/**
 * @author: Riley Emma Gavigan <rgavigan@uwo.ca>
 * @version: 1.0
 * @since: 1.0
 */
public class App
{
    /**
     * Main function that runs the application.
     * <p>
     * Function that waits for user to interact with the application, delegating any tasks to the appropriate classes. 
     * This function will be called when the application is ran and will run until the application is closed or exited.
     * <p>
     * @param args, none of which will be necessary to run the application.
     * @return None
     * @throws IOException
     * @throws MalformedURLException
     */
    public static void main( String[] args ) throws MalformedURLException, IOException
    {
        // Test print statement
        System.out.println( "Hello World!" );

        // Create Weather object to get weather data from API -> this will remain for the session
        // Weather weather = new Weather();
        // weather.parseWeather();

        // Create UserInterface object to create the UI -> this will remain for the session
        UserInterface ui = new UserInterface();
    }
}
