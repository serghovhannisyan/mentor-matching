package com.mentorcliq.mentormatching.matcher;

import com.mentorcliq.mentormatching.model.Employee;
import com.mentorcliq.mentormatching.model.Pair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmployeeAgeMatcher<T extends Employee> implements EmployeeMatchingStrategy<Integer, T> {

    private final Integer agePercentage;
    private final Integer ageDiff;

    public EmployeeAgeMatcher(@Value("${matching.age.percentage}") Integer agePercentage,
                              @Value("${matching.age.diff}") Integer ageDiff) {
        this.agePercentage = agePercentage;
        this.ageDiff = ageDiff;
    }

    @Override
    public Integer calculateMatch(Pair<T> pair) {
        Employee e1 = pair.getFirst();
        Employee e2 = pair.getSecond();
        return Math.abs(e1.getAge() - e2.getAge()) <= ageDiff ? agePercentage : 0;
    }
}
