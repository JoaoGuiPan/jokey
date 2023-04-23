package com.jpan.jokey.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "jokeapi.config")
public class JokeConfig {

    private boolean safe = false;
    private Map<String, Boolean> flags = new HashMap<>();

    public JokeConfig() {
    }

    public JokeConfig(final boolean safe, final Map<String, Boolean> flags) {
        this.safe = safe;
        this.flags = flags;
    }

    public boolean isSafe() {
        return safe;
    }

    public void setSafe(final boolean safe) {
        this.safe = safe;
    }

    public Map<String, Boolean> getFlags() {
        return flags;
    }

    public void setFlags(final Map<String, Boolean> flags) {
        this.flags = flags;
    }
}
