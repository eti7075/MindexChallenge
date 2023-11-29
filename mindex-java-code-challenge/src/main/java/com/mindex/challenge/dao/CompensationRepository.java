package com.mindex.challenge.dao;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

// Repository to store Compensation Records
@Repository
public interface CompensationRepository extends MongoRepository<Compensation, String> {
    /**
     * Finds a Compensation record by employee
     * @param employee employee to find the compensation record by
     * @return stored state of the compensation record in the dataase
     */
    Compensation findByEmployee(Employee employee);
}
