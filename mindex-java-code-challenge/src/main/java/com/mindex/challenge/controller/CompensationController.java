package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CompensationController {
    // Logging
    private static final Logger LOG = LoggerFactory.getLogger(CompensationController.class);

    // Service instantiation
    @Autowired
    private CompensationService compensationService;

    /**
     * Endpoint to create a Compensation record in the database instance from a provided Compensation
     * @param compensation Compensation to be added to the database
     * @return last recorded state of the Compensation added to the database
     */
    @PostMapping("/compensation")
    public Compensation create(@RequestBody Compensation compensation) {
        LOG.debug("Received compensation create request for [{}]", compensation.getEmployee());

        return compensationService.create(compensation);
    }

    /**
     * Endpoint to read a Compensation record from the database for a given employee id
     * @param id employeeId that identifies the Compensation record
     * @return last recorded state of the Compensation that is read from the database
     */
    @GetMapping("/compensation/{id}")
    public Compensation read(@PathVariable String id) {
        LOG.debug("Received compensation read request for id [{}]", id);

        return compensationService.read(id);
    }
}
