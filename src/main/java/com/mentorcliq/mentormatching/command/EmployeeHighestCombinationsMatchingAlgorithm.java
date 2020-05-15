package com.mentorcliq.mentormatching.command;

import com.mentorcliq.mentormatching.model.Employee;
import com.mentorcliq.mentormatching.model.Match;
import com.mentorcliq.mentormatching.model.Pair;
import com.mentorcliq.mentormatching.service.calculation.MatchingCalculationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class EmployeeHighestCombinationsMatchingAlgorithm extends HighestCombinationsMatchingAlgorithm<Employee> {

    private final MatchingCalculationService<Employee> employeeMatchingCalculationService;

    public EmployeeHighestCombinationsMatchingAlgorithm(@Qualifier("employeeMatchingCalculation") MatchingCalculationService<Employee> employeeMatchingCalculationService) {
        this.employeeMatchingCalculationService = employeeMatchingCalculationService;
    }

    @Override
    protected Match getMatch(Pair<Employee> pair) {
        return employeeMatchingCalculationService.calculate(pair);
    }
}
