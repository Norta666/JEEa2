package com.nbcc.salarypredictor.dao;

import com.nbcc.salarypredictor.model.Salary;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.util.List;

public class SalaryDAOImpl implements SalaryDAO {

    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/javaee_a2";
    private static final String USER_ID = "root";
    private static final String PASSWORD = "123456";

    private static final String INSERT = "INSERT INTO javaee_a2.salary (firstName, lastName, currentSalary, nextYearSalary) VALUES(?,?,?,?)";
    private static final String FIND_ALL = "SELECT * FROM javaee_a2.salary ORDER BY salaryId";
    private static final String FIND_BY_NAME = "SELECT * FROM javaee_a2.salary WHERE lastName=?";

    private Connection getConnection() {
        try {
            Class.forName(DRIVER_NAME);
            return DriverManager.getConnection(DB_URL, USER_ID, PASSWORD);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public int insert(Salary salary) {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = getConnection();
            statement = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, salary.getFirstName());
            statement.setString(2, salary.getLastName());
            statement.setDouble(3, salary.getCurrentSalary());
            statement.setDouble(4, salary.getNextYearSalary());
            int result = statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                salary.setSalaryId(rs.getInt(1));
            }
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        } finally {
            close(conn);
            close(statement);
        }
    }

    @Override
    public List<Salary> findAll() {
        Connection conn = null;
        PreparedStatement statement = null;
        List<Salary> list = new ArrayList<Salary>();

        try {
            conn = getConnection();
            statement = conn.prepareStatement(FIND_ALL);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Salary salary = new Salary();
                salary.setSalaryId(rs.getInt("salaryId"));
                salary.setFirstName(rs.getString("firstName"));
                salary.setLastName(rs.getString("lastName"));
                salary.setCurrentSalary(rs.getDouble("currentSalary"));
                salary.setNextYearSalary(rs.getDouble("nextYearSalary"));
                list.add(salary);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            close(conn);
            close(statement);
        }
        return list;
    }

    @Override
    public Salary findByLastName(String lastName) {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = getConnection();
            statement = conn.prepareStatement(FIND_BY_NAME);
            statement.setString(1, lastName);
            ResultSet rs = statement.executeQuery();
            Salary salary = new Salary();
            if (rs.next()) {
                salary.setSalaryId(rs.getInt("salaryId"));
                salary.setFirstName(rs.getString("firstName"));
                salary.setLastName(rs.getString("lastName"));
                salary.setCurrentSalary(rs.getDouble("currentSalary"));
                salary.setNextYearSalary(rs.getDouble("nextYearSalary"));
            }
            return salary;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            close(conn);
            close(statement);
        }
    }
}
