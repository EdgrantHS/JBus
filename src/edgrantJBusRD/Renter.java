package edgrantJBusRD;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Write a description of class Renter here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Renter extends Serializable
{
    private final String REGEX_NAME = "/[a-zA-Z0-9_]{4,20}";
    private final String REGEX_PHONE = "/[0-9]{9,12}";
    public String address, companyName;
    public int phoneNumber;

    public Renter(String companyName)
    {
        // initialise instance variables
        this.address = "";
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
    public Renter(String companyName, String address)
    {
        // initialise instance variables
        this.address = address;
        this.companyName = companyName;
        this.phoneNumber = 0;
    }


    public Renter(String companyName, int phoneNumber, String address)
    {
        // initialise instance variablel
        this.address = address;
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
    }

    public boolean validate(){
        Pattern pattern = Pattern.compile(this.REGEX_NAME);
        Matcher matcher = pattern.matcher(this.companyName);
        boolean name = matcher.find();
        pattern = Pattern.compile(this.REGEX_PHONE);
        matcher = pattern.matcher(Integer.toString(this.phoneNumber));
        boolean phone = matcher.find();
        return name && phone;
    }
}
