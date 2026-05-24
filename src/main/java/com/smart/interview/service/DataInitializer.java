package com.smart.interview.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.smart.interview.model.Topic;
import com.smart.interview.repository.TopicRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final TopicRepository topicRepository;

    public DataInitializer(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (topicRepository.count() == 0) {
            topicRepository.save(new Topic("DSA", "TECHNICAL"));
            topicRepository.save(new Topic("Java", "TECHNICAL"));
            topicRepository.save(new Topic("Spring Boot", "TECHNICAL"));
            topicRepository.save(new Topic("System Design", "TECHNICAL"));
            topicRepository.save(new Topic("HR Questions", "HR"));
            System.out.println("Topics initialized!");
        }
    }
}