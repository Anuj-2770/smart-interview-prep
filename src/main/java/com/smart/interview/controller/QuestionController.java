package com.smart.interview.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smart.interview.service.GeminiService;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final GeminiService geminiService;

    public QuestionController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @GetMapping("/generate")
    public String generateQuestions(@RequestParam String topic) {
        return geminiService.generateQuestions(topic);
    }
}
