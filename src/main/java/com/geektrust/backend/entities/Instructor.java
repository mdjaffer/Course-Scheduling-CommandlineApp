package com.geektrust.backend.entities;

public class Instructor {

    private String name;

    public Instructor(String instructorName) {
        this.name = instructorName;
    }
    
    public String getName() {
        return name;
    }
}