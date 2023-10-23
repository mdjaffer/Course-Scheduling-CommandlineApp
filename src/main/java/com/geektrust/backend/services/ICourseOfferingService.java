package com.geektrust.backend.services;

import com.geektrust.backend.exceptions.DuplicateCourseException;
import com.geektrust.backend.exceptions.IncorrectEmployeeNumbers;
import com.geektrust.backend.exceptions.InstructorAlreadyAssignedException;
import com.geektrust.backend.exceptions.InvalidDateException;

public interface ICourseOfferingService {
    public String addCourseOffering(String courseName, String instructorName,
                String date, int minEmp, int maxEmp)
            throws DuplicateCourseException, InstructorAlreadyAssignedException,
                IncorrectEmployeeNumbers, InvalidDateException;
}
