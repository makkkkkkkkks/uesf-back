package org.ua.uesf.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "user_table")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userName;
    private String firstName;
    private String lastName;
    private String img;

    @NotBlank
    @Pattern(regexp="[a-zA-Z0-9][a-zA-Z0-9-.]*@[a-zA-Z]+([.][a-zA-z]*)*")
    private String email;
    @NotBlank
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

}
