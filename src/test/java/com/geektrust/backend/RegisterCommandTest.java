package com.geektrust.backend;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import com.geektrust.backend.commands.RegisterCommand;
import com.geektrust.backend.services.RegistrationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@DisplayName("RegistrationCommandTest")
@ExtendWith(MockitoExtension.class)
public class RegisterCommandTest {
    
    private ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Mock
    private RegistrationService registrationServiceMock;

    @InjectMocks
    private RegisterCommand registerCommand;

    @Test
    @DisplayName("Register command input data error")
    public void registerCommandInputDataErrorTest() {

        //Arrange
        List<String> command = new ArrayList<String>();
        command.add("REGISTER");
        //command.add("WOO@GMAIL.COM");
        command.add("OFFERING-PYTHON-JOHN");

        PrintStream standardOut = System.out;       
        System.setOut(new PrintStream((OutputStream) outputStreamCaptor));

        //Act and Assert
        registerCommand.execute(command);

        Assertions.assertEquals("INPUT_DATA_ERROR", 
                    outputStreamCaptor.toString().trim());
        
        System.setOut(standardOut);
      
    }

    @Test
    @DisplayName("Register c ommand remove white spaces error")
    public void registerCommandCommandRemoveWhiteSpacesTest() {

        //Arrange
        List<String> command = new ArrayList<String>();
        command.add("REGISTER");
        command.add("WOO@ ");
        command.add("GMAIL.COM");
        command.add("OFFERING-PYTHON-JOHN");

        PrintStream standardOut = System.out;       
        System.setOut(new PrintStream((OutputStream) outputStreamCaptor));

        //Act and Assert
        registerCommand.execute(command);
        
        Assertions.assertEquals("REMOVE WHITE SPACES FROM INPUT FIELDS", 
                    outputStreamCaptor.toString().trim());
        
        System.setOut(standardOut);
      
    }
}

