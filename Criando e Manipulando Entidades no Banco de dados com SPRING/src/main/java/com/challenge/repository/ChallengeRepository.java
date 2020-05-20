package com.challenge.repository;

import com.challenge.entity.Challenge;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChallengeRepository extends CrudRepository<Challenge, Long> {

    @Query(value = "SELECT * from challenge " +
            " join acceleration ac on challenge.id = ac.challenge_id " +
            " join candidate ca on ac.id = ca.acceleration_id " +
            " where ac.id = :accelId " +
            " and ca.user_id = :userId", nativeQuery = true)
    List<Challenge> findByAccelerationIdAndUserId(@Param("accelId") Long accelerationId, @Param("userId") Long userId);
}
