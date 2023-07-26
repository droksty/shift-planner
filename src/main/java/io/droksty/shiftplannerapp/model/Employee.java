package io.droksty.shiftplannerapp.model;

public class Employee {

    // Properties
    private String firstname;


    // Constructors & Static Factories
    private Employee(String firstname) {
        this.firstname = firstname;
    }
    public static Employee createWithName(String name) {
        return new Employee(name);
    }


    // Getters & Setters
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

}
