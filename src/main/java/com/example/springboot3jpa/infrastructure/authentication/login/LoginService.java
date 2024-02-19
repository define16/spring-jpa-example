package com.example.springboot3jpa.infrastructure.authentication.login;

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
public class LoginService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MemberAggregate> member = memberRepository.findByUserId(username);
        if (member.isEmpty()) return null;

        return new CustomUserDetails(member.get());
    }
}
