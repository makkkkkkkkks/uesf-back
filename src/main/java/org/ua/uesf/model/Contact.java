package org.ua.uesf.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String picture;
    private String name;
    private String businessPosition;
    private String facebookUrl;
    private String twitterUrl;
    private String personalInformation;
}
