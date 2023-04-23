package com.jpan.jokey.controller;

import com.jpan.jokey.exception.InvalidJokeFlagConfigException;
import com.jpan.jokey.exception.InvalidJokeResponseException;
import com.jpan.jokey.service.JokeService;
import com.jpan.jokey.service.dto.JokeDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(JokeController.class)
class JokeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private JokeService jokeService;

    @Test
    void shouldGetRandomJoke() throws Exception {

        given(jokeService.getRandomJoke()).willReturn(getValidJoke());

        mvc.perform(get("/jokes/random").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(
                        "$.id",
                        is(0)
                ))
                .andExpect(jsonPath(
                        "$.joke",
                        is("I've got a really good UDP joke to tell you but I don’t know if you'll get it.")
                ));
    }

    @Test
    void shouldThrowInvalidJokeFlagConfigException() throws Exception {
        given(jokeService.getRandomJoke())
                .willThrow(new InvalidJokeFlagConfigException("No enum constant com.jpan.jokey.model.JokeFlag.INVALID_CFG"));

        mvc.perform(get("/jokes/random").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath(
                        "$.message",
                        is("Invalid flag config name!\n No enum constant com.jpan.jokey.model.JokeFlag.INVALID_CFG")
                ));
    }

    @Test
    void shouldThrowInvalidJokeResponseException() throws Exception {
        given(jokeService.getRandomJoke()).willThrow(new InvalidJokeResponseException());

        mvc.perform(get("/jokes/random").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath(
                        "$.message",
                        is("Invalid Joke Response!")
                ));
    }

    private JokeDto getValidJoke() {
        return new JokeDto(0, "I've got a really good UDP joke to tell you but I don’t know if you'll get it.");
    }
}