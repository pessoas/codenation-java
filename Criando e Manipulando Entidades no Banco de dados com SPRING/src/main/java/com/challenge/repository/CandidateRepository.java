package com.challenge.repository;

import com.challenge.entity.Candidate;
import com.challenge.entity.CandidateId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateRepository extends CrudRepository<Candidate, Long> {

    Optional<Candidate> findById(CandidateId candidate);

    @Query(value = "select * from candidate ca" +
            " where ca.user_id = :uId and " +
            "ca.company_id = :coId and " +
            "ca.acceleration_id = :accelId", nativeQuery = true)
    Optional<Candidate> findById(@Param("uId") Long userId, @Param("coId") Long companyId, @Param("accelId") Long accelerationId);

    @Query(value = "SELECT * from candidate " +
            " join acceleration a on candidate.acceleration_id = a.id" +
            " where a.id = :accelerationId", nativeQuery = true)
    List<Candidate> findByAccelerationId(@Param("accelerationId") Long id);

    @Query(value = "SELECT * from candidate " +
            " join company c on candidate.company_id = c.id" +
            " where c.id = :companyId", nativeQuery = true)
    List<Candidate> findByCompanyId(@Param("companyId")Long id);
}
