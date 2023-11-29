package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

// Implementation of the Compensation Service
@Service
public class CompensationServiceImpl implements CompensationService {
    // Logging
    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    // Employee database instantiation
    @Autowired
    private EmployeeRepository employeeRepository;

    // Compensation database instantiation
    @Autowired
    private CompensationRepository compensationRepository;

    /**
     * method to create a new compensation record in the database from a given compensation
     * @param compensation provided input to be inserted into database
     * @return compensation that was input into the database
     */
    @Override
    public Compensation create(Compensation compensation) {
        LOG.debug("Creating compensation with employee id [{}]", compensation.getEmployee());

        // Insert compensation into compensation repository
        compensationRepository.insert(compensation);

        return compensation;
    }

    /**
     * method to read a compensation record from the database given an employee id
     * @param id employeeId that identifies an employee
     * @return Compensation that is identified by an employee
     */
    @Override
    public Compensation read(String id) {
        LOG.debug("Reading compensation with employee id [{}]", id);

        // Get the employee from employee database with given Id
        Employee employee = employeeRepository.findByEmployeeId(id);

        // Get the compensation from the compensation database with the found employee
        // Employee identifies the compensation
        Compensation compensation = compensationRepository.findByEmployee(employee);

        // Check if a compensation for this employee exists. If not -> error
        if (compensation == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        // return the found compensation with an employee, salary, and effectiveDate
        return compensation;
    }

}
