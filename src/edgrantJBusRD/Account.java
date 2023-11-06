package edgrantJBusRD;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account extends Serializable
{
    private final static String REGEX_EMAIL = "^[a-zA-Z0-9]+@[a-zA-Z]+\\.[a-zA-Z]+$";
    private final static String REGEX_PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
    public String email, name, password;

    public Account(String name, String email, String password)
    {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String toString(){
        return (
            "" + 
            super.id + " " +
            this.name + " " +
            this.email + " " +
            this.password
        );
    }
    
    public Object write(){
        return this;
    }
    
    public boolean read(String content){
        return false;
    }

    public boolean validate(){
        Pattern pattern = Pattern.compile(this.REGEX_EMAIL);
        Matcher matcher = pattern.matcher(this.email);
        boolean name = matcher.find();
        pattern = Pattern.compile(this.REGEX_PASSWORD);
        matcher = pattern.matcher(this.password);
        boolean phone = matcher.find();
        return name && phone;
    }
}
