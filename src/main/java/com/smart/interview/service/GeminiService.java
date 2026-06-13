package com.smart.interview.service;

import java.util.List;
import java.util.Map;
import java.util.Random;

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
        String prompt = "Generate exactly 10 unique interview questions for topic: "
                + topic
                + ". Mix of theoretical and practical questions. "
                + "Number them 1-10. Keep each question clear and concise.";
        return callGemini(prompt);
    }

    public String evaluateAnswer(String question, String answer) {
        String prompt = "Question: " + question + "\n"
                + "Student Answer: " + answer + "\n"
                + "Evaluate this answer strictly and give: \n"
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
        	System.out.println("Gemini Error: " + e.getMessage());
            return getMockResponse(prompt);
        }
    }

    private String getMockResponse(String prompt) {
        Random random = new Random();

        if (prompt.contains("Score out of 10")) {
            return "{\"candidates\":[{\"content\":{\"parts\":[{\"text\":\"API unavailable — thodi der baad try karo!\"}]}}]}";
        }

        String[][] dsaQ = {
            {"1. What is Binary Search Tree?","2. Explain Quick Sort time complexity.","3. What is Dynamic Programming?","4. Difference between Stack and Queue?","5. What is BFS and DFS?","6. Explain Linked List vs Array.","7. What is recursion?","8. Explain Hash Map.","9. What is a Heap?","10. Explain Graph traversal."},
            {"1. What is Merge Sort?","2. Explain DFS algorithm.","3. What is Circular Queue?","4. Explain Binary Tree traversal.","5. What is memoization?","6. Explain two pointer technique.","7. What is sliding window?","8. Explain backtracking.","9. What is greedy algorithm?","10. Explain Dijkstra algorithm."}
        };

        String[][] javaQ = {
            {"1. What is OOP?","2. Explain inheritance in Java.","3. What is polymorphism?","4. Difference between abstract class and interface?","5. What is Java Collections Framework?","6. Explain exception handling.","7. What is multithreading?","8. Explain JVM architecture.","9. What is garbage collection?","10. Explain Java memory model."},
            {"1. What is constructor?","2. Explain super keyword.","3. What is final keyword?","4. Explain try-catch-finally.","5. What is ArrayList vs LinkedList?","6. Explain HashMap vs HashTable.","7. What is Lambda expression?","8. Explain Stream API.","9. What is serialization?","10. Explain design patterns."}
        };

        String[][] springQ = {
            {"1. What is Spring Boot?","2. Explain dependency injection.","3. What is REST API?","4. Difference between @Controller and @RestController?","5. What is Spring Security?","6. Explain JPA and Hibernate.","7. What is application.properties?","8. Explain Spring Boot auto-configuration.","9. What is @SpringBootApplication?","10. Explain Spring Boot actuator."},
            {"1. What is @Component?","2. Explain @Service annotation.","3. What is @Repository?","4. Explain @Autowired.","5. What is @RequestMapping?","6. Explain @PathVariable.","7. What is @RequestBody?","8. Explain Spring profiles.","9. What is Spring AOP?","10. Explain Spring transactions."}
        };

        String[][] hrQ = {
            {"1. Tell me about yourself.","2. What are your strengths?","3. Where do you see yourself in 5 years?","4. Why do you want to join our company?","5. What is your biggest weakness?","6. Describe a challenging situation.","7. How do you handle pressure?","8. What motivates you?","9. Team or solo work?","10. Why should we hire you?"},
            {"1. What are your salary expectations?","2. Why did you choose this field?","3. What is your greatest achievement?","4. How do you handle criticism?","5. Describe your work style.","6. How do you prioritize tasks?","7. What do you know about our company?","8. How do you handle failure?","9. What makes you unique?","10. Any questions for us?"}
        };

        String[] questions;
        if (prompt.contains("DSA")) {
            questions = dsaQ[random.nextInt(dsaQ.length)];
        } else if (prompt.contains("Java")) {
            questions = javaQ[random.nextInt(javaQ.length)];
        } else if (prompt.contains("Spring Boot")) {
            questions = springQ[random.nextInt(springQ.length)];
        } else if (prompt.contains("HR")) {
            questions = hrQ[random.nextInt(hrQ.length)];
        } else {
            questions = dsaQ[random.nextInt(dsaQ.length)];
        }

        String text = String.join("\\n", questions);
        return "{\"candidates\":[{\"content\":{\"parts\":[{\"text\":\"" + text + "\"}]}}]}";
    }
}