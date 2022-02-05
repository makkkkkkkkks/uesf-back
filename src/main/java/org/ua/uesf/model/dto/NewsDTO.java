package org.ua.uesf.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.time.Instant;

@Data
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
    String imgUA;
    String imgEN;
    Instant creationDate;
    Instant updateDate;

}
