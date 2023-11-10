package com.edgrantJBusRD;
import com.edgrantJBusRD.dbjson.Serializable;

import java.util.*;
import java.sql.Timestamp;

public class Bus extends Serializable {
    // instance variables - replace the example below with your own
    public String name;
    public Station departure;
    public Station arrival;
    public Price price;
    public Facility facility;
    public int capacity;
    public BusType busType;
    public City city;
    public List<Schedule> schedules;

    public Bus(String name, Facility facility, Price price, int capacity, BusType busType, City city, Station departure, Station arrival) {
        this.name = name;
        this.facility = facility;
        this.price = price;
        this.capacity = capacity;
        this.busType = busType;
        this.city = city;
        this.departure = departure;
        this.arrival = arrival;

        this.schedules = new ArrayList<Schedule>();
    }

    public String toString() {
        return (
                "" +
                        super.id + " " +
                        this.name + " " +
                        this.departure.stationName + " " +
                        this.arrival.stationName + " " +
                        this.price.price + " " +
                        this.facility + " " +
                        this.capacity + " " +
                        this.busType + " " +
                        this.city
        );
    }


    public Object write() {
        return this;
    }

    public boolean read(String content) {
        return false;
    }

    public void addSchedule(Timestamp schedule) {
        for (Schedule existingSchedule : schedules) {
            if (existingSchedule.departureSchedule.equals(schedule)) {
                throw new IllegalArgumentException("Duplicate schedule not allowed");
            }
        }
        schedules.add(new Schedule(schedule, this.capacity));
    }

}
