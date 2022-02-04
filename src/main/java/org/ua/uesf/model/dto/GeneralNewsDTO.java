package org.ua.uesf.model.dto;

import lombok.Value;

import java.time.Instant;

@Value
public class GeneralNewsDTO {

    String id;
    String title;
    String game;
    String content;
    String shortDescription;
    String newsStatus;
    String img;
    Instant creationDate;
    Instant updateDate;

}
