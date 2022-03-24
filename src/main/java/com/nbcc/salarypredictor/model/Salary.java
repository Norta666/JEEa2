package com.nbcc.salarypredictor.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Salary {

    private int salaryId;
    private String firstName;
    private String lastName;
    private double currentSalary;
    private double nextYearSalary;

    public Salary() {
        super();
    }

    public Salary(int salaryId, String firstName, String lastName, double currentSalary, double nextYearSalary) {
        super();
        this.salaryId = salaryId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.currentSalary = currentSalary;
        this.nextYearSalary = nextYearSalary;
    }

    public int getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(int salaryId) {
        this.salaryId = salaryId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getCurrentSalary() {
        return currentSalary;
    }

    public void setCurrentSalary(double currentSalary) {
        this.currentSalary = currentSalary;
    }

    public double getNextYearSalary() {
        return nextYearSalary;
    }

    public void setNextYearSalary(double nextYearSalary) {
        this.nextYearSalary = nextYearSalary;
    }
}
