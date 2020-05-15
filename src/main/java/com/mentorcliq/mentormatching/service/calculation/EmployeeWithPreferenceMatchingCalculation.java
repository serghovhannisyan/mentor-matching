package com.mentorcliq.mentormatching.service.calculation;

import com.mentorcliq.mentormatching.matcher.EmployeeMatchingStrategy;
import com.mentorcliq.mentormatching.model.EmployeeWithPreferences;
import com.mentorcliq.mentormatching.model.Match;
import com.mentorcliq.mentormatching.model.Pair;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class EmployeeWithPreferenceMatchingCalculation<T extends EmployeeWithPreferences> implements MatchingCalculationService<T> {

    private final MatchingCalculationService<T> employeeMatchingCalculationService;
    private final EmployeeMatchingStrategy<Boolean, T> employeePreferenceMatchingStrategy;

    public EmployeeWithPreferenceMatchingCalculation(
            @Qualifier("employeeMatchingCalculation") MatchingCalculationService<T> employeeMatchingCalculationService,
            @Qualifier("employeePreferenceMatcher") EmployeeMatchingStrategy<Boolean, T> employeePreferenceMatchingStrategy) {
        this.employeeMatchingCalculationService = employeeMatchingCalculationService;
        this.employeePreferenceMatchingStrategy = employeePreferenceMatchingStrategy;
    }

    @Override
    public Match calculate(Pair<T> pair) {
        Match match = new Match();
        Boolean isMatched = employeePreferenceMatchingStrategy.calculateMatch(pair);
        if (isMatched) {
            match.setMatched(true);
            match.setMatchingPercentage(employeeMatchingCalculationService.calculate(pair).getMatchingPercentage());
        }
        return match;
    }

}
