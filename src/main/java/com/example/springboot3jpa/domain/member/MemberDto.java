package com.example.springboot3jpa.domain.member;

import com.example.springboot3jpa.domain.shared.MemberRole;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

/**
 * 도메인은 서비스 계층에서 DTO로 변환되어 컨트롤러로 전달되어야 한다.
 */
public class MemberDto {

    @Getter
    public static class SignUpDomainService {
        @NotEmpty
        private String userId;

        @NotEmpty
        private String userName;

        @NotEmpty
        private String password;

        private String introduction;

        @NotEmpty
        private MemberRole role;


        @Builder
        public SignUpDomainService(String userId, String userName, String password, String introduction, MemberRole role) {
            this.userId = userId;
            this.userName = userName;
            this.password = password;
            this.introduction = introduction;
            this.role = role;

        }

        public MemberAggregate toEntity() {
            if (introduction == null)  return new MemberAggregate(this.userId, this.userName, this.password, this.role);
            else return new MemberAggregate(this.userId, this.userName, this.password, this.introduction, this.role);

        }
    }
}
