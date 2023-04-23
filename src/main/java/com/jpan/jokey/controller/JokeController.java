package com.jpan.jokey.controller;

import com.jpan.jokey.controller.dto.ErrorResponseDto;
import com.jpan.jokey.exception.InvalidJokeFlagConfigException;
import com.jpan.jokey.exception.InvalidJokeResponseException;
import com.jpan.jokey.service.JokeService;
import com.jpan.jokey.service.dto.JokeDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("jokes")
@RequiredArgsConstructor
public class JokeController {

    private final Logger logger = LoggerFactory.getLogger(JokeController.class);

    private final JokeService jokeService;

    @GetMapping("/random")
    public JokeDto getRandomJoke() {
        this.logger.info("Generating Random Joke.");
        final JokeDto randomJoke = this.jokeService.getRandomJoke();
        this.logger.info("Random joke generated! - id: " + randomJoke.getId());
        return randomJoke;
    }

    @ExceptionHandler({ InvalidJokeFlagConfigException.class, InvalidJokeResponseException.class, Exception.class })
    public ResponseEntity<ErrorResponseDto> handleCustomExceptions(final Exception exception) {
        logger.error(exception.getMessage(), exception);
        return new ResponseEntity<>(new ErrorResponseDto(exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
