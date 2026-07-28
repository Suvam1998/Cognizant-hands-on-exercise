package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.quiz.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AttemptRepository extends JpaRepository<Attempt, Integer> {

    /**
     * Hands-on 3: join the tables in order user -> attempt -> attempt_question
     * -> question -> attempt_option -> options, using "join fetch" for the
     * one-to-many / many-to-many relationships so the whole graph loads.
     */
    @Query("SELECT DISTINCT a FROM Attempt a "
            + "JOIN FETCH a.user "
            + "JOIN FETCH a.attemptQuestions aq "
            + "JOIN FETCH aq.question "
            + "JOIN FETCH aq.attemptOptions ao "
            + "JOIN FETCH ao.option "
            + "WHERE a.user.id = :userId AND a.id = :attemptId")
    Attempt getAttempt(@Param("userId") int userId, @Param("attemptId") int attemptId);
}
