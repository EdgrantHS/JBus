package edgrantJBusRD;


/**
 * Write a description of class Price here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Price
{
    // instance variables - replace the example below with your own
    public double price, rebate;
    public int discount;

    /**
     * Constructor for objects of class Price
     */
    public Price(double price)
    {
        // initialise instance variables
        this.price = price;
        this.discount = 0;
        this.rebate = 0;
    }

    public Price(double price, int discount)
    {
        // initialise instance variables
        this.price = price;
        this.discount = discount;
        this.rebate = 0;
    }
    
    public Price(double price, double rebate)
    {
        // initialise instance variables
        this.price = price;
        this.discount = 0;
        this.rebate = rebate;
    }
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */

    /*public double tes(){
        return getRebatedPrice();
    }*/
    
    private double getDiscountedPrice()
    {
        double hargaAkhir;
        
        if (this.discount >= 100){
            this.discount = 100;
            return 0.0;
        }
        
        hargaAkhir = ((float)(100 - this.discount)/100.0) * this.price;
        return hargaAkhir;
    }
    
    private double getRebatedPrice()
    {
        double hargaAkhir = this.price - this.rebate;
        
        return hargaAkhir < 0 ? 0 : hargaAkhir;
    }
}
