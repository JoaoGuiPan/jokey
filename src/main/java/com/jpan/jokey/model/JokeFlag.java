package com.jpan.jokey.model;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Joke Flags listed at https://jokeapi.dev/#flags-param
 */
public enum JokeFlag {
    nsfw, religious, political, racist, sexist, explicit;

    public static String group(final JokeFlag...flags) {
        return Arrays.stream(flags).map(Enum::name).collect(Collectors.joining(","));
    }
}
