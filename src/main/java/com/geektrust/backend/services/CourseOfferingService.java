package com.geektrust.backend.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.geektrust.backend.exceptions.DuplicateCourseException;
import com.geektrust.backend.exceptions.IncorrectEmployeeNumbers;
import com.geektrust.backend.exceptions.InstructorAlreadyAssignedException;
import com.geektrust.backend.exceptions.InvalidDateException;
import com.geektrust.backend.repositories.CourseOfferingRepository;
import com.geektrust.backend.repositories.InstructorRepository;

public class CourseOfferingService implements ICourseOfferingService {

    private CourseOfferingRepository courseOfferingRepository;
    private InstructorRepository instructorRepository;

    public CourseOfferingService(CourseOfferingRepository courseOfferingRepository,
            InstructorRepository instructorRepository) {
        this.courseOfferingRepository = courseOfferingRepository;
        this.instructorRepository = instructorRepository;
    }

    /* This function generates course offering ID and save in repository */
    @Override
    public String addCourseOffering(String courseName, String instructorName,
                    String date, int minEmp, int maxEmp)
            throws DuplicateCourseException, InstructorAlreadyAssignedException,
                IncorrectEmployeeNumbers, InvalidDateException {

        String courseId = "";
        LocalDate localDate;
        /* Check if course offering is already available */
        if (courseOfferingRepository.isCourseAlreadyAvailable(courseName)) {
            throw new DuplicateCourseException("Course : " + courseName + " already available !");
        }
        /* Check if instructor is already assigned */
        if (instructorRepository.isInstructorAvailable(instructorName)) {
            throw new InstructorAlreadyAssignedException("Instructor Name : " + instructorName + " already assigned !");
        }
        /* Check valid number of employees */
        if ((minEmp < 0 || maxEmp < 0) || (minEmp > maxEmp)) {
            throw new IncorrectEmployeeNumbers("Provide valid miniumum and maximum employees");
        }

        DateTimeFormatter format = DateTimeFormatter.ofPattern("ddMMyyyy");
        /* Check if date passed as input is valid */
        try {
            localDate = LocalDate.parse(date, format);
        } catch (Exception e) {
            throw new InvalidDateException("Provide valid date");
        }

        /* if (localDate.isBefore(LocalDate.now())) {
            throw new InvalidDateException("Please provide today's date or future date !");
        }
        */
        /* Save course details in repository */
        courseId = courseOfferingRepository
                        .saveCourseOffering(courseName, instructorName, date, minEmp, maxEmp);
        /* Save instructor and course maping */
        instructorRepository.saveInstructorDetails(instructorName, courseName);

        /*System.out.println("CourseId : " + courseId 
                    + " MinEmp : " + courseOfferingRepository.getMinEmployees(courseName) 
                    + " MaxEmp : " + courseOfferingRepository.getMaxEmployees(courseName));
        */
        
        return courseId;
    }
}
