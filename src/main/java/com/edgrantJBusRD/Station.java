package com.edgrantJBusRD;

public class Station extends Serializable
{
    // instance variables - replace the example below with your own
    public City city;
    public String address, stationName;
    
    public Station(String stationName, City city, String address)
    {
        this.stationName = stationName;
        this.city = city;
    }


    public String toString()
    {
        return ("" + super.id + " " + this.stationName + " " + this.city + " " + this.address);
    }
    public String print()
    {
        return ("" + super.id + " " + this.stationName + " " + this.city + " " + this.address);
    }
}
