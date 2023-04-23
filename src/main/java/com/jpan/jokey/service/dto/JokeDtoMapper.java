package com.jpan.jokey.service.dto;

import com.jpan.jokey.model.Joke;

public abstract class JokeDtoMapper {
    public static JokeDto mapFrom(final Joke joke) {
        return joke == null ? null : new JokeDto(joke.getId(), joke.getJoke());
    }
}
