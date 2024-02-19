package com.example.springboot3jpa.application.requests;

import lombok.*;

public class MemberRequestModels {
    @Getter
    @Setter
    @Builder
    public static class Response{
        private boolean isSuccess;
        private String message;
    }

    @Getter
    @Setter
//    @NoArgsConstructor
    public static class JoinRequest{
        private String userId;
        private String userName;
        private String password;
        private String introduction;
        private String role;
    }

}
