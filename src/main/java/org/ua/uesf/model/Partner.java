package org.ua.uesf.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private  String imageUA;
    private  String imageEN;
    private String link;
    private String orderNumber;
    private String titleUA;
    private String titleEN;

}
