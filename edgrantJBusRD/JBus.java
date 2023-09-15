package edgrantJBusRD;


/**
 * Menghitung harga ticket dengan harga admin dan juga terdapat diskon
 *
 * @author Edgrant Henderson Suryajaya
 * @version 1.0.0
 */
public class JBus
{
    public static void main(String string[]){
        Bus newBus = createBus();
        System.out.println(newBus.name);
        System.out.println(newBus.facility);
        System.out.println(newBus.price.price);
        System.out.println(newBus.capacity);
    }
    
    public static Bus createBus(){
        Price price = new Price(100000, 50.0);
        Bus bus = new Bus("My Bus", Facility.WIFI, price, 50);
        return bus;
    }
    /*
    public Jbus()
    {
        // initialise instance variables
        x = 0;
    }*/
}