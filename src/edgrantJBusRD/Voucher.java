package edgrantJBusRD;


/**
 * Write a description of class Voucher here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Voucher extends Serializable
{
    // instance variables - replace the example below with your own
    public String name;
    private boolean used;
    public double minimum, cut;
    public int code;
    public Type type;
    
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
        double appliedPrice = price.price;
        
        if (canApply(price)){
            if (this.type == Type.REBATE){
                appliedPrice = price.price - cut;
                if (appliedPrice < 0){
                    return 0;
                }
            }
            
            if (this.type == Type.DISCOUNT){
                if (cut >= 100){
                    return 0;
                }
                appliedPrice = price.price - (cut/100 * price.price);
            }   
        }
        
        used = true;
        return appliedPrice;
    }
    
    public boolean isUsed()
    {
        // put your code here
        return this.used;
    }
    
    public boolean canApply(Price price)
    {
        if ((price.price > this.minimum) && (!isUsed())){
            return true;
        }
        else{
            return false;   
        }
    }   
        
    public Object write(){
        return this;
    }
    
    public boolean read(String content){
        return false;
    }
}
