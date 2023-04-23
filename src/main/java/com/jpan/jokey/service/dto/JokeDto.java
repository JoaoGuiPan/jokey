package com.jpan.jokey.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JokeDto {
    private long id = 0;
    private String joke;
}
