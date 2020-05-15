package com.mentorcliq.mentormatching.service.calculation;

import com.mentorcliq.mentormatching.matcher.EmployeeMatchingStrategy;
import com.mentorcliq.mentormatching.model.Employee;
import com.mentorcliq.mentormatching.model.Match;
import com.mentorcliq.mentormatching.model.Pair;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMatchingCalculation<T extends Employee> implements MatchingCalculationService<T> {

    private final EmployeeMatchingStrategy<Integer, T> employeeAgeMatchingStrategy;
    private final EmployeeMatchingStrategy<Integer, T> employeeDivisionMatchingStrategy;
    private final EmployeeMatchingStrategy<Integer, T> employeeTimezoneMatchingStrategy;

    public EmployeeMatchingCalculation(@Qualifier("employeeAgeMatcher") EmployeeMatchingStrategy<Integer, T> employeeAgeMatchingStrategy,
                                       @Qualifier("employeeDivisionMatcher") EmployeeMatchingStrategy<Integer, T> employeeDivisionMatchingStrategy,
                                       @Qualifier("employeeTimezoneMatcher") EmployeeMatchingStrategy<Integer, T> employeeTimezoneMatchingStrategy) {
    this.employeeAgeMatchingStrategy = employeeAgeMatchingStrategy;
        this.employeeDivisionMatchingStrategy = employeeDivisionMatchingStrategy;
        this.employeeTimezoneMatchingStrategy = employeeTimezoneMatchingStrategy;
    }

    @Override
    public Match calculate(Pair<T> pair) {
        Match match = new Match();
        match.setMatchingPercentage(match.getMatchingPercentage() + employeeAgeMatchingStrategy.calculateMatch(pair));
        match.setMatchingPercentage(match.getMatchingPercentage() + employeeDivisionMatchingStrategy.calculateMatch(pair));
        match.setMatchingPercentage(match.getMatchingPercentage() + employeeTimezoneMatchingStrategy.calculateMatch(pair));

        return match;
    }

}
