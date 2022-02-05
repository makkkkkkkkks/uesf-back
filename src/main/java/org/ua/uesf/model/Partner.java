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
    Long id;
    String imageUA;
    String imageEN;
    String link;
    String orderNumber;
    String titleUA;
    String titleEN;

}
