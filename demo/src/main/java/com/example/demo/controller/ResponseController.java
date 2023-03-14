package com.example.demo.controller;


import com.example.demo.model.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ResponseController {

    @PostMapping
    Response test(@RequestBody Response response) {
        System.out.println(response.getHeader().getType());
        System.out.println(response.getClass().getName());
        return response;
    }
}
