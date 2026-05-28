package com.smart.interview.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smart.interview.service.GeminiService;

@RestController
@RequestMapping("/api/answer")
public class AnswerController {

    private final GeminiService geminiService;

    public AnswerController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @PostMapping("/evaluate")
    public String evaluateAnswer(Authentication authentication,
                                  @RequestParam String question,
                                  @RequestParam String answer) {
        return geminiService.evaluateAnswer(question, answer);
    }
}
