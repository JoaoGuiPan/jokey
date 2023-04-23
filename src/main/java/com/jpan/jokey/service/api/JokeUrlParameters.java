package com.jpan.jokey.service.api;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;

import static com.jpan.jokey.model.Constants.DEFAULT_LANG;
import static com.jpan.jokey.model.Constants.MAX_AMOUNT;
import static com.jpan.jokey.model.JokeFlag.*;
import static com.jpan.jokey.model.JokeType.single;

/**
 * URL Parameters listed at https://jokeapi.dev/#url-parameters
 * Not all parameters were included in the interest of time and requirements
 */
@Data
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JokeUrlParameters {

    @JsonAlias("safe-mode")
    private String safeMode = StringUtils.EMPTY;

    // https://jokeapi.dev/#amount-param
    private final int amount = MAX_AMOUNT;

    // https://jokeapi.dev/#lang
    @NotBlank
    private final String lang = DEFAULT_LANG;

    // https://jokeapi.dev/#type-param
    private final String type = single.name();

    // https://jokeapi.dev/#flags-param
    @NotBlank
    private String blacklistFlags = group(explicit,sexist);

    public JokeUrlParameters(final boolean safeMode, final String blacklistFlags) {
        this.safeMode = safeMode ? StringUtils.EMPTY : null;
        this.blacklistFlags = blacklistFlags;
    }

    public void setSafeMode(final boolean safeMode) {
        this.safeMode = safeMode ? StringUtils.EMPTY : null;
    }
}
