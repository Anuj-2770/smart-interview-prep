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

    public String generateQuestions(String topic) {
        String prompt = "Generate exactly 10 interview questions for topic: "
                + topic
                + ". Mix of theoretical and practical questions. "
                + "Number them 1-10. Keep each question clear and concise.";
        return callGemini(prompt);
    }

    public String evaluateAnswer(String question, String answer) {
        String prompt = "Question: " + question + "\n"
                + "Student Answer: " + answer + "\n"
                + "Evaluate this answer and give: \n"
                + "1. Score out of 10\n"
                + "2. What was correct\n"
                + "3. What was missing\n"
                + "4. Ideal answer in brief";
        return callGemini(prompt);
    }

    private String callGemini(String prompt) {
        try {
            return webClient.post()
                    .uri("/v1beta/models/gemini-2.0-flash:generateContent?key=" + apiKey)
                    .header("Content-Type", "application/json")
                    .bodyValue(Map.of(
                            "contents", List.of(
                                    Map.of("parts", List.of(
                                            Map.of("text", prompt)
                                    ))
                            )
                    ))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (Exception e) {
            // API fail hone par mock response return karo
            return getMockResponse(prompt);
        }
    }

    private String getMockResponse(String prompt) {
        // Pehle evaluate check karo
        if (prompt.contains("Score out of 10")) {
            return "{\"candidates\":[{\"content\":{\"parts\":[{\"text\":\"1. Score: 7/10\\n2. What was correct: Good understanding of the concept\\n3. What was missing: Need more detail and examples\\n4. Ideal Answer: A complete answer should include definition, working mechanism and real world example.\"}]}}]}";
        } else if (prompt.contains("DSA")) {
            return "{\"candidates\":[{\"content\":{\"parts\":[{\"text\":\"1. What is a Binary Search Tree?\\n2. Explain time complexity of Quick Sort.\\n3. What is Dynamic Programming?\\n4. Difference between Stack and Queue?\\n5. What is BFS and DFS?\\n6. Explain Linked List vs Array.\\n7. What is recursion?\\n8. Explain Hash Map.\\n9. What is a Heap?\\n10. Explain Graph traversal.\"}]}}]}";
        } else if (prompt.contains("Java")) {
            return "{\"candidates\":[{\"content\":{\"parts\":[{\"text\":\"1. What is OOP?\\n2. Explain inheritance in Java.\\n3. What is polymorphism?\\n4. Difference between abstract class and interface?\\n5. What is Java Collections Framework?\\n6. Explain exception handling.\\n7. What is multithreading?\\n8. Explain JVM architecture.\\n9. What is garbage collection?\\n10. Explain Java memory model.\"}]}}]}";
        } else if (prompt.contains("Spring Boot")) {
            return "{\"candidates\":[{\"content\":{\"parts\":[{\"text\":\"1. What is Spring Boot?\\n2. Explain dependency injection.\\n3. What is REST API?\\n4. Difference between @Controller and @RestController?\\n5. What is Spring Security?\\n6. Explain JPA and Hibernate.\\n7. What is application.properties?\\n8. Explain Spring Boot auto-configuration.\\n9. What is @SpringBootApplication?\\n10. Explain Spring Boot actuator.\"}]}}]}";
        } else if (prompt.contains("HR")) {
            return "{\"candidates\":[{\"content\":{\"parts\":[{\"text\":\"1. Tell me about yourself.\\n2. What are your strengths?\\n3. Where do you see yourself in 5 years?\\n4. Why do you want to join our company?\\n5. What is your biggest weakness?\\n6. Describe a challenging situation you faced.\\n7. How do you handle pressure?\\n8. What motivates you?\\n9. Do you prefer working alone or in a team?\\n10. Why should we hire you?\"}]}}]}";
        } else {
            return "{\"candidates\":[{\"content\":{\"parts\":[{\"text\":\"1. Explain your project experience.\\n2. What technologies do you know?\\n3. Describe your problem solving approach.\\n4. What is your biggest achievement?\\n5. How do you stay updated with technology?\\n6. Explain agile methodology.\\n7. What is version control?\\n8. Explain SOLID principles.\\n9. What is design pattern?\\n10. How do you debug your code?\"}]}}]}";
        }
    }
}