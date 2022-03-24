package com.nbcc.salarypredictor.dao;

import com.nbcc.salarypredictor.model.Salary;
import java.util.List;

public interface SalaryDAO {

    public int insert(Salary salary);

    public List<Salary> findAll();

    public Salary findByLastName(String lastName);
}
