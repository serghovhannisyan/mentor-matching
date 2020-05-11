package com.mentorcliq.mentormatching.matcher;

import com.mentorcliq.mentormatching.model.Employee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmployeeTimezoneMatcher implements EmployeeMatchingStrategy {

    private final Integer timezonePercentage;

    public EmployeeTimezoneMatcher(@Value("${matching.timezone.percentage}") Integer timezonePercentage) {
        this.timezonePercentage = timezonePercentage;
    }

    /**
     * Calculates match based on timezone
     * @param e1 first employee
     * @param e2 second employee
     * @return match value
     */
    @Override
    public int calculateMatch(Employee e1, Employee e2) {
        return e1.getTimezone() == e2.getTimezone() ? timezonePercentage : 0;
    }
}
