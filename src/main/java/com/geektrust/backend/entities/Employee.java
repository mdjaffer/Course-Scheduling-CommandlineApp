package com.geektrust.backend.entities;

public class Employee {
    
    private String emailId;
    private String name;
    private boolean allotmentStatus;

    public Employee(String emailId, String name, boolean registrationState) {
  
        this.emailId = emailId;
        this.name = name;
        allotmentStatus = registrationState;
    }
/*
    public String getEmailId() {
        return emailId;
    }

    public String getName() {
        return name;
    }
*/
    public boolean getAllotmentStatus() {
        return allotmentStatus;
    }

 /*   public void setAllotmentStatus(boolean status) {
        allotmentStatus = status;
    }
*/
}
