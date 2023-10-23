package com.geektrust.backend.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class RegistrationRepository {
    
    private Map<String, String> emailCourseMap;
    private Map<String, String> registrationEmailMap;

    public RegistrationRepository() {
        emailCourseMap = new HashMap<String, String>();
        registrationEmailMap = new HashMap<String, String>();
    }
    
    public String saveRegistration(String emailId, String courseOfferingId) {

        String registrationId = "";

        emailCourseMap.put(emailId, courseOfferingId);
        registrationId = "REG-COURSE-" + emailId.split("@")[0] + "-" + courseOfferingId.split("-")[1];
        registrationEmailMap.put(registrationId, emailId);
        //ystem.out.println("RegistrationId : " + registrationId);
        return registrationId + " ACCEPTED";
    }

    public boolean isCourseAlreadyAssigned(String emailId, String courseId) {
        if(courseId.equals(emailCourseMap.get(emailId))) {
            return true;
        } else {
            return false;
        }
    }
    
    public int getNumberOfEmployees(String courseId) {
        
        int count = 0;
        List<String> courseOfferingList = new ArrayList<String>(emailCourseMap.values());

        for(String courseList : courseOfferingList) {
            if(courseList.equals(courseId)) {
                count++;
            }
        }

        return count;
    }
    
    public String getRegisteredEmailId(String registrationId) {
        return registrationEmailMap.get(registrationId);
    }

    public String getRegistrationId(String emailId) {

        String result = "";
        
        for(Entry<String, String> entry: registrationEmailMap.entrySet()) {
            if(entry.getValue().equals(emailId)) {
                result = entry.getKey();
                break;
            }
        }

        return result;
    }
    
    public List<String> getEmailIds(String courseId) {
       
        List<String> emailIdList = new ArrayList<String>();
        
        for(Entry<String, String> entry: emailCourseMap.entrySet()) {
            if(entry.getValue().equals(courseId)) {
                emailIdList.add(entry.getKey());
            }
        }

        return emailIdList;
    }

    public boolean isRegistrationAvailable(String registrationId) {
        return registrationEmailMap.containsKey(registrationId);
    }

    public String cancleRegistration(String registrationId) {

        String emailId = "";
        
        emailId = registrationEmailMap.get(registrationId);

        emailCourseMap.remove(emailId);
        registrationEmailMap.remove(registrationId);
        
        return "CANCEL_ACCEPTED";
    }
}
