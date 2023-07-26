package io.droksty.shiftplannerapp.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Day {

    // Properties
    private Day previous;
    private LocalDate date;
    private final Shift[] shifts = new Shift[3];
    private final Shift firstShift = Shift.getInstance(ShiftOrder.FIRST);
    private final Shift secondShift = Shift.getInstance(ShiftOrder.SECOND);
    private final Shift thirdShift = Shift.getInstance(ShiftOrder.THIRD);

    private final Set<Employee> employeesOnLeave = new HashSet<>();
    private final Set<Employee> availableEmployees = new HashSet<>();


    // Constructors & Static Factories
    private Day() {
        populateShiftsArray();
    }

    public static Day generateDay(LocalDate date) {
        Day day = new Day();
        day.setDate(date);
        return day;
    }

    public static Day generateDateZero(LocalDate date, Employee first, Employee second, Employee third) {
        Day day = new Day();
        day.firstShift.getEmployees().add(first);
        day.secondShift.getEmployees().add(second);
        day.thirdShift.getEmployees().add(third);
        day.setDate(date);
        return day;
    }


    // Getters & Setters
    public Day getPrevious() {
        return previous;
    }
    public void setPrevious(Day previous) {
        this.previous = previous;
    }

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Shift[] getShifts() {
        return shifts;
    }
    public Shift getFirstShift() {
        return firstShift;
    }
    public Shift getSecondShift() {
        return secondShift;
    }
    public Shift getThirdShift() {
        return thirdShift;
    }

    public Set<Employee> getAvailableEmployees() {
        return availableEmployees;
    }
    public Set<Employee> getEmployeesOnLeave() {
        return employeesOnLeave;
    }


    // Public API
    public void addToEmployeesOnLeave(Employee employee) {
        this.getEmployeesOnLeave().add(employee);
    }
    public void removeFromEmployeesOnLeave(Employee employee) {
        this.getEmployeesOnLeave().remove(employee);
    }

    public void generateAvailableEmployees(Set<Employee> employees) {
        employees.forEach(employee -> {
            if (!employeesOnLeave.contains(employee)) {
                availableEmployees.add(employee);
            }
        });
    }


    // Util
    private void populateShiftsArray() {
        this.shifts[0] = firstShift;
        this.shifts[1] = secondShift;
        this.shifts[2] = thirdShift;
    }
}