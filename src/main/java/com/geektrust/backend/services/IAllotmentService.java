package com.geektrust.backend.services;

import java.util.List;
import com.geektrust.backend.exceptions.CourseOfferingNotAvailable;

public interface IAllotmentService {
    public List<String> allotCourse(String courseId)
                            throws CourseOfferingNotAvailable;
}
