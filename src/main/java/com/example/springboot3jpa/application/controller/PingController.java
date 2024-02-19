package com.example.springboot3jpa.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.DateTimeAtCompleted;
import java.time.LocalDateTime;
import java.util.HashMap;

@RestController
@RequestMapping()
public class PingController {

    @ResponseBody
    @GetMapping()
    public HashMap<String, Object> ping() throws Exception{
        HashMap<String, Object> response  = new HashMap<String, Object>();
        response.put("message", "Success");
        response.put("time", LocalDateTime.now().toString());
        return response;
    }
}
