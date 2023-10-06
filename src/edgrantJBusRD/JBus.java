package edgrantJBusRD;

public class JBus
{
    public static void main(String[] args) {
        Integer[] numbers = {10, 20, 30, 40, 50};
        int valueToCheck = 30;
        boolean result = Algorithm.exists(numbers, valueToCheck);
        if (result) {
            System.out.println(valueToCheck + " terdapat dalam array.");
        }
        else {
            System.out.println(valueToCheck + " tidak terdapat dalam array.");
        }
    }
//    public static Bus createBus() {
//        Price price = new Price(750000, 5);
//        Bus bus = new Bus(1, "Netlab Bus", Facility.LUNCH, price, 25, BusType.REGULER, City.BANDUNG, new Station(1, "Depok Terminal", City.DEPOK, "Jl. Margonda Raya"), new Station(2, "Halte UI", City.JAKARTA, "Universitas Indonesia"));
//        return bus;
//    }
}
