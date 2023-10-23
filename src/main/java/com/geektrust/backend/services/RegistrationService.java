package com.geektrust.backend.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.geektrust.backend.exceptions.CourseAlreadyAssignedException;
import com.geektrust.backend.exceptions.CourseFullException;
import com.geektrust.backend.exceptions.CourseOfferingNotAvailable;
import com.geektrust.backend.exceptions.RegistrationNotAvailableException;
import com.geektrust.backend.repositories.CourseOfferingRepository;
import com.geektrust.backend.repositories.EmployeeRepository;
import com.geektrust.backend.repositories.RegistrationRepository;

public class RegistrationService implements IRegistrationService {
    
    private CourseOfferingRepository courseOfferingRepository;
    private RegistrationRepository registrationRepository;
    private EmployeeRepository employeeRepository;

    public RegistrationService(CourseOfferingRepository courseOfferingRepository, 
            RegistrationRepository registrationRepository,
            EmployeeRepository employeeRepository) {
                this.courseOfferingRepository = courseOfferingRepository;
                this.registrationRepository = registrationRepository;
                this.employeeRepository = employeeRepository;
            }
    /* This function register the employee for course and returns registration ID */
    @Override
    public String registerEmployee(String emailId, String courseId)
                    throws CourseFullException, CourseOfferingNotAvailable {
        
        String result = "";
        String employeeName = emailId.split("@")[0];
        int numberOfEmployees = 0;
        int maxEmployees = 0;
        String courseName = courseId.split("-")[1];
 
        /* Check if course is available or not */
        if (!courseOfferingRepository.isCourseAlreadyAvailable(courseName)) {
            throw new CourseOfferingNotAvailable("COURSE NOT AVAILABLE");
        }
        /* Check if course is already assigned */
        if (registrationRepository.isCourseAlreadyAssigned(emailId, courseId))
            throw new CourseAlreadyAssignedException("ALREADY ASSIGNED");
        
        numberOfEmployees = registrationRepository.getNumberOfEmployees(courseId);
        
        maxEmployees = courseOfferingRepository.getMaxEmployees(courseName);
        /* Check if number of number of employee exceeds maximum limit for a course */
        if (numberOfEmployees >= maxEmployees) {
            throw new CourseFullException("COURSE_FULL_ERROR");
        }
        
        /* Save employee emailID and course in repository and generate registrationID */
        result = registrationRepository.saveRegistration(emailId, courseId);
        /* If number of employees registered for course 
            is less than minimum then set course state to cancel (false) else CONFIRMED (true) */
        if (numberOfEmployees < courseOfferingRepository.getMinEmployees(courseName)) {
            courseOfferingRepository.setCourseStatus(courseName, false);            
        } else {
            courseOfferingRepository.setCourseStatus(courseName, true);
        }
        /* Save employee details in repository */
        employeeRepository.saveEmployeeDetails(emailId, employeeName, false);

        return result;
    }

    /* This function cancels employee registration for a course offering */
    @Override
    public String cancleRegistration(String registrationId)
                    throws RegistrationNotAvailableException {

        String result = "";
        LocalDate courseDate; 
        DateTimeFormatter format = DateTimeFormatter.ofPattern("ddMMyyyy");
        String courseName = registrationId.split("-")[3];
        /* Check if the registration ID exist */
        if (!registrationRepository.isRegistrationAvailable(registrationId)) {
            throw new RegistrationNotAvailableException("REGISTRATION-ID NOT AVAILABLE");
        }
        
        courseDate = LocalDate.parse(
                                courseOfferingRepository.getCourse(courseName)
                                .getDate(), format);

        String emailId = registrationRepository.getRegisteredEmailId(registrationId);
        /* Check allotment status of employee for course offering */
        if (employeeRepository.getEmployee(emailId).getAllotmentStatus()) {
            result = "CANCEL_REJECTED";
        /* Check if course offering date is already passed */
        /*} else if(LocalDate.now().isEqual(courseDate) || 
                    LocalDate.now().isAfter(courseDate)) {
            result = "CANCEL_REJECTED";
                    */
        } else {
            /* Cancel the registration */
            result = registrationRepository.cancleRegistration(registrationId);
            result = registrationId + " " + result;
            employeeRepository.deleteEmployee(emailId);
        }    
        
        return result;
    } 
}
