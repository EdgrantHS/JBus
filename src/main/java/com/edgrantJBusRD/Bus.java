package com.edgrantJBusRD;

import com.edgrantJBusRD.dbjson.Serializable;

import java.util.*;
import java.sql.Timestamp;

/**
 * The Bus class represents a bus in the transportation system.
 * It stores information such as bus name, departure and arrival stations, price, facilities, capacity, and bus type.
 */
public class Bus extends Serializable {

    /**
     * The name of the bus.
     */
    public String name;

    /**
     * The departure station for the bus.
     */
    public Station departure;

    /**
     * The arrival station for the bus.
     */
    public Station arrival;

    /**
     * The price of the bus.
     */
    public Price price;

    /**
     * The list of facilities available on the bus.
     */
    public List<Facility> facilities;

    /**
     * The capacity of the bus.
     */
    public int capacity;

    /**
     * The type of the bus.
     */
    public BusType busType;

    /**
     * The list of schedules for the bus.
     */
    public List<Schedule> schedules;

    /**
     * The ID of the account associated with the bus.
     */
    public int accountId;

    /**
     * Creates a new Bus object with the specified parameters.
     *
     * @param accountId The ID of the account associated with the bus.
     * @param name      The name of the bus.
     * @param facilities The list of facilities available on the bus.
     * @param price     The price of the bus.
     * @param capacity  The capacity of the bus.
     * @param busType   The type of the bus.
     * @param departure The departure station for the bus.
     * @param arrival   The arrival station for the bus.
     */
    public Bus(int accountId, String name, List<Facility> facilities, Price price, int capacity, BusType busType, Station departure, Station arrival) {
        this.accountId = accountId;
        this.name = name;
        this.facilities = facilities;
        this.price = price;
        this.capacity = capacity;
        this.busType = busType;
        this.departure = departure;
        this.arrival = arrival;

        this.schedules = new ArrayList<>();
    }

    /**
     * Returns a string representation of the bus.
     *
     * @return A string containing bus details.
     */
    public String toString() {
        return (
                super.id + " " +
                        this.name + " " +
                        this.departure.stationName + " " +
                        this.arrival.stationName + " " +
                        this.price.price + " " +
                        this.facilities + " " +
                        this.capacity + " " +
                        this.busType + " "
        );
    }

    /**
     * Writes the bus data to a serialized format.
     *
     * @return An object representing the serialized bus data.
     */
    public Object write() {
        return this;
    }

    /**
     * Reads and populates the bus data from a serialized format.
     *
     * @param content The content containing serialized bus data.
     * @return True if reading and populating the data was successful; otherwise, false.
     */
    public boolean read(String content) {
        return false;
    }

    /**
     * Adds a new schedule to the bus with the specified departure time.
     *
     * @param schedule The departure time of the new schedule.
     * @throws IllegalArgumentException if a schedule with the same departure time already exists.
     */
    public void addSchedule(Timestamp schedule) {
        for (Schedule existingSchedule : schedules) {
            if (existingSchedule.departureSchedule.equals(schedule)) {
                throw new IllegalArgumentException("Duplicate schedule not allowed");
            }
        }
        schedules.add(new Schedule(schedule, this.capacity));
    }
}

