package edgrantJBusRD;

public class Price
{
    // instance variables - replace the example below with your own
    public double price, rebate;

    public Price(double price)
    {
        // initialise instance variables
        this.price = price;
        this.rebate = 0;
    }
    
    public Price(double price, double rebate)
    {
        // initialise instance variables
        this.price = price;
        this.rebate = rebate;
    }
}