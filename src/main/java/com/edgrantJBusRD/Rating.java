package com.edgrantJBusRD;

public class Rating
{
    private long count, total;

    public Rating()
    {
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
    
    public String toString(){
        return (
            "" + 
            this.count + " " +
            this.total
        );
    }
}