package com.jpan.jokey.model;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Joke Categories listed at https://jokeapi.dev/#categories
 */
public enum JokeCategory {
    Any, Misc, Programming, Dark, Pun, Spooky, Christmas;

    public static String group(final JokeCategory...categories) {

        // category "Any" shouldn't be combined with other categories
        if (Arrays.asList(categories).contains(Any)) {
            return Any.name();
        }

        return Arrays.stream(categories).map(Enum::name).collect(Collectors.joining(","));
    }
}
