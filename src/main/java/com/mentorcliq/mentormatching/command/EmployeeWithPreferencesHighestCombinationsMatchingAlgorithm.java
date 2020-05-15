package com.mentorcliq.mentormatching.command;

import com.mentorcliq.mentormatching.model.Combination;
import com.mentorcliq.mentormatching.model.EmployeeWithPreferences;
import com.mentorcliq.mentormatching.model.Match;
import com.mentorcliq.mentormatching.model.Pair;
import com.mentorcliq.mentormatching.service.calculation.MatchingCalculationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
public class EmployeeWithPreferencesHighestCombinationsMatchingAlgorithm extends HighestCombinationsMatchingAlgorithm<EmployeeWithPreferences> {

    private final MatchingCalculationService<EmployeeWithPreferences> employeeWithPreferencesMatchingCalculationService;

    public EmployeeWithPreferencesHighestCombinationsMatchingAlgorithm(@Qualifier("employeeWithPreferenceMatchingCalculation") MatchingCalculationService<EmployeeWithPreferences> employeeWithPreferencesMatchingCalculationService) {
        this.employeeWithPreferencesMatchingCalculationService = employeeWithPreferencesMatchingCalculationService;
    }

    @Override
    protected Match getMatch(Pair<EmployeeWithPreferences> pair) {
        return employeeWithPreferencesMatchingCalculationService.calculate(pair);
    }

    @Override
    protected void handleFinalResult(Combination<EmployeeWithPreferences> newCombination, LinkedList<Combination<EmployeeWithPreferences>> finalResult) {
        if (newCombination.isMatched()) {
            super.handleFinalResult(newCombination, finalResult);
        }
    }
}
