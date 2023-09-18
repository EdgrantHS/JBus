package edgrantJBusRD;


/**
 * Write a description of class Voucher here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Voucher
{
    // instance variables - replace the example below with your own
    public String name;
    private boolean used;
    public double minimum, cut;
    public int code;
    public Type type;
    
    /*public static void main(){
        Voucher v = new Voucher("h", 3, Type.DISCOUNT, 3000, 40);
        Price p = new Price(10000);
        
        System.out.println(v.apply(p));
    }*/
    
    public Voucher(String name, int code, Type type, double minimum, double cut)
    {
        this.name = name;
        this.code = code;
        this.type = type;
        this.minimum = minimum;
        this.cut = cut;
        this.used = false;
    }
    
    public double apply(Price price)
    {
        double appliedPrice = -1;
        
        used = true;
        if (this.type == Type.REBATE){
            appliedPrice = price.price - cut;
        }
        
        if (this.type == Type.DISCOUNT){
            appliedPrice = price.price - (cut/100 * price.price);
        }
        
        return appliedPrice;
    }
    
    public boolean isUsed()
    {
        // put your code here
        return this.used;
    }
    
    public boolean canApply(Price price)
    {
        if (price.price > this.minimum){
            if (!used){
                return true;
            }
        }
        
        return false;
    }
}
