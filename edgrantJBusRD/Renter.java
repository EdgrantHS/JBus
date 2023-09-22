package edgrantJBusRD;


/**
 * Write a description of class Renter here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Renter extends Serializable
{
    // instance variables - replace the example below with your own
    public String address, companyName;
    public int phoneNumber;

    /**
     * Constructor for objects of class Renter
     */
    public Renter(int id, String companyName)
    {
        // initialise instance variables
        super(id);
        this.address = "";
        this.companyName = companyName;
        this.phoneNumber = 0;
    }

    public Renter(int id, String companyName, String address)
    {
        // initialise instance variables
        super(id);
        this.address = address;
        this.companyName = companyName;
        this.phoneNumber = 0;
    }

    public Renter(int id, String companyName, int phoneNumber)
    {
        // initialise instance variables
        super(id);
        this.address = "";
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
    }

    public Renter(int id, String companyName, int phoneNumber, String address)
    {
        // initialise instance variables
        super(id);
        this.address = address;
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
    }

}
