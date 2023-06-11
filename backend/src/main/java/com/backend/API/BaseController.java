package com.backend.API;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("String Test");
    }

}