package edgrantJBusRD;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Renter extends Serializable
{
    private final static String REGEX_NAME = "^[a-zA-Z0-9_]{4,20}$";
    private final static String REGEX_PHONE = "^[0-9]{9,12}$";
    public String address, companyName;
    public String phoneNumber;

    public Renter(String companyName)
    {
        // initialise instance variables
        this.address = "";
        this.companyName = companyName;
        this.phoneNumber = "";
    }

    public Renter(String companyName, String phoneNumber)
    {
        // initialise instance variables
        this.address = "";
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
    }

    public Renter(String companyName, String phoneNumber, String address)
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
        matcher = pattern.matcher(this.phoneNumber);
        boolean phone = matcher.find();
        return name && phone;
    }
}
