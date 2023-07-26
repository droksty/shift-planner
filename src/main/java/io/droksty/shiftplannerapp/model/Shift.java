package io.droksty.shiftplannerapp.model;

import java.util.HashSet;
import java.util.Set;

public class Shift {

    // Properties
    private final ShiftOrder shiftOrder;
    private final Set<Employee> employees = new HashSet<>();


    // Constructors & Static Factories
    private Shift(ShiftOrder shiftOrder) {
        this.shiftOrder = shiftOrder;
    }
    public static Shift getInstance(ShiftOrder shiftOrder) {
        return new Shift(shiftOrder);
    }


    // Getters & Setters
    public ShiftOrder getShiftOrder() {
        return shiftOrder;
    }
    public Set<Employee> getEmployees() {
        return employees;
    }


    // Public API
    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }
    public void removeEmployee(Employee employee) {
        this.employees.remove(employee);
    }
}
