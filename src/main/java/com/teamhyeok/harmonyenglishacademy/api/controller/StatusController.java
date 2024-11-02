package com.teamhyeok.harmonyenglishacademy.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/")
@RestController
public class StatusController {

    @GetMapping("/health-check")
    public ResponseEntity<String> healthCheck() {

        return ResponseEntity.ok().build();
    }

}
