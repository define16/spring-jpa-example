package com.example.springboot3jpa.application.controller;

import com.example.springboot3jpa.application.dto.MemberRequest;
import com.example.springboot3jpa.application.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/sign-up")
    public String signUp(@RequestBody MemberRequest.SignUp signUpDto) {
        if (memberService.signUp(signUpDto)) return "ok";
        else return "failed";
    }

}
