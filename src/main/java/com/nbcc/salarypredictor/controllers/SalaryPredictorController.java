package com.nbcc.salarypredictor.controllers;

import com.nbcc.salarypredictor.dao.SalaryDAO;
import com.nbcc.salarypredictor.dao.SalaryDAOImpl;
import com.nbcc.salarypredictor.model.ErrorModel;
import com.nbcc.salarypredictor.model.Salary;
import com.nbcc.salarypredictor.util.ValidationUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SalaryPredictorController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String SALARY_LIST = "/listSalaries.jsp"; // LIST
    private static final String SALARY_PREDICTOR = "/salaryPredictor.jsp"; // CREATE
    private static final String SALARY_SEARCH = "/viewSalary.jsp"; // SINGLE
    private static final String SALARY_CREATED = "/salaryPredictorResult.jsp"; // SINGLE

    private RequestDispatcher view;

    public RequestDispatcher getView() {
        return view;
    }

    public void setView(HttpServletRequest request, String viewPath) {
        view = request.getRequestDispatcher(viewPath);
    }

    public SalaryPredictorController() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getPathInfo();
        SalaryDAO dao = new SalaryDAOImpl();

        setView(request, SALARY_PREDICTOR);
        if (path != null) {
            String[] destination = path.split("/");
            if (destination[1].equalsIgnoreCase("list")) {
                List<Salary> list = dao.findAll();
                request.setAttribute("salaryList", list);
                setView(request, SALARY_LIST);
            } else if (destination[1].equalsIgnoreCase("search")) {
                setView(request, SALARY_SEARCH);
            }

            getView().forward(request, response);

        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Salary salary = new Salary();
        String firstName = null;
        String lastName = null;
        double currentSalary = 0;
        double nextYearSalary = 0;

        SalaryDAO dao = new SalaryDAOImpl();
        ValidationUtil util = new ValidationUtil();

        setView(request, SALARY_PREDICTOR);

        try {
            String action = request.getParameter("action");
            if (action.equals("create")) {
                firstName = request.getParameter("firstName");
                lastName = request.getParameter("lastName");
                currentSalary = util.getDouble(request.getParameter("currentSalary"), "Current salary");

                util.checkRequiredField(firstName, "First Name");
                util.checkRequiredField(lastName, "Last Name");
                util.checkRequiredField(request.getParameter("currentSalary"), "Current Salary");

                util.errorCheck();


                if (currentSalary < 40000) {
                    nextYearSalary = currentSalary + currentSalary * 0.05;
                } else {
                    nextYearSalary = currentSalary + 2000 + (currentSalary - 40000) * 0.02;
                }
                salary.setFirstName(firstName);
                salary.setLastName(lastName);
                salary.setCurrentSalary(currentSalary);
                salary.setNextYearSalary(nextYearSalary);

                dao.insert(salary);
                request.setAttribute("createdSalary", salary);
                setView(request, SALARY_CREATED);

            } else if (action.equals("search")) {
                lastName = request.getParameter("lastName");
                util.checkRequiredField(lastName, "Last Name");

                util.errorCheck();

                salary = dao.findByLastName(lastName);
                List<Salary> list = new ArrayList<>();
                if (salary.getSalaryId() != 0) {
                    list.add(salary);
                    request.setAttribute("salaryList", list);
                }

                setView(request, SALARY_LIST);
            }
        } catch (Exception e) {
            request.setAttribute("errors", util.errors);
        }
        getView().forward(request, response);
    }
}
