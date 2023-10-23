package com.geektrust.backend.entities;

public class Course {
    
    private String title;
    private String instructorName;
    private String date;
    private int minEmployees;
    private int maxEmployees;
    boolean status;
 
    public Course(String title, String instructorName, String date, int minEmp,
            int maxEmp, boolean status) {

        this.title = title;
        this.instructorName = instructorName;
        this.date = date;
        minEmployees = minEmp;
        maxEmployees = maxEmp;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public String getDate() {
        return date;
    }
    
    public int getMinEmployees() {
        return minEmployees;
    }

    public int getMaxEmployees() {
        return maxEmployees;
    }

    public boolean getStatus() {
        return status;
    }
    
    public void setStatus(boolean status) {
        this.status = status;
    }

}
