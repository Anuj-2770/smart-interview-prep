package com.smart.interview.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final WebClient webClient;

    public GeminiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("https://generativelanguage.googleapis.com")
                .build();
    }
    
    public String evaluateAnswer(String question, String answer) {
        String prompt = "Question: " + question + "\n"
                + "Student Answer: " + answer + "\n"
                + "Evaluate this answer and give: \n"
                + "1. Score out of 10\n"
                + "2. What was correct\n"
                + "3. What was missing\n"
                + "4. Ideal answer in brief";

        Map<String, Object> requestBody = Map.of(
                "contents", List.of(
                        Map.of("parts", List.of(
                                Map.of("text", prompt)
                        ))
                )
        );

        try {
            return webClient.post()
                    .uri("/v1beta/models/gemini-2.0-flash:generateContent?key=" + apiKey)
                    .header("Content-Type", "application/json")
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    public String generateQuestions(String topic) {
        String prompt = "Generate exactly 10 interview questions for topic: "
                + topic
                + ". Mix of theoretical and practical questions. "
                + "Number them 1-10. Keep each question clear and concise.";

        Map<String, Object> requestBody = Map.of(
                "contents", List.of(
                        Map.of("parts", List.of(
                                Map.of("text", prompt)
                        ))
                )
        );

        String response = webClient.post()
        		.uri("/v1beta/models/gemini-2.0-flash:generateContent?key=" + apiKey)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return response;
    }
}
