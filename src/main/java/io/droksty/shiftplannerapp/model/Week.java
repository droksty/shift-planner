package io.droksty.shiftplannerapp.model;

import java.time.LocalDate;
import java.util.Arrays;

public class Week {

    // Properties
    private Week prev;
    private final Day[] days = new Day[7];


    // Constructors & Static Factories
    private Week() {}

    public static Week generateWeek(LocalDate date) {
        Week week = new Week();

        for (int i = 0; i < 7; i++) {
            Day day = Day.generateDay(date.plusDays(i+1));
            Arrays.fill(week.getDays(), i, i+1, day);
            if (i > 0) {
                day.setPrevious(week.getDays()[i-1]);
            }
        }

        return week;
    }


    // Getters & Setters
    public Week getPrev() {
        return prev;
    }
    public void setPrev(Week prev) {
        this.prev = prev;
    }

    public Day[] getDays() {
        return days;
    }
}
