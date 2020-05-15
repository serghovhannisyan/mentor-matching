package com.mentorcliq.mentormatching.service.calculation;

import com.mentorcliq.mentormatching.model.Employee;
import com.mentorcliq.mentormatching.model.Match;
import com.mentorcliq.mentormatching.model.Pair;

public interface MatchingCalculationService<T extends Employee> {

    Match calculate(Pair<T> pair);
}
