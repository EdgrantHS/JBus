package edgrantJBusRD;

public class Account extends Serializable
{
    // instance variables - replace the example below with your own
    public String email, name, password;

    /**
     * Constructor for objects of class Account
     */
    public Account(int id, String name, String email, String password)
    {
        super(id);
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
}
