package org.ua.uesf.exception.messages;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class ExceptionMessage {

    String path;
    String message;
    Instant date;

}

