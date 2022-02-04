package org.ua.uesf.model.dto;

import lombok.Builder;
import lombok.Value;

import java.time.Instant;

@Value
@Builder(setterPrefix = "with")
public class NewsDTO {

    String id;
    String titleUA;
    String titleEN;
    String game;
    String contentUA;
    String contentEN;
    String shortDescriptionUA;
    String shortDescriptionEN;
    String newsStatus;
    Instant creationDate;
    Instant updateDate;

}
