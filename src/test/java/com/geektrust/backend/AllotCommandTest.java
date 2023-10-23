package com.geektrust.backend;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import com.geektrust.backend.commands.AllotCommand;
import com.geektrust.backend.services.AllotmentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("CancelRegistrationCommandTest")
@ExtendWith(MockitoExtension.class)
public class AllotCommandTest {
    
    private ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Mock
    private AllotmentService allotmentServiceMock;

    @InjectMocks
    private AllotCommand allotCommand;

    @Test
    @DisplayName("Allot command input data error")
    public void allotCommandInputDataErrorTest() {

        //Arrange
        List<String> command = new ArrayList<String>();
        command.add("ALLOT");
        //command.add("OFFERING-PYTHON-JOHN");

        PrintStream standardOut = System.out;       
        System.setOut(new PrintStream((OutputStream) outputStreamCaptor));

        //Act and Assert
        allotCommand.execute(command);

        Assertions.assertEquals("INPUT_DATA_ERROR", 
                    outputStreamCaptor.toString().trim());
        
        System.setOut(standardOut);
      
    }

    @Test
    @DisplayName("Allot command remove white spaces error")
    public void allotCommandCommandRemoveWhiteSpacesTest() {

        //Arrange
        List<String> command = new ArrayList<String>();
        command.add("ALLOT");
        command.add("OFFERING ");
        command.add(" PYTHON-JOHN");

        PrintStream standardOut = System.out;       
        System.setOut(new PrintStream((OutputStream) outputStreamCaptor));

        //Act and Assert
        allotCommand.execute(command);
        
        Assertions.assertEquals("REMOVE WHITE SPACES FROM INPUT FIELDS", 
                    outputStreamCaptor.toString().trim());
        
        System.setOut(standardOut);
      
    }
}
