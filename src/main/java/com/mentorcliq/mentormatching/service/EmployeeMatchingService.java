package com.mentorcliq.mentormatching.service;

import com.mentorcliq.mentormatching.command.MatchingAlgorithm;
import com.mentorcliq.mentormatching.model.Combination;
import com.mentorcliq.mentormatching.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeMatchingService {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final MatchingAlgorithm matchingAlgorithm;

    public EmployeeMatchingService(@Qualifier("highestCombinationsMatchingAlgorithm") MatchingAlgorithm matchingAlgorithm) {
        this.matchingAlgorithm = matchingAlgorithm;
        log.info("matching algorithm. {}", this.matchingAlgorithm);
    }

    /**
     * Generates all possible variations of employees
     *
     * @param items
     * @return list of combinations
     */
    public List<Combination> computeAllCombinations(Set<Employee> items) {
        return matchingAlgorithm.execute(items);
    }
}
