package org.ua.uesf.model.dto;

import lombok.Value;
import org.ua.uesf.model.Game;

import java.time.Instant;
import java.util.Set;

@Value
public class GeneralNewsDTO {

    String id;
    String title;
    Set<String> game;
    String content;
    String shortDescription;
    String newsStatus;
    String img;
    Instant creationDate;
    Instant updateDate;

}
