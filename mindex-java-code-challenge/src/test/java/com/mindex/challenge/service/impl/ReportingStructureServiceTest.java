package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;



import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceTest {
    private String reportingIdUrl;
    private String employeeUrl;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        reportingIdUrl = "http://localhost:" + port + "/reporting/{id}";
        employeeUrl = "http://localhost:" + port + "/employee";
    }
    @Test
    public void testRetrieve() {
        Employee testEmployee1 = new Employee();
        testEmployee1.setFirstName("Robert");
        testEmployee1.setLastName("Kraft");
        testEmployee1.setDepartment("Senior Management");
        testEmployee1.setPosition("Owner");


        Employee createdEmployee1 = restTemplate.postForEntity(employeeUrl, testEmployee1, Employee.class).getBody();

        ReportingStructure reportingStructure = restTemplate.getForEntity(reportingIdUrl, ReportingStructure.class, createdEmployee1.getEmployeeId()).getBody();
        assertEmployeeEquivalence(createdEmployee1, reportingStructure.getEmployee());
        assertEquals(0, reportingStructure.getNumberOfReports());
    }

    private static void assertEmployeeEquivalence(Employee expected, Employee actual) {
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getDepartment(), actual.getDepartment());
        assertEquals(expected.getPosition(), actual.getPosition());
    }
}
