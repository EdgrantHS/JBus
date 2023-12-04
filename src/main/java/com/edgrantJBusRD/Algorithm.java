package com.edgrantJBusRD;

import java.util.*;
/**
 * The Algorithm class provides utility methods for working with collections and iterators.
 * It includes methods for filtering, counting, finding elements, checking existence, and pagination in collections.
 */
public class Algorithm {
    private Algorithm() {
    }

    public static <T> List<T> collect(Iterable<T> iterable, T value) {
        Iterator<T> i = iterable.iterator();
        return collect(i, value);
    }

    public static <T> List<T> collect(Iterable<T> iterable, Predicate<T> predicate) {
        Iterator<T> i = iterable.iterator();
        return collect(i, predicate);
    }

    public static <T> List<T> collect(T[] array, T value) {
        Iterator<T> i = Arrays.stream(array).iterator();
        return collect(i, value);
    }

    public static <T> List<T> collect(Iterator<T> iterator, T value) {
        Objects.requireNonNull(value);
        Predicate<T> predicate = value::equals;
        return collect(iterator, predicate);
    }

    public static <T> List<T> collect(T[] array, Predicate<T> predicate) {
        Iterator<T> i = Arrays.stream(array).iterator();
        return collect(i, predicate);
    }

    // Methods for counting elements.

    /**
     * Collects elements from an iterable based on a predicate condition.
     *
     * @param iterator  The iterator to collect elements from.
     * @param pred The predicate condition to filter elements.
     * @param <T>       The type of elements in the iterable.
     * @return A list containing elements that satisfy the predicate condition.
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

    public static <T> int count(Iterator<T> iterator, T value) {
        Objects.requireNonNull(value);
        Predicate<T> pred = value::equals;
        return count(iterator, pred);
    }

    public static <T> int count(Iterable<T> iterable, T value) {
        Objects.requireNonNull(value);
        Predicate<T> pred = value::equals;
        return count(iterable, pred);
    }

    public static <T> int count(T[] array, Predicate<T> pred) {
        Iterator<T> it = Arrays.stream(array).iterator();
        return count(it, pred);
    }

    public static <T> int count(T[] array, T value) {
        Iterator<T> it = Arrays.stream(array).iterator();
        Objects.requireNonNull(value);
        Predicate<T> pred = value::equals;
        return count(it, value);
    }

    public static <T> int count(Iterable<T> iterable, Predicate<T> pred) {
        Iterator<T> it = iterable.iterator();
        return count(it, pred);
    }

    /**
     * Counts the occurrences of a value in an iterable.
     *
     * @param iterator The iterator to count elements in.
     * @param pred    The function to count.
     * @param <T>      The type of elements in the iterable.
     * @return The number of occurrences of the value.
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

    public static <T> T find(Iterator<T> iterator, T number) {
        Objects.requireNonNull(number);
        Predicate<T> pred = number::equals;
        return find(iterator, pred);
    }

    public static <T> T find(T[] array, T value) {
        Iterator<T> it = Arrays.stream(array).iterator();
        return find(it, value);
    }

    public static <T> T find(Iterable<T> iterable, Predicate<T> pred) {
        Iterator<T> it = iterable.iterator();
        return find(it, pred);
    }

    public static <T> T find(T[] arr, Predicate<T> pred) {
        Iterator<T> it = Arrays.stream(arr).iterator();
        return find(it, pred);
    }

    /**
     * Finds the first element in the given iterator that satisfies the provided predicate condition.
     *
     * @param iterator  The iterator to search for the element.
     * @param pred The predicate condition to filter elements.
     * @param <T>       The type of elements in the iterator.
     * @return The first element that satisfies the predicate, or null if no such element is found.
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

    public static <T> T find(Iterable<T> iterable, T value) {
        Iterator<T> it = iterable.iterator();
        return find(it, value);
    }

    public static <T> boolean exists(T[] array, T value) {
        Iterator<T> it = Arrays.stream(array).iterator();
        return exists(it, value);
    }

    public static <T> boolean exists(Iterable<T> iterable, T value) {
        Iterator<T> it = iterable.iterator();
        return exists(it, value);
    }

    public static <T> boolean exists(Iterator<T> iterator, T value) {
        Objects.requireNonNull(value);
        Predicate<T> pred = value::equals;
        return exists(iterator, pred);
    }

    public static <T> boolean exists(Iterable<T> iterable, Predicate<T> pred) {
        Iterator<T> it = iterable.iterator();
        return exists(it, pred);
    }

    public static <T> boolean exists(T[] array, Predicate<T> pred) {
        Iterator<T> it = Arrays.stream(array).iterator();
        return exists(it, pred);
    }

    // Methods for checking existence.

    /**
     * Checks if a value exists in an iterator.
     *
     * @param iterator The iterator to check.
     * @param pred    The function to check for existence.
     * @param <T>      The type of elements in the iterator.
     * @return True if the value exists in the iterator; otherwise, false.
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

    // Methods for pagination.

    public static <T> List<T> paginate(T[] arr, int page, int pagesize, Predicate<T> pred) {
        Iterator<T> i = Arrays.stream(arr).iterator();
        return paginate(i, page, pagesize, pred);
    }

    public static <T> List<T> paginate(Iterable<T> iterable, int page, int pagesize, Predicate<T> pred) {
        Iterator<T> i = iterable.iterator();
        return paginate(i, page, pagesize, pred);
    }

    /**
     * Paginates elements in an iterator based on a predicate condition.
     *
     * @param iterator  The iterator to paginate.
     * @param page      The page number (zero-based) to start pagination.
     * @param pagesize  The number of elements per page.
     * @param pred The predicate condition for pagination.
     * @param <T>       The type of elements in the iterator.
     * @return A list containing elements that match the pagination criteria.
     */
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
