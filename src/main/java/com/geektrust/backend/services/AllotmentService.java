package com.geektrust.backend.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.geektrust.backend.exceptions.CourseOfferingNotAvailable;
import com.geektrust.backend.repositories.CourseOfferingRepository;
import com.geektrust.backend.repositories.EmployeeRepository;
import com.geektrust.backend.repositories.RegistrationRepository;

public class AllotmentService implements IAllotmentService {
    
    private CourseOfferingRepository courseOfferingRepository;
    private RegistrationRepository registrationRepository;
    private EmployeeRepository employeeRepository;

    public AllotmentService(CourseOfferingRepository courseOfferingRepository,
            RegistrationRepository registrationRepository,
            EmployeeRepository employeeRepository) {
        this.courseOfferingRepository = courseOfferingRepository;
        this.registrationRepository = registrationRepository;
        this.employeeRepository = employeeRepository;
    }

    /* This function allots the course to employees */
    @Override
    public List<String> allotCourse(String courseId) 
                            throws CourseOfferingNotAvailable {
        
        String result = "";
        String allotmentStatus = "";
        String courseName = courseId.split("-")[1];
        List<String> allotmentList = new ArrayList<String>();
        List<String> emailIdList = new ArrayList<String>();
        List<String> registrationIdList = new ArrayList<String>();
        LocalDate courseDate; 
        DateTimeFormatter format = DateTimeFormatter.ofPattern("ddMMyyyy");
        int numberOfEmployees = 0;
        int minEmployees = 0;

        /* Check if course offering exist */
        if (!courseOfferingRepository.isCourseAlreadyAvailable(courseName))
            throw new CourseOfferingNotAvailable("COURSE NOT AVAILABLE");

        emailIdList = registrationRepository.getEmailIds(courseId);

        courseDate = LocalDate.parse(
                                courseOfferingRepository.getCourse(courseName)
                                .getDate() , format);

        /* Check if course offering date is passed */
        /*if (LocalDate.now().isAfter(courseDate)){
            throw new CourseOfferingNotAvailable("COURSE NOT AVAILABLE");
        }*/

        numberOfEmployees = registrationRepository.getNumberOfEmployees(courseId);
        
        minEmployees = courseOfferingRepository.getMinEmployees(courseName);

        /* Check if minimum number of employees are available */
        if (numberOfEmployees < minEmployees) {
            allotmentStatus = "COURSE_CANCELED";
        } else {
            allotmentStatus = "CONFIRMED";
        }


        for(String emailId : emailIdList) {
            registrationIdList.add(registrationRepository.getRegistrationId(emailId));
        }

        Collections.sort(registrationIdList);

        for(String regId : registrationIdList){

            result = regId 
                        + " "
                        + registrationRepository.getRegisteredEmailId(regId)
                        + " "
                        + courseId
                        + " "
                        + courseName
                        + " "
                        + courseOfferingRepository.getCourse(courseName).getInstructorName()
                        + " "
                        + courseOfferingRepository.getCourse(courseName).getDate()
                        + " "
                        + allotmentStatus;
            /* If allotment is confirmed then update status of employee as CONFIRMED (true) */
            if (allotmentStatus.equals("CONFIRM")) {
                String emailID = registrationRepository.getRegisteredEmailId(regId);
                employeeRepository.setEmployeeAllotmentStatus(emailID, true);
            }

            allotmentList.add(result);
        }
                
        return allotmentList;
    }
}
