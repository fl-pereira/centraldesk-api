package com.felipe.centraldesk.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TesteController {

    @GetMapping("/teste")
    public Map<String, String> testeJson() {
        return Map.of(
                "status", "ok",
                "projeto", "CentralDesk"
        );
    }
}