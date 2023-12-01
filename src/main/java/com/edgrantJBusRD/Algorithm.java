package com.edgrantJBusRD;

import java.util.*;

/**
 * The {@code Algorithm} class provides static utility methods for various operations on collections and arrays.
 * These operations include collecting elements based on a value or predicate, counting elements, finding an element,
 * checking for the existence of an element, and paginating a collection or array.
 * This class cannot be instantiated.
 */
public class Algorithm {
    private Algorithm() {
    }

    /**
     * Collects elements from an array that match the predicate logic.
     *
     * @param array the array to collect
     * @param value the value to match
     * @param <T> the type of elements in the iterable
     * @return a list of elements that match the value
     */
    public static <T> List<T> collect(T[] array, T value) {
        Iterator<T> i = Arrays.stream(array).iterator();
        return collect(i, value);
    }

    /**
     * Collects elements from an array that match the predicate logic.
     *
     * @param array the array to collect
     * @param pred the function that return a boolean that match the logic
     * @param <T> the type of elements in the iterable
     * @return a list of elements that match the value
     */
    public static <T> List<T> collect(T[] array, Predicate<T> pred) {
        Iterator<T> i = Arrays.stream(array).iterator();
        return collect(i, pred);
    }

    /**
     * Collects elements from an iterable that match the specified value.
     *
     * @param iterable the iterable to process
     * @param value the value to match
     * @param <T> the type of elements in the iterable
     * @return a list of elements that match the value
     */
    public static <T> List<T> collect(Iterable<T> iterable, T value) {
        Iterator<T> i = iterable.iterator();
        return collect(i, value);
    }

    /**
     * Collects elements from an iterable that match the predicate logic.
     *
     * @param iterable the iterable to process
     * @param pred the function that return a boolean that match the logic
     * @param <T> the type of elements in the iterable
     * @return a list of elements that match the value
     */
    public static <T> List<T> collect(Iterable<T> iterable, Predicate<T> pred) {
        Iterator<T> i = iterable.iterator();
        return collect(i, pred);
    }


    /**
     * Collects elements from an iterator that match the value.
     *
     * @param iterator the iterator to process
     * @param value the value to match
     * @param <T> the type of elements in the iterable
     * @return a list of elements that match the value
     */
    public static <T> List<T> collect(Iterator<T> iterator, T value) {
        Objects.requireNonNull(value);
        Predicate<T> predicate = value::equals;
        return collect(iterator, predicate);
    }

    /**
     * Collects elements from an iterator that match the value.
     *
     * @param iterator the iterator to process
     * @param pred the function that return a boolean that match the logic
     * @param <T> the type of elements in the iterable
     * @return a list of elements that match the value
     */
    public static <T> List<T> collect(Iterator<T> iterator, Predicate<T> pred) {
        List<T> list = new ArrayList();

        while(iterator.hasNext()) {
            T tempVar = iterator.next();
            if (pred.predicate(tempVar)) {
                list.add(tempVar);
            }
        }

        return list;
    }



    /**
     * count elements from an array that match the predicate logic.
     *
     * @param array the array to collect
     * @param value the value to match
     * @param <T> the type of elements in the iterable
     * @return the number of elements that match the value
     */
    public static <T> int count(T[] array, T value) {
        Iterator<T> it = Arrays.stream(array).iterator();
        Objects.requireNonNull(value);
        Predicate<T> pred = value::equals;
        return count(it, value);
    }

    /**
     * count elements from an array that match the predicate logic.
     *
     * @param array the array to collect
     * @param pred the function that return a boolean that match the logic
     * @param <T> the type of elements in the iterable
     * @return the number of elements that match the value
     */
    public static <T> int count(T[] array, Predicate<T> pred) {
        Iterator<T> it = Arrays.stream(array).iterator();
        return count(it, pred);
    }

    /**
     * count elements from an iterable that match the specified value.
     *
     * @param iterable the iterable to process
     * @param value the value to match
     * @param <T> the type of elements in the iterable
     * @return the number of elements that match the value
     */
    public static <T> int count(Iterable<T> iterable, T value) {
        Objects.requireNonNull(value);
        Predicate<T> pred = value::equals;
        return count(iterable, pred);
    }

    /**
     * count elements from an iterable that match the predicate logic.
     *
     * @param iterable the iterable to process
     * @param pred the function that return a boolean that match the logic
     * @param <T> the type of elements in the iterable
     * @return the number of elements that match the value
     */
    public static <T> int count(Iterable<T> iterable, Predicate<T> pred) {
        Iterator<T> it = iterable.iterator();
        return count(it, pred);
    }

    /**
     * count elements from an iterator that match the value.
     *
     * @param iterator the iterator to process
     * @param value the value to match
     * @param <T> the type of elements in the iterable
     * @return the number of elements that match the value
     */
    public static <T> int count(Iterator<T> iterator, T value) {
        Objects.requireNonNull(value);
        Predicate<T> pred = value::equals;
        return count(iterator, pred);
    }

    /**
     * count elements from an iterator that match the value.
     *
     * @param iterator the iterator to process
     * @param pred the function that return a boolean that match the logic
     * @param <T> the type of elements in the iterable
     * @return the number of elements that match the value
     */
    public static <T> int count(Iterator<T> iterator, Predicate<T> pred) {
        int count = 0;

        while(iterator.hasNext()) {
            if (pred.predicate(iterator.next())) {
                ++count;
            }
        }

        return count;
    }

    /**
     * Find an element from an array that match the predicate logic.
     *
     * @param array the array to collect
     * @param value the value to match
     * @param <T> the type of elements in the iterable
     * @return an elements that match the value
     */
    public static <T> T find(T[] array, T value) {
        Iterator<T> it = Arrays.stream(array).iterator();
        return find(it, value);
    }

    /**
     * Find an element from an array that match the predicate logic.
     *
     * @param array the array to collect
     * @param pred the function that return a boolean that match the logic
     * @param <T> the type of elements in the iterable
     * @return an elements that match the value
     */
    public static <T> T find(T[] array, Predicate<T> pred) {
        Iterator<T> it = Arrays.stream(array).iterator();
        return find(it, pred);
    }

    /**
     * Find an element from an iterable that match the specified value.
     *
     * @param iterable the iterable to process
     * @param value the value to match
     * @param <T> the type of elements in the iterable
     * @return an elements that match the value
     */
    public static <T> T find(Iterable<T> iterable, T value) {
        Iterator<T> it = iterable.iterator();
        return find(it, value);
    }

    /**
     * Find an element from an iterable that match the predicate logic.
     *
     * @param iterable the iterable to process
     * @param pred the function that return a boolean that match the logic
     * @param <T> the type of elements in the iterable
     * @return an elements that match the value
     */
    public static <T> T find(Iterable<T> iterable, Predicate<T> pred) {
        Iterator<T> it = iterable.iterator();
        return find(it, pred);
    }

    /**
     * Find an element from an iterator that match the value.
     *
     * @param iterator the iterator to process
     * @param value the value to match
     * @param <T> the type of elements in the iterable
     * @return an elements that match the value
     */
    public static <T> T find(Iterator<T> iterator, T value) {
        Objects.requireNonNull(value);
        Predicate<T> pred = value::equals;
        return find(iterator, pred);
    }

    /**
     * Find an element from an iterator that match the value.
     *
     * @param iterator the iterator to process
     * @param pred the function that return a boolean that match the logic
     * @param <T> the type of elements in the iterable
     * @return an elements that match the value
     */
    public static <T> T find(Iterator<T> iterator, Predicate<T> pred) {
        while(true) {
            if (iterator.hasNext()) {
                T current = iterator.next();
                if (!pred.predicate(current)) {
                    continue;
                }

                return current;
            }

            return null;
        }
    }



    /**
     * Find if an element exist from an array that match the predicate logic.
     *
     * @param array the array to collect
     * @param value the value to match
     * @param <T> the type of elements in the iterable
     * @return a boolean whether an elements that match the value exist
     */
    public static <T> boolean exists(T[] array, T value) {
        Iterator<T> it = Arrays.stream(array).iterator();
        return exists(it, value);
    }

    /**
     * Find if an element exist from an array that match the predicate logic.
     *
     * @param array the array to collect
     * @param pred the function that return a boolean that match the logic
     * @param <T> the type of elements in the iterable
     * @return a boolean whether an elements that match the value exist
     */
    public static <T> boolean exists(T[] array, Predicate<T> pred) {
        Iterator<T> it = Arrays.stream(array).iterator();
        return exists(it, pred);
    }

    /**
     * Find if an element exist from an iterable that match the specified value.
     *
     * @param iterable the iterable to process
     * @param value the value to match
     * @param <T> the type of elements in the iterable
     * @return a boolean whether an elements that match the value exist
     */
    public static <T> boolean exists(Iterable<T> iterable, T value) {
        Iterator<T> it = iterable.iterator();
        return exists(it, value);
    }

    /**
     * Find if an element exist from an iterable that match the predicate logic.
     *
     * @param iterable the iterable to process
     * @param pred the function that return a boolean that match the logic
     * @param <T> the type of elements in the iterable
     * @return a boolean whether an elements that match the value exist
     */
    public static <T> boolean exists(Iterable<T> iterable, Predicate<T> pred) {
        Iterator<T> it = iterable.iterator();
        return exists(it, pred);
    }

    /**
     * Find if an element exist from an iterator that match the value.
     *
     * @param iterator the iterator to process
     * @param value the value to match
     * @param <T> the type of elements in the iterable
     * @return a boolean whether an elements that match the value exist
     */
    public static <T> boolean exists(Iterator<T> iterator, T value) {
        Objects.requireNonNull(value);
        Predicate<T> pred = value::equals;
        return exists(iterator, pred);
    }

    /**
     * Find if an element exist from an iterator that match the value.
     *
     * @param iterator the iterator to process
     * @param pred the function that return a boolean that match the logic
     * @param <T> the type of elements in the iterable
     * @return a boolean whether an elements that match the value exist
     */
    public static <T> boolean exists(Iterator<T> iterator, Predicate<T> pred) {
        while(true) {
            if (iterator.hasNext()) {
                T current = iterator.next();
                if (!pred.predicate(current)) {
                    continue;
                }

                return true;
            }

            return false;
        }
    }



    public static <T> List<T> paginate(T[] arr, int page, int pagesize, Predicate<T> pred) {
        Iterator<T> i = Arrays.stream(arr).iterator();
        return paginate(i, page, pagesize, pred);
    }

    public static <T> List<T> paginate(Iterable<T> iterable, int page, int pagesize, Predicate<T> pred) {
        Iterator<T> i = iterable.iterator();
        return paginate(i, page, pagesize, pred);
    }

    public static <T> List<T> paginate(Iterator<T> iterator, int page, int pagesize, Predicate<T> pred) {
        List<T> pageResult = new ArrayList<>();
        int count = 0;
        int startindex = page * pagesize;
        int endindex = startindex + pagesize;
        while(iterator.hasNext()) {
            T obj = iterator.next();
            if (pred.predicate(obj)) {
                if (count >= startindex && count < endindex) {
                    pageResult.add(obj);
                }

                ++count;
            }
        }

        return pageResult;
    }
}
