package com.jpan.jokey.service;

import com.jpan.jokey.config.JokeConfig;
import com.jpan.jokey.service.api.JokeApiClient;
import com.jpan.jokey.service.dto.JokeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static com.jpan.jokey.model.JokeFlag.explicit;
import static com.jpan.jokey.model.JokeFlag.sexist;
import static org.springframework.util.Assert.isTrue;

@SpringBootTest(properties = "spring.profiles.active:test")
public class JokeServiceIntegrationTest {

    @Autowired
    private JokeApiClient apiClient;

    @Test
    void shouldGetFallbackJokeResponse() {
        final JokeConfig config = getDefaultConfig();

        final JokeService jokeService = new JokeService(config, apiClient);

        final JokeDto randomJoke = jokeService.getRandomJoke();

        isTrue(randomJoke.getId() == 1, "Id must be 1");
    }

    private static JokeConfig getDefaultConfig() {
        final JokeConfig jokeConfig = new JokeConfig();
        jokeConfig.setSafe(true);

        final Map<String, Boolean> flags = new HashMap<>();
        flags.put(explicit.name(), false);
        flags.put(sexist.name(), false);

        jokeConfig.setFlags(flags);
        return jokeConfig;
    }
}
