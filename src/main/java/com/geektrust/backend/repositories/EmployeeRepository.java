package com.geektrust.backend.repositories;

import java.util.HashMap;
import java.util.Map;

import com.geektrust.backend.entities.Employee;

public class EmployeeRepository {
    
    private Map<String, Employee> emailIdEmployeeMap;

    public EmployeeRepository() {
        emailIdEmployeeMap = new HashMap<String, Employee>();
    }

    public void saveEmployeeDetails(String emailId, 
                String name, boolean registrationState) {
        
        Employee newEmployee = new Employee(emailId, name, registrationState);

        emailIdEmployeeMap.put(emailId, newEmployee);
        
    }

    public Employee getEmployee(String emailId) {
        return emailIdEmployeeMap.get(emailId);
    }

    public void deleteEmployee(String emailId) {
        emailIdEmployeeMap.remove(emailId);
    }

    public void setEmployeeAllotmentStatus(String emailId, boolean status) {
        /*Employee updatedEmployee = getEmployee(emailId);
        updatedEmployee.setAllotmentStatus(status); */
		Employee updatedEmployee = new Employee(emailId, emailId.split("@")[0], status);
        emailIdEmployeeMap.put(emailId, updatedEmployee);
    }

}
