package com.jpan.jokey.exception;

public class InvalidJokeResponseException extends RuntimeException {
    public InvalidJokeResponseException() {
        super("Invalid Joke Response!");
    }
}
