package com.example.springboot3jpa.domain.member;

import com.example.springboot3jpa.infrastructure.authentication.SecurityConfig;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

@Embeddable
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Password {
    @Column(name = "password", nullable = false)
    private String value;

    @Column
    private LocalDateTime expirationDate;

    @Column(nullable = false)
    private int failedCount;

    @Column(name = "passwordTtl", nullable = false)
    private long ttl;

    @Builder
    public Password(final String value){
        this.ttl = 1209_604;  // 1209_604(1,209,604)초는 14일이다.
        this.value = encodePassword(value);
        this.expirationDate = extendExpirationDate();
    }

    private String encodePassword(final String password) {
        return SecurityConfig.passwordEncoder().encode(password);
    }

    public boolean isMatched(final String rawPassword) {
        boolean matches = new BCryptPasswordEncoder().matches(rawPassword, this.value);
        updateFailedCount(matches);
        return matches;
    }

    private void updateFailedCount(boolean matches){
        if (matches) {
            failedCount = 0;
        } else {
            failedCount += 1;
        }
    }

    private LocalDateTime extendExpirationDate() {
        return LocalDateTime.now().plusSeconds(ttl);
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expirationDate);
    }

}
