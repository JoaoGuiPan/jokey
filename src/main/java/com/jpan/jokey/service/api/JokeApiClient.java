package com.jpan.jokey.service.api;

import com.jpan.jokey.model.JokeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "jokeapi", url = "${jokeapi.url}", fallback = JokeApiClientFallback.class)
public interface JokeApiClient {

    @RequestMapping(method = RequestMethod.GET, value = "/joke/{jokeCategory}")
    JokeResponse getJokes(@PathVariable String jokeCategory, @SpringQueryMap JokeUrlParameters queryMap);
}
