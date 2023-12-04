package com.edgrantJBusRD;

/**
 * Represents a functional interface for defining a condition or criteria to evaluate objects of type T.
 *
 * @param <T> The type of objects that the predicate operates on.
 */
public interface Predicate<T> {

    /**
     * Evaluates the given condition or criteria on the provided value.
     *
     * @param value The value to be evaluated.
     * @return True if the condition is satisfied, false otherwise.
     */
    boolean predicate(T value);
}
