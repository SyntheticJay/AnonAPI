package me.jay.exception;

/**
 * @author Jay, 2018
 *
 * Thrown when the get request fails.
 */
public class GetRequestException extends RuntimeException {
    public GetRequestException() {
        super("Failed to respond with a 'get' request!");
    }
}
