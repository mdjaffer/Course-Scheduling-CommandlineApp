package com.geektrust.backend;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import com.geektrust.backend.commands.CancelRegistrationCommand;
import com.geektrust.backend.services.RegistrationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("CancelRegistrationCommandTest")
@ExtendWith(MockitoExtension.class)
public class CancelRegistrationCommandTest {
    
    private ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Mock
    private RegistrationService registrationServiceMock;

    @InjectMocks
    private CancelRegistrationCommand cancelRegistrationCommand;

    @Test
    @DisplayName("Cancel register command input data error")
    public void cancelRegisterCommandInputDataErrorTest() {

        //Arrange
        List<String> command = new ArrayList<String>();
        command.add("CANCEL");
        //command.add("REG-COURSE-BOBY-PYTHON");

        PrintStream standardOut = System.out;       
        System.setOut(new PrintStream((OutputStream) outputStreamCaptor));

        //Act and Assert
        cancelRegistrationCommand.execute(command);

        Assertions.assertEquals("INPUT_DATA_ERROR", 
                    outputStreamCaptor.toString().trim());
        
        System.setOut(standardOut);
      
    }

    @Test
    @DisplayName("Cancel register command remove white spaces error")
    public void cancelRegisterCommandCommandRemoveWhiteSpacesTest() {

        //Arrange
        List<String> command = new ArrayList<String>();
        command.add("CANCEL");
        command.add("REG-COURSE ");
        command.add(" BOBY-PYTHON");

        PrintStream standardOut = System.out;       
        System.setOut(new PrintStream((OutputStream) outputStreamCaptor));

        //Act and Assert
        cancelRegistrationCommand.execute(command);
        
        Assertions.assertEquals("REMOVE WHITE SPACES FROM INPUT FIELDS", 
                    outputStreamCaptor.toString().trim());
        
        System.setOut(standardOut);
      
    }
}
