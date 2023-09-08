package edgrantJBusRD;


/**
 * Write a description of class Jbus here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Jbus
{
    public static void main(String string[])
    {
        int x;
    }
    
    /**
     * getBusId - mendapatkan bus id yaitu 0
     *
     * @return    0
     */
    public static int getBusId()
    {
        return 0;
    }
    
    /**
     * getBusName - mendapatkan nama bus yaitu Bus
     *
     * @return    "Bus"
     */
    public static String getBusName()
    {
        return "Bus";
    }
    
    /**
     * isDiscount - mendapatkan nama apakah diskon yaitu iya
     *
     * @return    true
     */
    public static boolean isDiscount()
    {
        return true;
    }
    
    /**
     * getDiscountPercentage - menghitung persentasi diskon
     *
     * @param  berforeDiscount  harga sebelum diskon
     * @param  afterDiscount  harga setelah diskon
     * @return    persentase diskon untuk menyebabkan harga sebelum jadi setelah
     */
    public static float getDiscountPercentage(int berforeDiscount, int afterDiscount)
    {
        if (afterDiscount <= berforeDiscount)
        {
            return 0.0f;
        }
        else
        {
            float persen = 1 - (afterDiscount / berforeDiscount);
            return persen*100;
        }
    }
    
    public static int getDiscountedPrice(int price, float discountPercentage)
    {
        float discountedPrice;
        if (discountPercentage >= 100) 
        {
            return 0;
        }
        else
        {
            discountedPrice = (1 - discountPercentage/100) * price;
            return (int) discountedPrice; //!cek ini return bener atau gak
        }
    }
    
    public static int getOriginalPrice(int discountedPrice, float discountPercentage)
    {
        float originalPrice;
        if (discountPercentage >= 100) 
        {
            return 0;
        }
        else
        {
            originalPrice = discountedPrice / (1 - discountPercentage/100);
            return (int) originalPrice; //!cek ini return bener atau gak
        }
    }
    
    public static float getAdminFeePercentage()
    {
        return 0.05f;
    }
    
    public static int getAdminFee(int price)
    {
        float adminFee = price * getAdminFeePercentage();
        return (int) adminFee; //!cek ini return bener atau gak
    }
    
    public static int getTotalPrice(int price, int numberOfSeat)
    {
        int priceWithoutAdmin = price * numberOfSeat;
        int priceAfterAdmin = priceWithoutAdmin - getAdminFee(priceWithoutAdmin);
        
        return priceAfterAdmin;
    }
    /*
    public Jbus()
    {
        // initialise instance variables
        x = 0;
    }*/
}
