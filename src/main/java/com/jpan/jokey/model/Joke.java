package com.jpan.jokey.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Joke implements Comparable<Joke> {

    private long id = 0;
    private String category;
    private String type;
    private String joke;
    private String setup;
    private String delivery;
    private JokeFlagsResponse flags;
    private boolean safe = false;
    private String lang;

    public Joke(final long id, final String joke) {
        this.id = id;
        this.joke = joke;
    }

    @Override
    public int compareTo(final Joke o) {
        return this.getJoke().length() - o.getJoke().length();
    }
}
