package com.geekster.InstagramProject.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
public class AdminAuthenticationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;
    private String token;
    private LocalDate tokenCreationDate;

    @OneToOne
    @JoinColumn(nullable = false , name = "fk_user_ID")
    private Admin admin;

    public AdminAuthenticationToken(Admin admin) {
        this.admin = admin;
        this.tokenCreationDate = LocalDate.now();
        this.token = UUID.randomUUID().toString();
    }
}
