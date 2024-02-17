package com.example.springboot3jpa.domain.member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity(name="follower")
@RequiredArgsConstructor
public class Follower {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private final String userId;

    @Column
    @NonNull
    private String userName;

    @ManyToOne
    private MemberAggregate member;

}
