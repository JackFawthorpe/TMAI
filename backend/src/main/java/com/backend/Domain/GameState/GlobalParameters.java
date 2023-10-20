package com.backend.Domain.GameState;

/**
 * Container for metrics around the global parameters
 * - Water
 * - O2
 * - Temperature
 */
public class GlobalParameters {

    private int waterToPlace;
    private int temperature;
    private int oxygenLevel;

    public GlobalParameters() {
        waterToPlace = 9;
        temperature = -30;
        oxygenLevel = 0;
    }

    public int getWaterToPlace() {
        return waterToPlace;
    }

    public int getTemperature() {
        return temperature;
    }

    public int getOxygenLevel() {
        return oxygenLevel;
    }
}
