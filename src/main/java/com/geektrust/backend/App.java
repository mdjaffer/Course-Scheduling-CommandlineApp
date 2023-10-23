package com.geektrust.backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.geektrust.backend.appConfig.ApplicationConfig;
import com.geektrust.backend.commands.CommandInvoker;
import com.geektrust.backend.exceptions.NoSuchCommandException;

public class App {

	public static void main(String[] args) {
		//System.out.println("Welcome to Geektrust Backend Challenge!");

		List<String> commandLineArgs = new ArrayList<>(Arrays.asList(args));

		ApplicationConfig applicationConfig = new ApplicationConfig();

		CommandInvoker commandInvoker = applicationConfig.getCommandInvoker();

		BufferedReader reader;

        String inputFile = commandLineArgs.get(0);
		
		commandLineArgs.remove(0);

		try {
            reader = new BufferedReader(new FileReader(inputFile));

            String line = reader.readLine();
            //System.out.println("Read Line : " + line);
			while (line != null) {
            
				List<String> tokens = Arrays.asList(line.split(" "));
            
				commandInvoker.executeCommand(tokens.get(0), tokens);
                // read next line
                line = reader.readLine();
            }
            
			reader.close();
			
        } catch (IOException | NoSuchCommandException e) {
            e.printStackTrace();
        }
		
	}

}
