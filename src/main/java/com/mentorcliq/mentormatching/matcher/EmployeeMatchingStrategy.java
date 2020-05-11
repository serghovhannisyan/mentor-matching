package com.mentorcliq.mentormatching.matcher;

import com.mentorcliq.mentormatching.model.Employee;

public interface EmployeeMatchingStrategy {

    /**
     * Calculates match between two employees based on matching criteria
     * @param e1 first employee
     * @param e2 second employee
     * @return calculated value
     */
    int calculateMatch(Employee e1, Employee e2);
}
