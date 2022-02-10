package org.ua.uesf.model;

import lombok.Data;

import java.util.Set;

@Data
public class GameDto {

    private Long id;
    private String img;
    private String title;
    private Set<Long> news;

}
