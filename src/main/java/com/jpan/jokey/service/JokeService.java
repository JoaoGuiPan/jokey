package com.jpan.jokey.service;

import com.jpan.jokey.config.JokeConfig;
import com.jpan.jokey.exception.InvalidJokeFlagConfigException;
import com.jpan.jokey.exception.InvalidJokeResponseException;
import com.jpan.jokey.model.Joke;
import com.jpan.jokey.model.JokeCategory;
import com.jpan.jokey.model.JokeFlag;
import com.jpan.jokey.model.JokeResponse;
import com.jpan.jokey.service.api.JokeApiClient;
import com.jpan.jokey.service.api.JokeUrlParameters;
import com.jpan.jokey.service.dto.JokeDto;
import com.jpan.jokey.service.dto.JokeDtoMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.jpan.jokey.model.JokeCategory.Any;

/**
 * Given an array of jokes taken from the external API, return the shortest one.
 * @see com.jpan.jokey.service.api.JokeApiClient
 * Jokes being returned should be safe to display and are not sexist or explicit.
 * @see com.jpan.jokey.config.JokeConfig
 */
@Service
@RequiredArgsConstructor
public class JokeService {

    private final Logger logger = LoggerFactory.getLogger(JokeService.class);

    private final JokeConfig config;

    private final JokeApiClient apiClient;

    public JokeDto getRandomJoke() {
        final String blacklistFlags = getBlacklistFlags();

        final JokeUrlParameters queryMap = new JokeUrlParameters(this.config.isSafe(), blacklistFlags);

        logger.info("Calling external API with parameters: " + queryMap);

        final JokeResponse response = this.apiClient.getJokes(Any.name(), queryMap);

        logger.debug("Joke response: " + response.getJokes());

        final Joke joke = getShortestJoke(response);

        logger.info("Shortest joke is: " + joke.getJoke());

        return JokeDtoMapper.mapFrom(joke);
    }

    private Joke getShortestJoke(final JokeResponse response) {
        logger.debug("Looking for shortest joke");
        return response.getJokes().stream().sorted().findFirst().orElseThrow(InvalidJokeResponseException::new);
    }

    private String getBlacklistFlags() {

        final List<String> blacklistFlags = new ArrayList<>();

        final Map<String, Boolean> configFlags = this.config.getFlags();

        this.logger.debug("Checking enabled joke flags - " + configFlags);

        try {
            configFlags.keySet().forEach(flag -> {
                final boolean isFlagEnabled = BooleanUtils.isTrue(configFlags.get(flag));
                JokeFlag.valueOf(flag); // check if config flag name is valid to prevent misconfiguration
                if (!isFlagEnabled) {
                    blacklistFlags.add(flag);
                }
            });
        } catch (final IllegalArgumentException exception) {
            throw new InvalidJokeFlagConfigException(exception.getMessage());
        }

        this.logger.debug("Enabled joke flags are: " + blacklistFlags);

        return String.join(",", blacklistFlags);
    }
}
