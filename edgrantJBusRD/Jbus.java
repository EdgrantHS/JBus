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
        Price[] unfilteredArray = new Price[5];
        for(int i = 0; i <unfilteredArray.length; i++){
            int j = 5000;
            unfilteredArray[i] = new Price((i+1)*j);
        }
        System.out.println("Price List");
        for(Price price : unfilteredArray){
            System.out.println(price.price);
        }
        System.out.println("Below 12000.0");
        System.out.println(Validate.filter(unfilteredArray, 12000, true));
        System.out.println("Above 10000.0");
        System.out.println(Validate.filter(unfilteredArray, 10000, false));
    }
}
