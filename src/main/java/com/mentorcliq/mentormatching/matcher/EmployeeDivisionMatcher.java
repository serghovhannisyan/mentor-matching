package com.mentorcliq.mentormatching.matcher;

import com.mentorcliq.mentormatching.model.Employee;
import com.mentorcliq.mentormatching.model.Pair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmployeeDivisionMatcher<T extends Employee> implements EmployeeMatchingStrategy<Integer, T> {

    private final Integer divisionPercentage;

    public EmployeeDivisionMatcher(@Value("${matching.division.percentage}") Integer divisionPercentage) {
        this.divisionPercentage = divisionPercentage;
    }

    @Override
    public Integer calculateMatch(Pair<T> pair) {
        Employee e1 = pair.getFirst();
        Employee e2 = pair.getSecond();
        return e1.getDivision().equals(e2.getDivision()) ? divisionPercentage : 0;
    }
}
