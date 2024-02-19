package com.example.springboot3jpa.application.controller;

import com.example.springboot3jpa.application.requests.MemberRequestModels;
import com.example.springboot3jpa.application.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final MemberService memberService;

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/join")
    public String joinPage(){
        return "join";
    }

    @GetMapping("/seller")
    public String sellerPage(){
        return "seller";
    }


    @PostMapping("/sign-up")
    @ResponseBody
    public HashMap<String, Object> signUp(MemberRequestModels.JoinRequest request) throws Exception {
        HashMap<String, Object> responseBody = new HashMap<String, Object>();
        MemberRequestModels.Response result = memberService.signUp(request);
        responseBody.put("message", result.getMessage());
        return responseBody;
    }
}
