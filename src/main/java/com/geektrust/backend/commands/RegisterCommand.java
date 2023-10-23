package com.geektrust.backend.commands;

import java.util.List;
import com.geektrust.backend.exceptions.CourseAlreadyAssignedException;
import com.geektrust.backend.exceptions.CourseFullException;
import com.geektrust.backend.exceptions.CourseOfferingNotAvailable;
import com.geektrust.backend.exceptions.InputDataErrorException;
import com.geektrust.backend.exceptions.WhiteSpacesInFieldsException;
import com.geektrust.backend.services.IRegistrationService;

public class RegisterCommand implements ICommand {
    
    private IRegistrationService registrationService;

    public RegisterCommand(IRegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @Override
    public void execute(List<String> arguments) {
        int expectedArguements = 3;
        try {
                if(arguments.size() > expectedArguements)
                    throw new WhiteSpacesInFieldsException("Remove whitespaces from input fields");

                if(arguments.size() < expectedArguements 
                    || !arguments.get(1).contains("@") 
                    || !arguments.get(1).contains(".COM"))
                    throw new InputDataErrorException("Input Data Error");
                
        } catch (WhiteSpacesInFieldsException wfe) {
            System.out.println("REMOVE WHITE SPACES FROM INPUT FIELDS");
            return;
        } catch (InputDataErrorException ide) {
            System.out.println("INPUT_DATA_ERROR");
            return;
        }

        String emailId = arguments.get(1);
        String courseId = arguments.get(2);
        String registrationId = "";

        try {
            registrationId = registrationService.registerEmployee(emailId, courseId);
        } catch (CourseFullException cfe) {
            System.out.print("COURSE_FULL_ERROR");
        } catch (CourseOfferingNotAvailable cnae) {
            System.out.print("COURSE NOT AVAILABLE");
        } catch (CourseAlreadyAssignedException ca) {
            System.out.print("COURSE ALREADY ASSIGNED");
        }

        System.out.println(registrationId);
    }
}
