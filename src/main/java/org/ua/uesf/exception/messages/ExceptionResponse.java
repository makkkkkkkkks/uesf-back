package org.ua.uesf.exception.messages;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class ExceptionResponse {

    private String errorMessage;
    private String requestedURI;
    private Instant date;
}
