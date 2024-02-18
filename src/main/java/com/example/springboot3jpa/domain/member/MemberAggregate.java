package com.example.springboot3jpa.domain.member;

import com.example.springboot3jpa.domain.shared.MemberRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity(name = "member")
@Table(indexes = {
        @Index(name = "idx_userId", columnList = "userId", unique = true),
        @Index(name = "idx_userName_userId", columnList = "userName, userId")
})
@NoArgsConstructor  // No default constructor for entity
@AllArgsConstructor
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

    private MemberRole role;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Follower> followers;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public MemberAggregate(String userId, String userName, String passwordValue, String introduction){
        this.userId = userId;
        this.userName = userName;
        this.password = Password.builder().value(passwordValue).build();
        this.introduction = introduction;
    }

    public MemberAggregate(String userId, String userName, String passwordValue){
        this.userId = userId;
        this.userName = userName;
        this.password = Password.builder().value(passwordValue).build();
    }

    @Override
    public int hashCode() {
        return this.id.intValue();
    }

    @Override
    public boolean equals(Object obj) {
        return this.hashCode() == obj.hashCode() && obj instanceof MemberAggregate;
    }

}
