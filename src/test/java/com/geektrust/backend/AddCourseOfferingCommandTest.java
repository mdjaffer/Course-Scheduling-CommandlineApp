package com.geektrust.backend;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import com.geektrust.backend.commands.AddCourseOfferingsCommand;
import com.geektrust.backend.services.CourseOfferingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("AddCourseOfferingTest")
@ExtendWith(MockitoExtension.class)
public class AddCourseOfferingCommandTest {
    
    private ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Mock
    private CourseOfferingService courseOfferingServiceMock;

    @InjectMocks
    private AddCourseOfferingsCommand addCourseOfferingsCommand;

    @Test
    @DisplayName("Add course offering command test input data error")
    public void addCourseOfferingCommandInputDataErrorTest() {

        //Arrange
        List<String> command = new ArrayList<String>();
        command.add("ADD-COURSE-OFFERING");
        command.add("PYTHON");
        //command.add("JOHN");
        command.add("05062022");
        command.add("1");
        command.add("3");
        PrintStream standardOut = System.out;       
        System.setOut(new PrintStream((OutputStream) outputStreamCaptor));

        //Act and Assert
        addCourseOfferingsCommand.execute(command);

        Assertions.assertEquals("INPUT_DATA_ERROR", 
                    outputStreamCaptor.toString().trim());
        
        System.setOut(standardOut);
      
    }

    @Test
    @DisplayName("Add course offering command test remove white spaces error")
    public void addCourseOfferingCommandRemoveWhiteSpacesTest() {

        //Arrange
        List<String> command = new ArrayList<String>();
        command.add("CANCEL");
        command.add("PYTHON");
        command.add("JO ");
        command.add("HN");
        command.add("05062022");
        command.add("1");
        command.add("3");
        PrintStream standardOut = System.out;       
        System.setOut(new PrintStream((OutputStream) outputStreamCaptor));

        //Act and Assert
        addCourseOfferingsCommand.execute(command);
        
        Assertions.assertEquals("REMOVE WHITE SPACES FROM INPUT FIELDS", 
                    outputStreamCaptor.toString().trim());
        
        System.setOut(standardOut);
      
    }
}
