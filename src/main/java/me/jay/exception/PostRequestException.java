package me.jay.exception;

/**
 * @author Jay, 2018
 *
 * Thrown when the post request fails.
 */
public class PostRequestException extends RuntimeException {
    public PostRequestException() {
        super("Failed to respond with a 'post' request! (File doesn't exist? I dont know.) WTF!");
    }
}
