package com.example.springboot3jpa.infrastructure.authentication;

import com.example.springboot3jpa.domain.member.MemberAggregate;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {
    private final MemberAggregate memberAggregate;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {

            @Override
            public String getAuthority() {
                return memberAggregate.getRole();
            }
        });
        return collection;
    }

    @Override
    public String getPassword() {
        return memberAggregate.getPassword().getValue();
    }

    @Override
    public String getUsername() {
        return memberAggregate.getUserId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
