package com.mindex.challenge.data;

public class ReportingStructure {
    // Unique Identifier
    private Employee employee;
    // Init numberOfReports to 0. To be updated manually later during execution
    private int numberOfReports = 0;

    public ReportingStructure() {
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee){
        this.employee = employee;
    }

    /**
     * method to increment/add a specified numberOfReports to the current stored value.
     * @param numberOfReports input integer that represents a number of reports to be added to current total
     */
    public void addToNumberOfReports(int numberOfReports) {
        this.numberOfReports += numberOfReports;
    }

    public int getNumberOfReports() {
        return numberOfReports;
    }
}
