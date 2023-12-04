package com.edgrantJBusRD;

import com.edgrantJBusRD.dbjson.Serializable;
/**
 * Represents a bus station with a name, city, and address.
 */
public class Station extends Serializable {
    /**
     * The city where the station is located.
     */
    public City city;

    /**
     * The address of the station.
     */
    public String address;

    /**
     * The name of the station.
     */
    public String stationName;

    /**
     * Constructs a Station object with a station name, city, and address.
     *
     * @param stationName The name of the station.
     * @param city        The city where the station is located.
     * @param address     The address of the station.
     */
    public Station(String stationName, City city, String address) {
        this.stationName = stationName;
        this.city = city;
        this.address = address;
    }

    /**
     * Returns a string representation of the station, including its ID, name, city, and address.
     *
     * @return A string representation of the station.
     */
    public String toString() {
        return (super.id + " " + this.stationName + " " + this.city + " " + this.address);
    }

    /**
     * Returns a string representation of the station, including its ID, name, city, and address.
     *
     * @return A string representation of the station.
     */
    public String print() {
        return (super.id + " " + this.stationName + " " + this.city + " " + this.address);
    }
}
