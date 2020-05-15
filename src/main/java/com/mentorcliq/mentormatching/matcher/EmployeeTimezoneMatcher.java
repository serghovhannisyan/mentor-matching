package com.mentorcliq.mentormatching.matcher;

import com.mentorcliq.mentormatching.model.Employee;
import com.mentorcliq.mentormatching.model.Pair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmployeeTimezoneMatcher<T extends Employee> implements EmployeeMatchingStrategy<Integer, T> {

    private final Integer timezonePercentage;

    public EmployeeTimezoneMatcher(@Value("${matching.timezone.percentage}") Integer timezonePercentage) {
        this.timezonePercentage = timezonePercentage;
    }

    @Override
    public Integer calculateMatch(Pair<T> pair) {
        Employee e1 = pair.getFirst();
        Employee e2 = pair.getSecond();
        return e1.getTimezone() == e2.getTimezone() ? timezonePercentage : 0;
    }
}
