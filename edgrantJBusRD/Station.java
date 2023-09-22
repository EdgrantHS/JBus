package edgrantJBusRD;


/**
 * Write a description of class Station here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Station extends Serializable
{
    // instance variables - replace the example below with your own
    public City city;
    public String stationName;
    
    public Station(int id, String stationName, City city)
    {
        super(id);
        this.stationName = stationName;
        this.city = city;
    }

    
    public String print()
    {
       return ("" + super.id + " " + this.stationName + " " + this.city);
    }
}
