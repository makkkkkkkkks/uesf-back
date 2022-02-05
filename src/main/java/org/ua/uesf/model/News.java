package org.ua.uesf.model;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String titleUA;
    String titleEN;
    String game;
    String contentUA;
    String contentEN;
    String shortDescriptionUA;
    String shortDescriptionEN;
    String imgUA;
    String imgEN;

    @Enumerated(EnumType.STRING)
    NewsStatus newsStatus;
    Instant creationDate;
    Instant updateDate;

}
