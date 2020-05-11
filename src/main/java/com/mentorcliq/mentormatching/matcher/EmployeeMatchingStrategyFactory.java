package com.mentorcliq.mentormatching.matcher;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeMatchingStrategyFactory {

    private final EmployeeMatchingStrategy employeeAgeMatchingStrategy;
    private final EmployeeMatchingStrategy employeeDivisionMatchingStrategy;
    private final EmployeeMatchingStrategy employeeTimezoneMatchingStrategy;

    public EmployeeMatchingStrategyFactory(@Qualifier("employeeAgeMatcher") EmployeeMatchingStrategy employeeAgeMatchingStrategy,
                                           @Qualifier("employeeDivisionMatcher") EmployeeMatchingStrategy employeeDivisionMatchingStrategy,
                                           @Qualifier("employeeTimezoneMatcher") EmployeeMatchingStrategy employeeTimezoneMatchingStrategy) {
        this.employeeAgeMatchingStrategy = employeeAgeMatchingStrategy;
        this.employeeDivisionMatchingStrategy = employeeDivisionMatchingStrategy;
        this.employeeTimezoneMatchingStrategy = employeeTimezoneMatchingStrategy;
    }

    /**
     * Factory method for matching algorithms
     * @return list of available strategies
     */
    public List<EmployeeMatchingStrategy> getStrategies() {
        return Arrays.asList(employeeAgeMatchingStrategy,
                employeeDivisionMatchingStrategy,
                employeeTimezoneMatchingStrategy);
    }
}
