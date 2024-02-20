package com.example.springboot3jpa.application.dto;

import com.example.springboot3jpa.domain.member.Password;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class MemberRequest {

    @Getter
    @Setter
    @NoArgsConstructor // deserialize 할때 필요하다
    public static class SignUp {
        private String userId;
        private String userName;
        private String password;
        private String introduction;

        public SignUp(String userId, String userName, String password, String introduction) {
            this.userId = userId;
            this.userName = userName;
            this.password = password;
            this.introduction = introduction;
        }
    }
}
