package com.edgrantJBusRD.controller;

/**
 * The BaseResponse class represents a generic response structure used for
 * communicating results in a RESTful API or other similar scenarios.
 *
 * @param <T> The type of payload data to include in the response.
 */
public class BaseResponse<T> {
    /**
     * Indicates whether the operation was successful.
     */
    public boolean success;

    /**
     * A message providing additional information about the response.
     */
    public String message;

    /**
     * The payload data included in the response.
     */
    public T payload;

    /**
     * Creates a new BaseResponse instance.
     *
     * @param success Indicates whether the operation was successful.
     * @param message A message providing additional information about the response.
     * @param payload The payload data included in the response.
     */
    public BaseResponse(boolean success, String message, T payload) {
        this.success = success;
        this.message = message;
        this.payload = payload;
    }
}
