package com.example.springboot3jpa.application.service;

import com.example.springboot3jpa.application.requests.MemberRequestModels;
import com.example.springboot3jpa.domain.member.MemberAggregate;
import com.example.springboot3jpa.domain.member.MemberDto;
import com.example.springboot3jpa.domain.shared.MemberRole;
import com.example.springboot3jpa.infrastructure.repositorty.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberRequestModels.Response signIn(String userId, String password){
        Optional<MemberAggregate>  member = memberRepository.findByUserId(userId);
        if (member.isEmpty()) return MemberRequestModels.Response.builder().isSuccess(false).message("아이디를 찾을 수 없습니다.").build();

        return MemberRequestModels.Response.builder().isSuccess(member.get().getPassword().isMatched(password)).message(member.get().getPassword().isMatched(password) ? "성공":"비밀번호가 틀렸습니다.").build();
    }

    public MemberRequestModels.Response signUp(MemberRequestModels.JoinRequest request){
        Optional<MemberAggregate> member = memberRepository.findByUserId(request.getUserId());
        if(member.isPresent()) {
            return MemberRequestModels.Response.builder().isSuccess(false).message("이미 등록된 아이디 입니다.").build();
        }
        System.out.println(request.getRole().toUpperCase());
        System.out.println(MemberRole.valueOf(request.getRole().toUpperCase()));
        MemberAggregate memberAggregate = MemberDto.SignUpDomainService.builder()
                .userId(request.getUserId())
                .userName(request.getUserName())
                .password(request.getPassword())
                .introduction(request.getIntroduction())
                .role(MemberRole.valueOf(request.getRole().toUpperCase()))
                .build().toEntity();
        memberRepository.save(memberAggregate);

        return MemberRequestModels.Response.builder().isSuccess(true).message("성공").build();
    }

    public Page<MemberAggregate> retrieveMembers(int page) {
        int pageSize = 10;
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        return memberRepository.findAll(pageRequest);
    }
}
