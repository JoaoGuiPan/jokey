package com.jpan.jokey.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class JokeResponse extends ErrorResponse {

    private int amount = 0;
    private List<Joke> jokes = new ArrayList<>();

    public JokeResponse(final int amount, final List<Joke> jokes) {
        this.amount = amount;
        this.jokes = jokes;
    }
}
