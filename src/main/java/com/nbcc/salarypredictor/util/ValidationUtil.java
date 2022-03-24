package com.nbcc.salarypredictor.util;

import jakarta.servlet.http.HttpServletRequest;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ValidationUtil {

    public String errors = "";

    public double getDouble(String value, String fieldName) {
        try {
            return Double.parseDouble(value);
        } catch (Exception e) {
            this.errors += fieldName + " must be numeric<br>";
            return 0.0;
        }
    }

    public void checkRequiredField(String value, String fieldName) {
        if (value.equals("")) {
            this.errors += fieldName + " is required<br>";
        }
    }

    public String currencyFormatter(double value) {
        try {
            Locale locale = new Locale("en", "US");
            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
            return currencyFormatter.format(value);
        } catch (Exception e) {
            this.errors += "Can't format " + value + " to currency<br>";
            return "0.0";
        }
    }

    public void errorCheck() throws Exception {
        if (!errors.equals("")) {
            throw new Exception(this.errors);
        }
    }
}
