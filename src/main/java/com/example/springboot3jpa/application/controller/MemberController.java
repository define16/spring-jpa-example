package com.example.springboot3jpa.application.controller;

import com.example.springboot3jpa.application.requests.MemberRequestModels;
import com.example.springboot3jpa.application.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController  // 리턴값에 자동으로 @ResponseBody 붙는다.
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {
    private final MemberService memberService;

    @GetMapping()
    public HashMap<String, Object> getMemberInfo() throws Exception {
        HashMap<String, Object> response = new HashMap<String, Object>();
        response.put("message", "Success");
        return response;
    }

}
