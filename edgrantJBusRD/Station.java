package edgrantJBusRD;

public class Station extends Serializable
{
    // instance variables - replace the example below with your own
    public City city;
    public String address, stationName;
    
    public Station(int id, String stationName, City city, String address)
    {
        super(id);
        this.stationName = stationName;
        this.city = city;
    }

    
    public String print()
    {
        return ("" + super.id + " " + this.stationName + " " + this.city + " " + this.address);
    }
}
