package com.jpan.jokey.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JokeFlagsResponse {
    private boolean nsfw = true;
    private boolean religious = true;
    private boolean political = true;
    private boolean racist = true;
    private boolean sexist = true;
    private boolean explicit = true;
}
