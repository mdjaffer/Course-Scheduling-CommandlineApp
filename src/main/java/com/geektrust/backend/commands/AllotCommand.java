package com.geektrust.backend.commands;

import java.util.ArrayList;
import java.util.List;
import com.geektrust.backend.exceptions.CourseOfferingNotAvailable;
import com.geektrust.backend.exceptions.InputDataErrorException;
import com.geektrust.backend.exceptions.WhiteSpacesInFieldsException;
import com.geektrust.backend.services.IAllotmentService;

public class AllotCommand implements ICommand {
    
    private IAllotmentService allotmentService;

    public AllotCommand(IAllotmentService allotmentService) {
        this.allotmentService = allotmentService;
    }

    @Override
    public void execute(List<String> arguments) {
        int expectedArguements = 2;
        try {
                if(arguments.size() > expectedArguements)
                    throw new WhiteSpacesInFieldsException("Remove whitespaces from input fields");

                if(arguments.size() < expectedArguements)
                    throw new InputDataErrorException("Input Data Error");
                
        } catch (WhiteSpacesInFieldsException wfe) {
            System.out.print("REMOVE WHITE SPACES FROM INPUT FIELDS");
            return;
        } catch (InputDataErrorException ide) {
            System.out.print("INPUT_DATA_ERROR");
            return;
        }

        String courseId = arguments.get(1);
        List<String> allotmentList = new ArrayList<String>();

        try {
            allotmentList = allotmentService.allotCourse(courseId);
        } catch (CourseOfferingNotAvailable cne) {
            System.out.print("COURSE NOT AVAILABLE");
        }

        for(String allotment : allotmentList)
            System.out.println(allotment);

    }
}
