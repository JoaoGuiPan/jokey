package com.jpan.jokey.service.api;

import com.jpan.jokey.model.Joke;
import com.jpan.jokey.model.JokeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class JokeApiClientFallback implements JokeApiClient {

    private final Logger logger = LoggerFactory.getLogger(JokeApiClientFallback.class);

    @Override
    public JokeResponse getJokes(final String jokeCategory, final JokeUrlParameters queryMap) {

        this.logger.error("Error calling external API! Executing Fallback Response.");

        final Joke joke = new Joke(
                1,
                "A SQL statement walks into a bar and sees two tables.\nIt approaches, and asks \"may I join you?\""
        );

        final List<Joke> jokes = new ArrayList<>();
        jokes.add(joke);

        this.logger.info("Joke Response is: " + joke.getJoke());

        return new JokeResponse(1, jokes);
    }
}
