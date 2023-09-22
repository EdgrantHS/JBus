package edgrantJBusRD;


/**
 * Write a description of class Bus here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Bus extends Serializable
{
    // instance variables - replace the example below with your own
    public Facility facility;
    public String name;
    public Price price;
    public int capacity;

    public Bus(int id, String name, Facility facility, Price price, int capacity)
    {
        super(id);
        this.name = name;
        this.facility = facility;
        this.price = price;
        this.capacity = capacity;
    }

}
