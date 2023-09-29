package edgrantJBusRD;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Write a description of class Bus here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Bus extends Serializable implements FileParser
{
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

    public Bus(int id, String name, Facility facility, Price price, int capacity, BusType busType, City city, Station departure, Station arrival)
    {
        super(id);
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

    public String toString(){
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
    
    
    public Object write(){
        return this;
    }
    
    public boolean read(String content){
        return false;
    }    

    public void addSchedule(Calendar calendar){
        Schedule schedule = new Schedule(calendar, this.capacity);
        this.schedules.add(schedule);
    }

    public void printSchedule(Schedule schedule){
        SimpleDateFormat SDFormat = new SimpleDateFormat("'Tanggal keberangkatan: 'MMMM dd, yyyy hh:mm:ss");
        
        System.out.println(SDFormat.format(schedule.departureSchedule.getTime()));
        System.out.println("Daftar kursi dan ketersdiaan:");
        System.out.println(schedule.seatAvailability);
    }
}
