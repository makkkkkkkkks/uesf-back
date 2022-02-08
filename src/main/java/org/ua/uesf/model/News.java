package org.ua.uesf.model;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private   String titleUA;
    private  String titleEN;
    private  String game;
    private  String contentUA;
    private String contentEN;
    private String shortDescriptionUA;
    private  String shortDescriptionEN;
    private  String imgUA;
    private  String imgEN;

    @Enumerated(EnumType.STRING)
    private  NewsStatus newsStatus;
    private  Instant creationDate;
    private  Instant updateDate;

}


