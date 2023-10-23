package com.geektrust.backend.services;

import com.geektrust.backend.exceptions.CourseFullException;
import com.geektrust.backend.exceptions.CourseOfferingNotAvailable;
import com.geektrust.backend.exceptions.RegistrationNotAvailableException;

public interface IRegistrationService {
    
    public String registerEmployee(String emailId, String courseId)
                    throws CourseFullException, CourseOfferingNotAvailable;

    public String cancleRegistration(String registrationId)
                    throws RegistrationNotAvailableException;
}
