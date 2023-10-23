package com.geektrust.backend.repositories;

import java.util.HashMap;
import java.util.Map;

import com.geektrust.backend.entities.Course;

public class CourseOfferingRepository {
    
    private Map<String, Course> courseOfferingMap;
    
    public CourseOfferingRepository() {
        courseOfferingMap = new HashMap<String, Course>();
    }

    public String saveCourseOffering(String courseName,
                    String instructorName, String date,
                    int minEmp, int maxEmp) {
        
        String courseOfferingId = "";

        Course newCourse = new Course(courseName, instructorName,
                                    date, minEmp, maxEmp, false);
     
        courseOfferingMap.put(courseName, newCourse);

        courseOfferingId = "OFFERING-" + courseName + "-" + instructorName;
        
        return courseOfferingId;
    }

    public boolean isCourseAlreadyAvailable(String courseName) {
        
        return courseOfferingMap.containsKey(courseName);

    } 
    
    public int getMaxEmployees(String courseName) {

        return courseOfferingMap.get(courseName).getMaxEmployees();
    }

    public int getMinEmployees(String courseName) {

        return courseOfferingMap.get(courseName).getMinEmployees();
    }

    public Course getCourse(String courseName) {
        return courseOfferingMap.get(courseName);
    }
    
    public void setCourseStatus(String courseName, boolean status) {

        Course updatedCourse = courseOfferingMap.get(courseName);

        updatedCourse.setStatus(status);

        courseOfferingMap.put(courseName, updatedCourse);
    }

    
 
}
