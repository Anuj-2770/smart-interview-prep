package com.smart.interview.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smart.interview.model.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    List<Topic> findByCategory(String category);
}