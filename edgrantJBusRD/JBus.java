package edgrantJBusRD;


/**
 * Menghitung harga ticket dengan harga admin dan juga terdapat diskon
 *
 * @author Edgrant Henderson Suryajaya
 * @version 1.0.0
 */
public class JBus
{
    public static void main(String string[])
    {

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
        if (afterDiscount >= berforeDiscount)
        {
            return 0.0f;
        }
        else
        {
            float persen = 1 - ((float)afterDiscount / (float)berforeDiscount);
            return persen*100f;
        }
    }
    
    /**
     * getDiscountedPrice - menghitung harga akhir setelah didiskon
     *
     * @param  price  harga sebelum diskon
     * @param  discountPercentage  persentasi diskon
     * @return    harga akhir setelah didiskon
     */
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
    
    /**
     * getOriginalPrice - menghitung harga awal sebelum didiskon
     *
     * @param  discountedPrice  harga setelah diskon
     * @param  discountPercentage  persentasi diskon
     * @return    harga awal sebelum didiskon
     */
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
    
    /**
     * getAdminFeePercentage - mendapatkan persen admin yaitu 0.05
     *
     * @return    0.05
     */
    public static float getAdminFeePercentage()
    {
        return 0.05f;
    }
    
    /**
     * getAdminFee - mendapatkan harga admin dengan persentasi 0.05
     *
     * @return    harga admin yang akan dikenakan
     */
    public static int getAdminFee(int price)
    {
        float adminFee = price * getAdminFeePercentage();
        return (int) adminFee; //!cek ini return bener atau gak
    }
    
    /**
     * getAdminFee - menghitung harga pembelian kursi dengan pengenaan harga admin
     *
     * @param  price  harga satu kursi
     * @param  numberOfSeat  jumlah kursi yang ingin dibeli
     * @return    harga pembelian kursi dengan pengenaan harga admin
     */
    public static int getTotalPrice(int price, int numberOfSeat)
    {
        int priceWithoutAdmin = price * numberOfSeat;
        int priceAfterAdmin = priceWithoutAdmin + getAdminFee(priceWithoutAdmin);
        
        return priceAfterAdmin;
    }
    /*
    public Jbus()
    {
        // initialise instance variables
        x = 0;
    }*/
}