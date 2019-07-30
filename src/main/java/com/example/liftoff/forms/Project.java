package com.example.liftoff.forms;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Project {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=1, message = "Name must not be empty")
    private String name;

    @NotNull
    @Size(min=1, message = "Please add a project goal")
    private String goal;

    @NotNull
    private double budget;

    //constructors
    public Project(String name, String goal, double budget) {
        this.name = name;
        this.goal = goal;
        this.budget = budget;
    }

    public Project() { }

    //getters and setters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }
}
