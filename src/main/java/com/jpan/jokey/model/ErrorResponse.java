package com.jpan.jokey.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private boolean error = false;
    private Boolean internalError;
    private String code;
    private String message;
    private List<String> causedBy;
    private String additionalInfo;
}
