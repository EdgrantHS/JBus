package edgrantJBusRD;

public class Account extends Serializable implements FileParser
{
    // instance variables - replace the example below with your own
    public String email, name, password;

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
    
    public Object write(){
        return this;
    }
    
    public boolean read(String content){
        return false;
    }
}