package com.challenge.repository;

import com.challenge.entity.Company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompanyRepository extends CrudRepository<Company, Long> {


    List<Company>  findDistinctByCandidatesIdAccelerationId( Long id);

    @Query(value = "SELECT * from company " +
            " join candidate ca on company.id = ca.company_id " +
            " where ca.user_id = :userId", nativeQuery = true)
    List<Company> findByUserId(@Param("userId") Long id);
}
