package com.geekster.InstagramProject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.stream.Location;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @Column(nullable = false)
    private LocalDateTime createdDate;


    @Column(nullable = false)
    @NotEmpty
    private String postData;

    private String postCaption;


    private String location;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false , name = "fk_user_ID")
    private User user;

}
