package com.mindex.challenge.data;

import java.time.LocalDate;

public class Compensation {
    // Unique Identifier
    private Employee employee;
    private double salary;
    // LocalDate has format 'yyyy-mm-dd'
    private LocalDate effectiveDate;

    public Compensation(){
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }
}
