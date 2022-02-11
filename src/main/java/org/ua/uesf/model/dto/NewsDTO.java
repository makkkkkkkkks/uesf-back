package org.ua.uesf.model.dto;

import lombok.Builder;
import lombok.Data;
import org.ua.uesf.model.Game;

import java.time.Instant;
import java.util.Set;

@Data
@Builder(setterPrefix = "with")
public class NewsDTO {

    private String id;
    private String titleUA;
    private String titleEN;
    private Set<String> game;
    private String contentUA;
    private String contentEN;
    private String shortDescriptionUA;
    private String shortDescriptionEN;
    private String newsStatus;
    private String imgUA;
    private String imgEN;
    private Instant creationDate;
    private Instant updateDate;

}
