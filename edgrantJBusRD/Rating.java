package edgrantJBusRD;


/**
 * Write a description of class Rating here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Rating
{
    // instance variables - replace the example below with your own
    private long count, total;

    /**
     * Constructor for objects of class Rating
     */
    public Rating()
    {
        // initialise instance variables
        this.count = 0;
        this.total = 0;
    }


    public void insert(int rating)
    {
        this.total += rating;
        this.count++;
    }
    
    public long getTotal()
    {
        return this.total;
    }
    
    public long getCount()
    {
        return this.count;
    }
    
    public double getAverage()
    {
        return count != 0 ? (double)this.total/this.count : 0;
    }
}
