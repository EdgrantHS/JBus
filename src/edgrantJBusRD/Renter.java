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
    public Renter(String companyName)
    {
        // initialise instance variables
        this.address = "";
        this.companyName = companyName;
        this.phoneNumber = 0;
    }

    public Renter(String companyName, String address)
    {
        // initialise instance variables
        this.address = address;
        this.companyName = companyName;
        this.phoneNumber = 0;
    }

    public Renter(String companyName, int phoneNumber)
    {
        // initialise instance variables
        this.address = "";
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
    }

    public Renter(String companyName, int phoneNumber, String address)
    {
        // initialise instance variablel
        this.address = address;
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
    }

}
