package com.example.springboot3jpa.application.controller;

import com.example.springboot3jpa.application.requests.MemberRequestModels;
import com.example.springboot3jpa.application.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

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

    @GetMapping("/home")
    public String homePage(Model model){
        String id = SecurityContextHolder.getContext().getAuthentication().getName();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();
        // role을 얻어서 특정한 로직을 추가할 수 있음

        model.addAttribute("id", id);
        model.addAttribute("role", role);

        return "home";
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
