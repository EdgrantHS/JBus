package edgrantJBusRD;


/**
 * Write a description of class Jbus here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Jbus
{
    public static void main(){
        Payment testPayment = new Payment(1, 1, 1, "A", 1, "A", "A");
        Invoice testInvoice = new Invoice(2, 2, 2, "B");
        Station testStation = new Station(3, "C", City.DEPOK);
        System.out.println(testPayment.print());
        System.out.println(testInvoice.print());
        System.out.println(testStation.print());
    }
}
