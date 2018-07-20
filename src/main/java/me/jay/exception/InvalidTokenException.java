package me.jay.exception;

/**
 * @author Jay, 2018
 *
 * Fails when the token is invalid.
 */
public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException() {
        super("The token you provided was invalid and failed to respond with AnonFile!");
    }
}
