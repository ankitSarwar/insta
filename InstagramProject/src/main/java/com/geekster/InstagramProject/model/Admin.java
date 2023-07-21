package com.geekster.InstagramProject.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "InstagramAdmin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;
    @Column(nullable = false)
    @NotEmpty
    private String firstName;

    @Column(nullable = false)
    @NotEmpty
    private String lastName;

    @NotNull
    private String password;

    @Column(unique = true , nullable = false)
    @Email
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@admin\\.com$")
    private String email;

}
