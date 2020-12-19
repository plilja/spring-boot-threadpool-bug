package com.example.threadpool;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
class HelloController {
    @GetMapping("/hello")
    String hello() {
        return "Hello World!";
    }
}
