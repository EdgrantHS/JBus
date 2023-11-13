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
    public List<Facility> facilities;
    public int capacity;
    public BusType busType;
    public List<Schedule> schedules;
    public int accountId;

    public Bus(int accountId, String name, List<Facility> facilities, Price price, int capacity, BusType busType, Station departure, Station arrival) {
        this.accountId = accountId;
        this.name = name;
        this.facilities = facilities;
        this.price = price;
        this.capacity = capacity;
        this.busType = busType;
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
                        this.facilities + " " +
                        this.capacity + " " +
                        this.busType + " "
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
