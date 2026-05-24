package com.smart.interview.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smart.interview.model.Topic;
import com.smart.interview.repository.TopicRepository;

@RestController
@RequestMapping("/api/topics")
public class TopicController {

    private final TopicRepository topicRepository;

    public TopicController(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @PostMapping("/add")
    public Topic addTopic(@RequestParam String name,
                          @RequestParam String category) {
        Topic topic = new Topic();
        topic.setName(name);
        topic.setCategory(category);
        return topicRepository.save(topic);
    }

    @GetMapping("/all")
    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    @GetMapping("/category")
    public List<Topic> getByCategory(@RequestParam String category) {
        return topicRepository.findByCategory(category);
    }
}