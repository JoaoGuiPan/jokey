package com.jpan.jokey.exception;

public class InvalidJokeFlagConfigException extends RuntimeException {
    public InvalidJokeFlagConfigException(final String message) {
        super("Invalid flag config name!\n " + message);
    }
}
