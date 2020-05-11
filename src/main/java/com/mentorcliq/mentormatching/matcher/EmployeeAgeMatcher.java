package com.mentorcliq.mentormatching.matcher;

import com.mentorcliq.mentormatching.model.Employee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmployeeAgeMatcher implements EmployeeMatchingStrategy {

    private final Integer agePercentage;
    private final Integer ageDiff;

    public EmployeeAgeMatcher(@Value("${matching.age.percentage}") Integer agePercentage,
                              @Value("${matching.age.diff}") Integer ageDiff) {
        this.agePercentage = agePercentage;
        this.ageDiff = ageDiff;
    }

    /**
     * Calculates match based on age
     * @param e1 first employee
     * @param e2 second employee
     * @return match value
     */
    @Override
    public int calculateMatch(Employee e1, Employee e2) {
        return Math.abs(e1.getAge() - e2.getAge()) <= ageDiff ? agePercentage : 0;
    }
}
