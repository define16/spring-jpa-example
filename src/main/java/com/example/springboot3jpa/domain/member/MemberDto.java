package com.example.springboot3jpa.domain.member;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class MemberDto {

    @Builder
    public static class SignUp {
        private String userId;
        private String userName;
        private String password;
        private String introduction;
        private String role;

        public MemberAggregate toEntity() {
            return new MemberAggregate(userId, userName, password, introduction, role);
        }
    }
}
