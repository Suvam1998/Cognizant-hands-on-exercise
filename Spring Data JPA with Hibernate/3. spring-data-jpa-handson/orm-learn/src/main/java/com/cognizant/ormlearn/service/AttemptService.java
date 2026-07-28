package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.quiz.Attempt;
import com.cognizant.ormlearn.repository.AttemptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AttemptService {

    @Autowired
    private AttemptRepository attemptRepository;

    @Transactional
    public Attempt getAttempt(int userId, int attemptId) {
        return attemptRepository.getAttempt(userId, attemptId);
    }
}
