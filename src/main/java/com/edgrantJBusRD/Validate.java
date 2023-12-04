package com.edgrantJBusRD;
import java.util.ArrayList;

/**
 * The {@code Validate} class provides utility methods for data validation and filtering.
 */
public class Validate {

    /**
     * Filters an array of {@code Price} objects based on a specified value and condition.
     *
     * @param list  The array of {@code Price} objects to filter.
     * @param value The value used as the filter criteria.
     * @param less  A boolean flag indicating whether to filter values less than or greater than the specified value.
     *              If {@code true}, filters values less than or equal to the specified value; if {@code false}, filters values greater than the specified value.
     * @return An {@code ArrayList} of {@code Double} values that meet the filter criteria.
     */
    public static ArrayList<Double> filter(Price[] list, int value, boolean less) {
        ArrayList<Double> newArr = new ArrayList<Double>();
        for (int i = 0; i < list.length; i++) {
            if (less) {
                if (list[i].price <= value) {
                    newArr.add(list[i].price);
                }
            } else {
                if (list[i].price > value) {
                    newArr.add(list[i].price);
                }
            }
        }
        return newArr;
    }
}
