package com.geektrust.backend;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import com.geektrust.backend.exceptions.DuplicateCourseException;
import com.geektrust.backend.exceptions.IncorrectEmployeeNumbers;
import com.geektrust.backend.exceptions.InstructorAlreadyAssignedException;
import com.geektrust.backend.exceptions.InvalidDateException;
import com.geektrust.backend.repositories.CourseOfferingRepository;
import com.geektrust.backend.repositories.InstructorRepository;
import com.geektrust.backend.services.CourseOfferingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("CourseOfferingServiceTest")
@ExtendWith(MockitoExtension.class)
public class CourseOfferingServiceTest {

    @Mock
    private CourseOfferingRepository courseOfferingRepositoryMock;

    @Mock
    private InstructorRepository instructorRepositoryMock;

    @InjectMocks
    private CourseOfferingService courseOfferingService;

    @Test
    @DisplayName("Test Adding new course offering")
    public void addNewCourseOfferingTest() {

        //Arrange
        String expectedCourseOffering = "OFFERING-JAVA-JAMES";
        String actualCourseOffering = "";

        when(courseOfferingRepositoryMock.saveCourseOffering(anyString(), 
                                            anyString(),
                                            anyString(),
                                            anyInt(),
                                            anyInt())).thenReturn(expectedCourseOffering);
        
        //Act 
        actualCourseOffering = courseOfferingService.addCourseOffering(
                                                        "PYTHON", "JAMES", "15022022", 1, 4);
        
        //Assert
        Assertions.assertEquals(expectedCourseOffering, actualCourseOffering);
    }

    @Test
    @DisplayName("Test Dupicate course exception")
    public void duplicateCourseExceptionTest() {

        //Arrange
        when(courseOfferingRepositoryMock.isCourseAlreadyAvailable(anyString())).thenReturn(true);
        
        //Act and Assert
        Assertions.assertThrows(
                    DuplicateCourseException.class, 
                    () -> courseOfferingService
                    .addCourseOffering("PYTHON", "JAMES", "15022022", 1, 4));
        
    }

    @Test
    @DisplayName("Test Instructor already assigned exception")
    public void instructorAlreadyAssignedExceptionTest() {

        //Arrange
        when(instructorRepositoryMock.isInstructorAvailable(anyString())).thenReturn(true);
        
        //Act and Assert
        Assertions.assertThrows(
                    InstructorAlreadyAssignedException.class, 
                    () -> courseOfferingService
                    .addCourseOffering("PYTHON", "JAMES", "15022022", 1, 4));
        
    }

    @Test
    @DisplayName("Test Invalid date exception")
    public void InvalidDateExceptionTest() {

        //Arrange
        String invalidDate = "55";
                
        //Act and Assert
        Assertions.assertThrows(
                    InvalidDateException.class, 
                    () -> courseOfferingService
                    .addCourseOffering("PYTHON", "JAMES", invalidDate, 1, 4));
        
    }

    @Test
    @DisplayName("Test Invalid minimum employees")
    public void IncorrectEmployeeNumbersExceptionMinEmp() {

        //Arrange
        int invalidMinEmpNum = -1;
                
        //Act and Assert
        Assertions.assertThrows(
                    IncorrectEmployeeNumbers.class, 
                    () -> courseOfferingService
                    .addCourseOffering("PYTHON", "JAMES", "15052022", invalidMinEmpNum, 4));
        
    }

    @Test
    @DisplayName("Test Invalid maximum employees")
    public void IncorrectEmployeeNumbersExceptionMaxEmp() {

        //Arrange
        int invalidMaxEmpNum = -1;
                
        //Act and Assert
        Assertions.assertThrows(
                    IncorrectEmployeeNumbers.class, 
                    () -> courseOfferingService
                    .addCourseOffering("PYTHON", "JAMES", "15052022", 1, invalidMaxEmpNum));
        
    }

}
