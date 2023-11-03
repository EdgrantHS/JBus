package edgrantJBusRD;
import java.util.*;
import java.sql.Timestamp;

public class Bus extends Serializable {
    // instance variables - replace the example below with your own
    public String name;
    public Station departure;
    public Station arrival;
    public Price price;
    public Facility facility;
    public int capacity;
    public BusType busType;
    public City city;
    public List<Schedule> schedules;

    public Bus(String name, Facility facility, Price price, int capacity, BusType busType, City city, Station departure, Station arrival) {
        this.name = name;
        this.facility = facility;
        this.price = price;
        this.capacity = capacity;
        this.busType = busType;
        this.city = city;
        this.departure = departure;
        this.arrival = arrival;

        this.schedules = new ArrayList<Schedule>();
    }

    public String toString() {
        return (
                "" +
                        super.id + " " +
                        this.name + " " +
                        this.departure.stationName + " " +
                        this.arrival.stationName + " " +
                        this.price.price + " " +
                        this.facility + " " +
                        this.capacity + " " +
                        this.busType + " " +
                        this.city
        );
    }


    public Object write() {
        return this;
    }

    public boolean read(String content) {
        return false;
    }

    public void addSchedule(Timestamp schedule) {
        for (Schedule existingSchedule : schedules) {
            if (existingSchedule.departureSchedule.equals(schedule)) {
                throw new IllegalArgumentException("Duplicate schedule not allowed");
            }
        }
        schedules.add(new Schedule(schedule, this.capacity));
    }

    public static List<Bus> filterByDeparture(List<Bus> buses, City departure, int page, int pageSize){
        List<Bus> newBuses = new ArrayList<>();
        newBuses.add(Algorithm.<Bus>find(buses, s -> s.departure.city == departure));
        return Algorithm.paginate(newBuses.iterator(), page, pageSize, t -> true);
    }
    public static List<Bus> filterByPrice(List<Bus> buses, int min, int max) {
        List<Bus> newBuses = new ArrayList<>();
        newBuses.add(Algorithm.<Bus>find(buses, s -> s.price.price >= min && s.price.price <= max));
        return newBuses;
    }
    public static Bus filterByPrice(List<Bus> buses, int id) {
        return Algorithm.<Bus>find(buses, s -> s.id == id);
    }
    public static List<Bus> filterByDepartureAndArival(List<Bus> buses, City departure, City arrival, int page, int pageSize) {
        List<Bus> newBuses = new ArrayList<>();
        newBuses.add(Algorithm.<Bus>find(buses, s -> s.departure.city == departure && s.departure.city == arrival));
        return Algorithm.paginate(newBuses.iterator(), page, pageSize, t -> true);
    }
//    pasrah dulu gasih
}
