package com.geektrust.backend.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.geektrust.backend.entities.Instructor;

public class InstructorRepository {
    
    private Map<String, Instructor> courseInstructorMap;

    public InstructorRepository() {
        courseInstructorMap = new HashMap<String, Instructor>();
    }

    public void saveInstructorDetails(String instructorName,String courseName) {
        
        Instructor newInstructor = new Instructor(instructorName);

        courseInstructorMap.put(courseName, newInstructor);
        
    }

    public boolean isInstructorAvailable(String instructorName) {
        
        List<Instructor> instructorList 
                            = new ArrayList<Instructor>(courseInstructorMap.values());

        for (Instructor instructor : instructorList) {
            if (instructor.getName().equals(instructorName))
                return true;
        }                

        return false;
    }

    public Instructor getInstructor(String courseName) {
        return courseInstructorMap.get(courseName);
    }

    public void deleteInstructor(String courseName) {
        courseInstructorMap.remove(courseName);
    }
    
}
