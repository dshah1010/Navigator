package com.javan.dev;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.io.IOException;
import java.net.MalformedURLException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestWeather {
    Weather weather;

    /**
     * This method is called before each test method - sets up a new weather object
     * @throws IOException
     * @throws MalformedURLException
     */
    @BeforeEach
    void setUp() throws MalformedURLException, IOException {
        weather = new Weather();
    }

    /**
     * This method is called to check that the temperature is not null
     */
    @Test
    @DisplayName("Test that the temperature is not null")
    void testTemperature() {
        assertNotNull(weather.getTempC());
    }

    /**
     * This method is called to check that the condition is not null
     */
    @Test
    @DisplayName("Test that the condition is not null")
    void testCondition() {
        assertNotNull(weather.getCondition());
    }

    /**
     * This method is called to check that the condition icon is not null
     */
    @Test
    @DisplayName("Test that the condition icon is not null")
    void testConditionIcon() {
        assertNotNull(weather.getConditionIcon());
    }

    /**
     * This method is called to check that the API successfully ran and returned a JSON that is not null
     */
    @Test
    @DisplayName("Test that the WeatherAPI was successfully called")
    void testWeatherAPI() {
        assertNotNull(weather.getJSON());
    }
}