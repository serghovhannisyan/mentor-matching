package com.mentorcliq.mentormatching.matcher;

import com.mentorcliq.mentormatching.model.Employee;
import com.mentorcliq.mentormatching.model.Pair;

public interface EmployeeMatchingStrategy<T, E extends Employee> {

    T calculateMatch(Pair<E> pair);
}
