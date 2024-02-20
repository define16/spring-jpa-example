package com.example.springboot3jpa.infrastructure.authentication;

import com.example.springboot3jpa.domain.member.MemberAggregate;
import com.example.springboot3jpa.infrastructure.repositorty.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MemberAggregate> member = memberRepository.findByUserId(username);
        return member.isPresent() ? new CustomUserDetails(member.get()) : null;
    }
}
