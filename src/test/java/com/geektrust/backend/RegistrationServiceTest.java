package com.geektrust.backend;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import com.geektrust.backend.exceptions.CourseAlreadyAssignedException;
import com.geektrust.backend.exceptions.CourseFullException;
import com.geektrust.backend.exceptions.CourseOfferingNotAvailable;
import com.geektrust.backend.exceptions.RegistrationNotAvailableException;
import com.geektrust.backend.repositories.CourseOfferingRepository;
import com.geektrust.backend.repositories.EmployeeRepository;
import com.geektrust.backend.repositories.RegistrationRepository;
import com.geektrust.backend.services.RegistrationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("RegistrationServiceTest")
@ExtendWith(MockitoExtension.class)
public class RegistrationServiceTest {
    @Mock
    private CourseOfferingRepository courseOfferingRepositoryMock;

    @Mock
    private RegistrationRepository registrationRepositoryMock;

    @Mock
    private EmployeeRepository employeeRepositoryMock;

    @InjectMocks
    private RegistrationService registrationService;

    @Test
    @DisplayName("Test Registering employee")
    public void registerEmployeeTest() {

        //Arrange
        String expectedRegistrationId = "REG-COURSE-JAFFER-PYTHON";
        String actualRegistrationId = "";

        when(registrationRepositoryMock.saveRegistration(anyString(), anyString())).thenReturn(expectedRegistrationId);
        when(courseOfferingRepositoryMock.isCourseAlreadyAvailable(anyString())).thenReturn(true);
        when(registrationRepositoryMock.isCourseAlreadyAssigned(anyString(), anyString())).thenReturn(false);
        when(registrationRepositoryMock.getNumberOfEmployees(anyString())).thenReturn(3);
        when(courseOfferingRepositoryMock.getMaxEmployees(anyString())).thenReturn(5);
                
        //Act 
        actualRegistrationId = registrationService.registerEmployee("JAFFER@GMAIL.COM", "OFFERING-PYTHON-JAMES");
        
        //Assert
        Assertions.assertEquals(expectedRegistrationId, actualRegistrationId);
    }

    @Test
    @DisplayName("Test Course full exception")
    public void courseFullExceptionTest() {

        //Arrange
        when(courseOfferingRepositoryMock.isCourseAlreadyAvailable(anyString())).thenReturn(true);
        when(registrationRepositoryMock.isCourseAlreadyAssigned(anyString(), anyString())).thenReturn(false);
        when(registrationRepositoryMock.getNumberOfEmployees(anyString())).thenReturn(6);
        when(courseOfferingRepositoryMock.getMaxEmployees(anyString())).thenReturn(5);

        //Act and Assert
        Assertions.assertThrows(CourseFullException.class, 
                    () -> registrationService.registerEmployee("JAFFER@GMAIL.COM", "OFFERING-PYTHON-JAMES"));
    
    }

    @Test
    @DisplayName("Test Course Offering not available exception")
    public void courseOfferingNotAvailableTest() {

        //Arrange
        when(courseOfferingRepositoryMock.isCourseAlreadyAvailable(anyString())).thenReturn(false);
        
        //Act and Assert
        Assertions.assertThrows(CourseOfferingNotAvailable.class, 
                    () -> registrationService.registerEmployee("JAFFER@GMAIL.COM", "OFFERING-PYTHON-JAMES"));
    
    }    

    @Test
    @DisplayName("Test Course already assigned exception")
    public void courseAlreadyAssignedExceptionTest() {

        //Arrange
        when(courseOfferingRepositoryMock.isCourseAlreadyAvailable(anyString())).thenReturn(true);
        when(registrationRepositoryMock.isCourseAlreadyAssigned(anyString(), anyString())).thenReturn(true);
        
        //Act and Assert
        Assertions.assertThrows(CourseAlreadyAssignedException.class, 
                    () -> registrationService.registerEmployee("JAFFER@GMAIL.COM", "OFFERING-PYTHON-JAMES"));
    
    }

    @Test
    @DisplayName("Test Cancel registration")
    public void cancelRegistrationTest() {

        //Arrange
        when(registrationRepositoryMock.isRegistrationAvailable(anyString())).thenReturn(false);
        
        //Act and Assert
        Assertions.assertThrows(RegistrationNotAvailableException.class, 
        () -> registrationService.cancleRegistration("REG-COURSE-SAM-JAVA")); 
    
    }

}
