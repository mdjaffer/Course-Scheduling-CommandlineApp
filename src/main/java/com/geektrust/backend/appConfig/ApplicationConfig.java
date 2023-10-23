package com.geektrust.backend.appConfig;

import com.geektrust.backend.commands.AddCourseOfferingsCommand;
import com.geektrust.backend.commands.AllotCommand;
import com.geektrust.backend.commands.CancelRegistrationCommand;
import com.geektrust.backend.commands.CommandInvoker;
import com.geektrust.backend.commands.RegisterCommand;
import com.geektrust.backend.repositories.CourseOfferingRepository;
import com.geektrust.backend.repositories.EmployeeRepository;
import com.geektrust.backend.repositories.InstructorRepository;
import com.geektrust.backend.repositories.RegistrationRepository;
import com.geektrust.backend.services.AllotmentService;
import com.geektrust.backend.services.CourseOfferingService;
import com.geektrust.backend.services.RegistrationService;

public class ApplicationConfig {
    
    private final CourseOfferingRepository courseOfferingRepository = new CourseOfferingRepository();
    private final InstructorRepository instructorRepository = new InstructorRepository();
    private final RegistrationRepository registrationRepository = new RegistrationRepository();
    private final EmployeeRepository employeeRepository = new EmployeeRepository();
    
    private final CourseOfferingService courseOfferingService = 
                                            new CourseOfferingService(courseOfferingRepository, instructorRepository);

    private final RegistrationService registrationService = 
                                        new RegistrationService(courseOfferingRepository, registrationRepository, employeeRepository);

    private final AllotmentService allotmentService =
                                    new AllotmentService(courseOfferingRepository, registrationRepository, employeeRepository);

    private final AddCourseOfferingsCommand addCourseOfferingsCommand = new AddCourseOfferingsCommand(courseOfferingService);
    private final RegisterCommand registerCommand = new RegisterCommand(registrationService);
    private final CancelRegistrationCommand cancelRegistrationCommand = new CancelRegistrationCommand(registrationService);
    private final AllotCommand allotCommand = new AllotCommand(allotmentService);

    private final CommandInvoker commandInvoker = new CommandInvoker();

    public CommandInvoker getCommandInvoker() {
        commandInvoker.register("ADD-COURSE-OFFERING", addCourseOfferingsCommand);
        commandInvoker.register("REGISTER", registerCommand);
        commandInvoker.register("CANCEL", cancelRegistrationCommand);
        commandInvoker.register("ALLOT", allotCommand);
        
        return commandInvoker;
    }
}
