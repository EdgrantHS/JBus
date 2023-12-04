package com.edgrantJBusRD.dbjson;

import java.util.HashMap;

/**
 * Serializable class provides a unique identification mechanism for objects.
 * It assigns a unique ID to each instance of the class and its subclasses.
 * The unique ID is generated based on the class type and the number of instances created.
 */
public class Serializable implements Comparable<Serializable>{
    public final int id;
    private static final HashMap<Class<?>, Integer> mapCounter = new HashMap<Class <?>, Integer>();

    /**
     * Protected constructor for Serializable.
     * Initializes the object with a unique ID, which is generated based on the number of instances of its class.
     */
    protected Serializable(){
        Integer counter = mapCounter.get(getClass());
        counter = counter == null ? 0 : counter + 1;
        mapCounter.put(getClass(), counter);
        this.id = counter;
    }

    /**
     * Retrieves the last assigned ID for a given class.
     *
     * @param getter The class for which the last assigned ID is required.
     * @param <T> The type of the class.
     * @return The last assigned ID for the specified class, or null if no ID has been assigned yet.
     */
    public static <T> Integer getLastAssignedId(Class<T> getter) {
        return mapCounter.get(getter);
    }

    /**
     * Sets the last assigned ID for a given class.
     *
     * @param setter The class for which the last assigned ID needs to be set.
     * @param number The new ID to be set for the specified class.
     * @param <T>    The type of the class.
     */
    public static <T> void setLastAssignedId(Class<T> setter, int number) {
        mapCounter.put(setter, number);
    }

    /**
     * Compares this Serializable object with another Serializable object.
     *
     * @param temp The Serializable object to be compared with.
     * @return A negative integer, zero, or a positive integer as this object's ID
     *         is less than, equal to, or greater than the specified object's ID.
     */
    public int compareTo(Serializable temp) {
        return Integer.compare(this.id, temp.id);
    }

    /**
     * Checks if this Serializable object is equal to another Serializable object.
     *
     * @param temp The Serializable object to be checked for equality.
     * @return true if the IDs of both objects are equal, false otherwise.
     */
    public boolean equals(Serializable temp) {
        return temp.id == this.id;
    }

    /**
     * Checks if this object is equal to another object.
     *
     * @param object The object to be checked for equality.
     * @return true if the object is an instance of Serializable and its ID matches this object's ID, false otherwise.
     */
    @Override
    public boolean equals(Object object) {
        return object instanceof Serializable && ((Serializable) object).id == this.id;
    }
}
