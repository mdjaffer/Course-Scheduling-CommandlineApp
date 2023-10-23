package com.geektrust.backend;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import com.geektrust.backend.exceptions.CourseOfferingNotAvailable;
import com.geektrust.backend.repositories.CourseOfferingRepository;
import com.geektrust.backend.repositories.EmployeeRepository;
import com.geektrust.backend.repositories.RegistrationRepository;
import com.geektrust.backend.services.AllotmentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("AllotmentServiceTest")
@ExtendWith(MockitoExtension.class)
public class AllotmentServiceTest {

    @Mock
    private CourseOfferingRepository courseOfferingRepositoryMock;

    @Mock
    private RegistrationRepository registrationRepositoryMock;

    @Mock
    private EmployeeRepository employeeRepositoryMock;
    
    @InjectMocks
    private AllotmentService allotmentService;

    @Test
    @DisplayName("Test allot course thrwos course not available exception")
    public void allotCourseExceptionTest() {

        //Arrange
        when(courseOfferingRepositoryMock.isCourseAlreadyAvailable(anyString())).thenReturn(false);

        //Act and Assert
        Assertions.assertThrows(CourseOfferingNotAvailable.class,
                    () -> allotmentService.allotCourse("OFFERING-JAVA-JOHN"));
    }
}
