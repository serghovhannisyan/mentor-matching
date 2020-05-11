package com.mentorcliq.mentormatching.command;

import com.mentorcliq.mentormatching.model.Combination;
import com.mentorcliq.mentormatching.model.Employee;

import java.util.List;
import java.util.Set;

public interface MatchingAlgorithm {

    /**
     * Generates combination of group of employees
     * @param items
     * @return list of combinations
     */
    List<Combination> execute(Set<Employee> items);
}