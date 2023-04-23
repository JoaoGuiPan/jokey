package com.jpan.jokey.service.api;

import com.jpan.jokey.model.Joke;
import com.jpan.jokey.model.JokeResponse;

import java.util.ArrayList;
import java.util.List;

class JokeApiClientFallback implements JokeApiClient {

    @Override
    public JokeResponse getJokes(final String jokeCategory, final JokeUrlParameters queryMap) {

        final Joke joke = new Joke(
                1,
                "A SQL statement walks into a bar and sees two tables.\nIt approaches, and asks \"may I join you?\""
        );

        final List<Joke> jokes = new ArrayList<>();
        jokes.add(joke);

        return new JokeResponse(1, jokes);
    }
}
