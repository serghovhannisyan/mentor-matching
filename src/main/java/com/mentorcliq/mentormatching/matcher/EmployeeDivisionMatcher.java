package com.mentorcliq.mentormatching.matcher;

import com.mentorcliq.mentormatching.model.Employee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmployeeDivisionMatcher implements EmployeeMatchingStrategy {

    private final Integer divisionPercentage;

    public EmployeeDivisionMatcher(@Value("${matching.division.percentage}") Integer divisionPercentage) {
        this.divisionPercentage = divisionPercentage;
    }

    /**
     * Calculates match based on division
     * @param e1 first employee
     * @param e2 second employee
     * @return match value
     */
    @Override
    public int calculateMatch(Employee e1, Employee e2) {
        return e1.getDivision().equals(e2.getDivision()) ? divisionPercentage : 0;
    }
}
