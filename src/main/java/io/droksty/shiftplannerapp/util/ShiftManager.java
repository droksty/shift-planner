package io.droksty.shiftplannerapp.util;


import io.droksty.shiftplannerapp.model.Day;
import io.droksty.shiftplannerapp.model.Employee;
import io.droksty.shiftplannerapp.model.Shift;
import io.droksty.shiftplannerapp.model.ShiftOrder;

import java.util.ArrayList;
import java.util.Random;

public class ShiftManager {

    // Properties


    // Constructor
    private ShiftManager() {}


    // Public API
    public static void populateAllShifts(Day day) {
        for (Shift shift : day.getShifts()) {
            populateShift(day, shift.getShiftOrder());
        }
    }


    private static void populateShift(Day day, ShiftOrder shiftOrder) {
        ArrayList<Employee> shiftCandidates = generateShiftCandidateList(day, shiftOrder);
        Employee employee = shiftCandidates.get(new Random().nextInt(shiftCandidates.size()));

        switch (shiftOrder) {
            case FIRST:
                day.getFirstShift().addEmployee(employee);
                break;
            case SECOND:
                day.getSecondShift().addEmployee(employee);
                break;
            case THIRD:
                day.getThirdShift().addEmployee(employee);
                break;
            default:
                System.out.println("Unexpected Error. Throw an exception, would you kindly?");
        }
    }


    private static ArrayList<Employee> generateShiftCandidateList(Day day, ShiftOrder shiftOrder) {
        ArrayList<Employee> shiftCandidates = new ArrayList<>();

        switch (shiftOrder) {
            case FIRST:
                day.getAvailableEmployees().forEach(employee -> {
                    if (!day.getPrevious().getThirdShift().getEmployees().contains(employee)) {
                        shiftCandidates.add(employee);
                    }
                });
                break;
            case SECOND:
                day.getAvailableEmployees().forEach(employee -> {
                    if (!day.getFirstShift().getEmployees().contains(employee)) {
                        shiftCandidates.add(employee);
                    }
                });
                break;
            case THIRD:
                day.getAvailableEmployees().forEach(employee -> {
                    if (!day.getSecondShift().getEmployees().contains(employee)) {
                        shiftCandidates.add(employee);
                    }
                });
                break;
            default:
                System.out.println("Unexpected Error. Throw an exception?");
        }

        return shiftCandidates;
    }
}
