package com.challenge.repository;

import com.challenge.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query(value = "SELECT * from users " +
            " join candidate c on users.id = c.user_id " +
            " join acceleration ac on ac.id = c.acceleration_id" +
            " where ac.name = :accelName", nativeQuery = true)
    List<User> findByAccelerationName(@Param("accelName") String name);

    @Query(value = "SELECT * from users " +
            " join candidate c on users.id = c.user_id " +
            " join company com on com.id = c.company_id" +
            " where com.id = :companyId", nativeQuery = true)
    List<User> findByCompanyId(@Param("companyId") Long id);
}
