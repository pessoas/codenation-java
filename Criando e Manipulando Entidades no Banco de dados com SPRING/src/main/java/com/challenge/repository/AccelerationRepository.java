package com.challenge.repository;

import com.challenge.entity.Acceleration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccelerationRepository extends CrudRepository<Acceleration, Long> {

    Optional<Acceleration> findAccelerationById(Long id);
    List<Acceleration> findAccelerationByName(String name);

    @Query(value = "SELECT * from acceleration " +
            " join candidate c on acceleration.id = c.acceleration_id " +
            " join company com on com.id = c.company_id" +
            " where com.id = :companyId", nativeQuery = true)
    List<Acceleration> findByCompanyId(@Param("companyId") Long company);
}
