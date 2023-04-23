package com.jpan.jokey.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpan.jokey.config.JokeConfig;
import com.jpan.jokey.exception.InvalidJokeFlagConfigException;
import com.jpan.jokey.exception.InvalidJokeResponseException;
import com.jpan.jokey.model.Joke;
import com.jpan.jokey.model.JokeResponse;
import com.jpan.jokey.service.api.JokeApiClient;
import com.jpan.jokey.service.api.JokeUrlParameters;
import com.jpan.jokey.service.dto.JokeDto;
import io.micrometer.core.instrument.util.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jpan.jokey.model.JokeCategory.Any;
import static com.jpan.jokey.model.JokeFlag.explicit;
import static com.jpan.jokey.model.JokeFlag.sexist;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.util.Assert.isTrue;

class JokeServiceUnitTest {

    final JokeApiClient apiClient = mock(JokeApiClient.class);

    @Test
    void shouldGetRandomJoke() {
        final JokeUrlParameters queryMap = new JokeUrlParameters();
        final JokeResponse validJokeResponse = getValidJokeResponse();
        when(apiClient.getJokes(Any.name(), queryMap)).thenReturn(validJokeResponse);

        final JokeConfig config = getDefaultConfig();

        final JokeService jokeService = new JokeService(config, apiClient);

        final JokeDto randomJoke = jokeService.getRandomJoke();

        isTrue(randomJoke.getId() == 271, "Id must be 271");
    }

    @Test
    void shouldThrowInvalidJokeFlagConfigException() {
        final JokeUrlParameters queryMap = new JokeUrlParameters();
        final JokeResponse validJokeResponse = getValidJokeResponse();
        when(apiClient.getJokes(Any.name(), queryMap)).thenReturn(validJokeResponse);

        final JokeConfig config = getInvalidFlagConfig();

        final JokeService jokeService = new JokeService(config, apiClient);

        final RuntimeException exception = assertThrows(InvalidJokeFlagConfigException.class, jokeService::getRandomJoke);

        isTrue("Invalid flag config name!\n No enum constant com.jpan.jokey.model.JokeFlag.INVALID_CFG".equals(exception.getMessage()), "InvalidJokeFlagConfigException must be thrown");
    }

    @Test
    void shouldThrowInvalidJokeResponseException() {
        final JokeUrlParameters queryMap = new JokeUrlParameters();
        when(apiClient.getJokes(Any.name(), queryMap)).thenReturn(new JokeResponse());

        final JokeConfig config = getDefaultConfig();

        final JokeService jokeService = new JokeService(config, apiClient);

        final RuntimeException exception = assertThrows(InvalidJokeResponseException.class, jokeService::getRandomJoke);

        isTrue("Invalid Joke Response!".equals(exception.getMessage()), "InvalidJokeResponseException must be thrown");
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

    private static JokeConfig getInvalidFlagConfig() {
        final JokeConfig jokeConfig = new JokeConfig();
        final Map<String, Boolean> flags = new HashMap<>();
        flags.put("INVALID_CFG", false);
        jokeConfig.setFlags(flags);
        return jokeConfig;
    }

    private JokeResponse getValidJokeResponse() {

        final List<Joke> jokes = new ArrayList<>();
        jokes.add(new Joke(0, "I've got a really good UDP joke to tell you but I don’t know if you'll get it."));
        jokes.add(new Joke(5, "A SQL statement walks into a bar and sees two tables.\nIt approaches, and asks \"may I join you?\""));
        jokes.add(new Joke(12, "// This line doesn't actually do anything, but the code stops working when I delete it."));
        jokes.add(new Joke(22, "If Bill Gates had a dime for every time Windows crashed ... Oh wait, he does."));
        jokes.add(new Joke(28, "Two C strings walk into a bar.\nThe bartender asks \"What can I get ya?\"\nThe first string says \"I'll have a gin and tonic.\"\nThe second string thinks for a minute, then says \"I'll take a tequila sunriseJF()#$JF(#)$(@J#()$@#())!*FNIN!OBN134ufh1ui34hf9813f8h8384h981h3984h5F!##@\"\nThe first string apologizes, \"You'll have to excuse my friend, he's not null-terminated.\""));
        jokes.add(new Joke(42, "Debugging is like being the detective in a crime movie where you're also the murderer at the same time."));
        jokes.add(new Joke(43, "How do you tell HTML from HTML5?\n- Try it out in Internet Explorer\n- Did it work?\n- No?\n- It's HTML5."));
        jokes.add(new Joke(220, "I was struggling to figure out how lightning works, but then it struck me."));
        jokes.add(new Joke(271, "Never date a baker. They're too kneady."));
        jokes.add(new Joke(306, "Yo mama is so old, she knew Burger King while he was still a prince."));

        return new JokeResponse(10, jokes);
    }
}