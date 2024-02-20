package com.example.springboot3jpa.application.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Iterator;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping()
    public String admin(){

        // SecurityContextHolder에 정보가 있다.
        String name = SecurityContextHolder.getContext().getAuthentication().getName(); // 일시적이지만 session이 있긴함 그래서 사용이 가능하다

        // Role 정보 확인
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();

        return "admin : " + name + role ;
    }
}
