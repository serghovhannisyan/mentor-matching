package com.mentorcliq.mentormatching.command;

import com.mentorcliq.mentormatching.matcher.EmployeeMatchingStrategy;
import com.mentorcliq.mentormatching.matcher.EmployeeMatchingStrategyFactory;
import com.mentorcliq.mentormatching.model.Combination;
import com.mentorcliq.mentormatching.model.Employee;
import com.mentorcliq.mentormatching.model.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.*;

@Service
public class AllCombinationsMatchingAlgorithm implements MatchingAlgorithm {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final List<EmployeeMatchingStrategy> strategies;

    public AllCombinationsMatchingAlgorithm(EmployeeMatchingStrategyFactory employeeMatchingStrategyFactory) {
        this.strategies = employeeMatchingStrategyFactory.getStrategies();
        log.info("matching strategies loaded. {}", this.strategies);
    }

    /**
     * Generates all possible variations of given set of employees
     *
     * @param items
     * @return sorted list of combinations
     */
    @Override
    public List<Combination> execute(Set<Employee> items) {
        log.info("execute() has called. number of employees {}", items.size());
        List<Combination> finalResult = new LinkedList<>();
        compute(items, new LinkedList<>(), finalResult);

        Collections.sort(finalResult);

        return finalResult;
    }

    private void compute(Set<Employee> items, List<Pair> currentResults, List<Combination> finalResult) {
        if (items.size() < 2) {
            finalResult.add(new Combination(currentResults));
            return;
        }

        LinkedList<Employee> newItems = new LinkedList<>(items);
        Employee first = newItems.removeFirst();

        // duplicate items in order to remove first item and make pairs with the rest
        // 1 -> (i -> 2,3,4)
        for (int i = 0; i < newItems.size(); i++) {
            Employee second = newItems.get(i);
            Set<Employee> remainingItems = new LinkedHashSet<>(newItems);
            // remove second item of pair and pass the remaining items and do the same with existing items
            // 2 -> (3,4)
            remainingItems.remove(second);

            Pair pair = new Pair(first, second);
            pair.calculateMatch(strategies);

            currentResults.add(pair);
            compute(remainingItems, currentResults, finalResult);
            // once currentResults has been added to finalResult
            // we need to remove it to use next pair instead
            currentResults.remove(pair);
        }
    }
}
