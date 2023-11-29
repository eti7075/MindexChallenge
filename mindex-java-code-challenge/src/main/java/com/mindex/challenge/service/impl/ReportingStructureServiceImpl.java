package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Implementation of ReportingStructureService
@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {
    // Logging
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    // Repository instantiation
    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * method to retrieve a ReportingStructure from a given employeeId. Creates a new ReportingStructure from
     * the employee with the given id.
     * @param id employeeId of an employee. Not necessarily valid, will be checked in function
     * @return new ReportingStructure. The "filled out" RS contains its employee, reporting employees, and total
     * reporters below it.
     */
    @Override
    public ReportingStructure retrieve(String id) {
        LOG.debug("Retrieving reporting structure with id [{}]", id);

        // Get employee from database from Id
        Employee employee = employeeRepository.findByEmployeeId(id);

        // Check employee actually exists in database
        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        // Create a new reporting structure and set employee to the previously got employee
        ReportingStructure reportingStructure = new ReportingStructure();
        reportingStructure.setEmployee(employee);

        // Calculate the total number of reporters below the current employee. Recursive strategy to search through tree
        // Also find all reporters below given employee, and update current employees direct reports with filled out
        // employees
        List<Employee> directReports = employee.getDirectReports();
        if (directReports != null) {
            List<Employee> fullDirectReports = new ArrayList<>();
            for (Employee directEmployee : directReports) {
                // Recursive call
                ReportingStructure subReportingStructure = retrieve(directEmployee.getEmployeeId());
                // Get full employee from id
                Employee fullDirectEmployee = employeeRepository.findByEmployeeId(directEmployee.getEmployeeId());
                // Update direct reporter's direct reports. Fills out tree below current direct employee
                fullDirectEmployee.setDirectReports(subReportingStructure.getEmployee().getDirectReports());
                // Add to new, full direct reporter list
                fullDirectReports.add(fullDirectEmployee);
                // Update total number of reports
                reportingStructure.addToNumberOfReports(1 + subReportingStructure.getNumberOfReports());
            }
            // Update current employees direct reports to full employees
            reportingStructure.getEmployee().setDirectReports(fullDirectReports);
        }

        // Returns resulting reporting structure. contains top level employee, reporting employees, and total reports
        return reportingStructure;
    }
}
