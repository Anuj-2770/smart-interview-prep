package com.smart.interview.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smart.interview.model.Progress;
import com.smart.interview.model.User;
import com.smart.interview.repository.ProgressRepository;
import com.smart.interview.repository.UserRepository;

@RestController
@RequestMapping("/api/progress")
public class ProgressController {

    private final ProgressRepository progressRepository;
    private final UserRepository userRepository;

    public ProgressController(ProgressRepository progressRepository,
                               UserRepository userRepository) {
        this.progressRepository = progressRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/save")
    public Progress saveProgress(Authentication authentication,
                                  @RequestParam String topic,
                                  @RequestParam int questionsSolved) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found!"));
        Progress progress = new Progress();
        progress.setUser(user);
        progress.setTopic(topic);
        progress.setQuestionsSolved(questionsSolved);
        return progressRepository.save(progress);
    }

    @GetMapping("/my")
    public List<Progress> getMyProgress(Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found!"));
        return progressRepository.findByUser(user);
    }
}
