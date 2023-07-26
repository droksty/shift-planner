package io.droksty.shiftplannerapp;

import io.droksty.shiftplannerapp.model.*;
import io.droksty.shiftplannerapp.util.ShiftManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {

    @FXML
    private Button exitButton;
    @FXML
    private AnchorPane scenePane;


    Stage stage;


    // Table & Columns
    @FXML
    private TableView<Day> theTable;
    @FXML
    private TableColumn<Day, String> dayCol;
    @FXML
    private TableColumn<Day, String> firstShiftCol;
    @FXML
    private TableColumn<Day, String> secondShiftCol;
    @FXML
    private TableColumn<Day, String> thirdShiftCol;



    // Public API

    private Week generateShiftsWithTestData() {
        var white = Employee.createWithName("Mr. White");
        var orange = Employee.createWithName("Mr. Orange");
        var blonde = Employee.createWithName("Mr. Blonde");
        var pink = Employee.createWithName("Mr. Pink");
        var blue = Employee.createWithName("Mr. Blue");
        var brown = Employee.createWithName("Mr. Brown");

        Set<Employee> employees = Stream.of(white, orange, blonde, pink, blue, brown)
                .collect(Collectors.toCollection(HashSet::new));

        Week week = Week.generateWeek(LocalDate.of(2023, 7, 16));
        week.getDays()[0].setPrevious(Day.generateDateZero(LocalDate.of(2023,7,16), brown, white, brown));

        for (Day day : week.getDays()) {
            day.generateAvailableEmployees(employees);
            ShiftManager.populateAllShifts(day);
        }

        return week;
    }


    public void displayShifts(ActionEvent event) {
        Week week = generateShiftsWithTestData();
        ObservableList<Day> days = FXCollections.observableArrayList();
        days.addAll(week.getDays());
        theTable.setItems(days);

        dayCol.setCellValueFactory(data -> {
            return new SimpleStringProperty(data.getValue().getDate().getDayOfWeek().toString());
        });

        firstShiftCol.setCellValueFactory(data -> {
            Set<Employee> employees = data.getValue().getFirstShift().getEmployees();
            StringBuilder employeesString = new StringBuilder();
            employees.forEach(employee -> employeesString.append(employee.getFirstname()).append("\n"));
            return new SimpleStringProperty(employeesString.toString());
        });

        secondShiftCol.setCellValueFactory(data -> {
            Set<Employee> employees = data.getValue().getSecondShift().getEmployees();
            StringBuilder employeesString = new StringBuilder();
            employees.forEach(employee -> employeesString.append(employee.getFirstname()).append("\n"));
            return new SimpleStringProperty(employeesString.toString());
        });

        thirdShiftCol.setCellValueFactory(data -> {
            Set<Employee> employees = data.getValue().getThirdShift().getEmployees();
            StringBuilder employeesString = new StringBuilder();
            employees.forEach(employee -> employeesString.append(employee.getFirstname()).append("\n"));
            return new SimpleStringProperty(employeesString.toString());
        });
    }


    public void exitApp(ActionEvent event) {
        stage = (Stage) scenePane.getScene().getWindow();
        stage.close();
    }

}
