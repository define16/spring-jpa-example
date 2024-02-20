package com.example.springboot3jpa.application.service;

import com.example.springboot3jpa.application.dto.MemberRequest;
import com.example.springboot3jpa.domain.member.MemberAggregate;
import com.example.springboot3jpa.domain.member.MemberDto;
import com.example.springboot3jpa.infrastructure.repositorty.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public boolean signUp(MemberRequest.SignUp signUpDto) {
        if (memberRepository.existsByUserId(signUpDto.getUserId())){
            return false;
        }

        MemberAggregate sign = MemberDto.SignUp.builder()
                .userId(signUpDto.getUserId())
                .userName(signUpDto.getUserName())
                .password(signUpDto.getPassword())
                .introduction(signUpDto.getIntroduction())
                .role("ROLE_ADMIN").build().toEntity();
        memberRepository.save(sign);
        return true;
    }
}
