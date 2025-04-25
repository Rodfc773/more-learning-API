package com.br.rodrigofc.more_learning_API.users;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/")
    public ResponseEntity<Object> getUser(){
        return ResponseEntity.ok().body("Hello World");
    }
}
