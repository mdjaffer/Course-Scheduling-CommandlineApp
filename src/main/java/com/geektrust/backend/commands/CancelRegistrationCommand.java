package com.geektrust.backend.commands;

import java.util.List;
import com.geektrust.backend.exceptions.InputDataErrorException;
import com.geektrust.backend.exceptions.RegistrationNotAvailableException;
import com.geektrust.backend.exceptions.WhiteSpacesInFieldsException;
import com.geektrust.backend.services.IRegistrationService;

public class CancelRegistrationCommand implements ICommand {
    
    private IRegistrationService registrationService;

    public CancelRegistrationCommand(IRegistrationService registrationService) {
        this.registrationService = registrationService;
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

        String registrationId = arguments.get(1);
        String result = "";

        try {
                result = registrationService.cancleRegistration(registrationId);
        } catch (RegistrationNotAvailableException rne) {
            System.out.print("REGISTRATION NOT AVAILABLE");
        }

        System.out.println(result);
    }
}
