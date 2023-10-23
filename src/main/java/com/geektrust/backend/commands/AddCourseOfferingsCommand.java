package com.geektrust.backend.commands;

import java.util.List;
import com.geektrust.backend.exceptions.DuplicateCourseException;
import com.geektrust.backend.exceptions.IncorrectEmployeeNumbers;
import com.geektrust.backend.exceptions.InputDataErrorException;
import com.geektrust.backend.exceptions.InstructorAlreadyAssignedException;
import com.geektrust.backend.exceptions.InvalidDateException;
import com.geektrust.backend.exceptions.WhiteSpacesInFieldsException;
import com.geektrust.backend.services.ICourseOfferingService;

public class AddCourseOfferingsCommand implements ICommand {
    
    private ICourseOfferingService courseOfferingService;

    public AddCourseOfferingsCommand(ICourseOfferingService courseOfferingService) {
        this.courseOfferingService = courseOfferingService;
    }

    @Override
    public void execute(List<String> arguments) {
        int expectedArguements = 6;
        try {
                if(arguments.size() > expectedArguements)
                    throw new WhiteSpacesInFieldsException("Remove whitespaces from input fields");

                if(arguments.size() < expectedArguements)
                    throw new InputDataErrorException("Input Data Error");
                
        } catch (WhiteSpacesInFieldsException wfe) {
            System.out.println("REMOVE WHITE SPACES FROM INPUT FIELDS");
            return;
        } catch (InputDataErrorException ide) {
            System.out.println("INPUT_DATA_ERROR");
            return;
        }

        String courseId = "";
        String courseName = arguments.get(1);
        String instructorName = arguments.get(2);
        String date = arguments.get(3);
        int minEmp = Integer.parseInt(arguments.get(4));
        int maxEmp = Integer.parseInt(arguments.get(5));

        try {
                courseId = courseOfferingService
                            .addCourseOffering(courseName, instructorName, date, minEmp, maxEmp);
        } catch (IncorrectEmployeeNumbers iemp) {
            System.out.println("PROVIDE VALID MINIMUM AND MAXIMUM EMPLOYEES");
        } catch (DuplicateCourseException dpc) {
            System.out.println("COURSE ALREADY AVAILABLE");
        } catch (InstructorAlreadyAssignedException iaa) {
            System.out.println("INSTRUCTOR ALREADY ASSIGNED TO OTHER COURSE");
        } catch (InvalidDateException invd) {
            System.out.println("INVALID DATE - PROVIDE TODAY's DATE OR FUTURE DATE");
        }
        
        System.out.println(courseId);
    }
}
