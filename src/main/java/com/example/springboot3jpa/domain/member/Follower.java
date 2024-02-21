package com.example.springboot3jpa.domain.member;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity(name="follower")
@RequiredArgsConstructor
@NoArgsConstructor
public class Follower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NonNull
    private String userId;

    @Column
    @NonNull
    private String userName;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberAggregate member;

}
