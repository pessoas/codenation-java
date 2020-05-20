package com.challenge.repository;

import com.challenge.entity.Submission;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface SubmissionRepository extends CrudRepository<Submission, Long> {

    @Query(value = "SELECT MAX(submission.score) FROM submission " +
            " WHERE submission.challenge_id = :chId" , nativeQuery = true)
    BigDecimal findHigherScoreByChallengeId(@Param("chId") Long id);

    @Query(value = "SELECT * FROM submission " +
            " JOIN challenge ch ON submission.challenge_id = ch.id " +
            " JOIN acceleration ac ON ac.challenge_id = ch.id" +
            " WHERE ac.id = :accelId AND ch.id = :chId" , nativeQuery = true)
    List<Submission> findByChallengeIdAndAccelerationId(@Param("chId") Long challengeId, @Param("accelId") Long accelerationId);

}
