package com.example.springboot3jpa.infrastructure.authentication.login;

import com.example.springboot3jpa.domain.member.MemberAggregate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private final MemberAggregate memberAggregate;

    public CustomUserDetails(MemberAggregate memberAggregate) {
        this.memberAggregate = memberAggregate;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                String prefix = "ROLE_";
                System.out.println(prefix.concat(memberAggregate.getRole().name()));
                return prefix.concat(memberAggregate.getRole().name());
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
        return !memberAggregate.getPassword().isExpired();
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
