package com.mindex.challenge.controller;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReportingStructureController {
    // Logging
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureController.class);

    // Service instantiation
    @Autowired
    private ReportingStructureService reportingStructureService;

    /**
     * Endpoint to retrieve a report structure for a given employee id
     * @param id employeeId that identifies a report structure
     * @return filled out ReportingStructure
     */
    @GetMapping("/reporting/{id}")
    public ReportingStructure retrieve(@PathVariable String id) {
        LOG.debug("Received employee retrieve request for id [{}]", id);

        return reportingStructureService.retrieve(id);
    }
}
