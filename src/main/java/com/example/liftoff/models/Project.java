package com.example.liftoff.models;


import com.example.liftoff.models.Category;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany
    @JoinColumn(name = "project_id")
    private List<Category> category = new ArrayList<>();

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

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }
}
