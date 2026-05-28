package com.smart.interview.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smart.interview.model.Progress;
import com.smart.interview.model.User;

public interface ProgressRepository extends JpaRepository<Progress, Long> {
    List<Progress> findByUser(User user);
    List<Progress> findByUserAndTopic(User user, String topic);
}