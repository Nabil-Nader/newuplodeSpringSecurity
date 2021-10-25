package com.example.mydomain.security;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Setter
@Getter

@NoArgsConstructor
@Builder
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;



    public Role(Long id, String role) {
        this.id = id;
        this.role = role;
    }
}
