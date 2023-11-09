package com.edgrantJBusRD;
import java.util.ArrayList;

public class Validate
{
    public Validate()
    {
    }

    public static ArrayList<Double> filter (Price[] list, int value, boolean less)
    {
        ArrayList<Double> newArr = new ArrayList <Double>();
        for (int i = 0; i < list.length; i++){
            if (less){
                if (list[i].price <= value){
                    newArr.add(list[i].price);
                }
            }
            else{
                if (list[i].price > value){
                    newArr.add(list[i].price);
                }
            }
        }
        return newArr;
    }
}
