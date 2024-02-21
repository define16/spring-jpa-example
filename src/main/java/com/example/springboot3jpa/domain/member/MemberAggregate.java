package com.example.springboot3jpa.domain.member;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity(name = "member")
@Table(indexes = {
        @Index(name = "idx_userId", columnList = "userId", unique = true),
        @Index(name = "idx_userName_userId", columnList = "userName, userId")
})
@NoArgsConstructor  // No default constructor for entity
@EntityListeners(AuditingEntityListener.class)
public class MemberAggregate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String userId;

    @Column(length = 200)
    @NonNull
    private String userName;

    @Embedded  // 엔티티 클래스 내에 다른 엔티티를 포함하는데 사용
    @NonNull
    private Password password;

    @Column(columnDefinition = "TEXT")
    private String introduction;


    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private Set<Follower> followers = new HashSet<>();

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(length = 50)
    private String role;

    public MemberAggregate(String userId, String userName, String passwordValue, String introduction, String role){
        this.userId = userId;
        this.userName = userName;
        this.password = Password.builder().value(passwordValue).build();
        this.introduction = introduction;
        this.role = role;
    }

    public MemberAggregate(String userId, String userName, String passwordValue, String role){
        this.userId = userId;
        this.userName = userName;
        this.password = Password.builder().value(passwordValue).build();
        this.role = role;
    }

    @Override
    public int hashCode() {
        return this.id.intValue();
    }

    @Override
    public boolean equals(Object obj) {
        return this.hashCode() == obj.hashCode() && obj instanceof MemberAggregate;
    }


    public void addFollower(Follower follower) {
        follower.setMember(this);
        followers.add(follower);
    }
}
