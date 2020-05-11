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
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
public class HighestCombinationsMatchingAlgorithm implements MatchingAlgorithm {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final List<EmployeeMatchingStrategy> strategies;

    public HighestCombinationsMatchingAlgorithm(EmployeeMatchingStrategyFactory employeeMatchingStrategyFactory) {
        this.strategies = employeeMatchingStrategyFactory.getStrategies();
        log.info("matching strategies loaded. {}", this.strategies);
    }

    /**
     * Generates all possible variations of given set of employees
     * And takes the highest average matching sets
     *
     * @param items
     * @return list of combinations with highest match
     */
    @Override
    public List<Combination> execute(Set<Employee> items) {
        log.info("execute() has called. number of employees {}", items.size());
        LinkedList<Combination> finalResult = new LinkedList<>();
        compute(items, new LinkedList<>(), finalResult);

        return finalResult;
    }

    private void compute(Set<Employee> items, List<Pair> currentResults, LinkedList<Combination> finalResult) {
        if (items.size() < 2) {
            // once size is 0 - currentResult contains one full combinations
            Combination newCombination = new Combination(currentResults);
            // we can keep all the combinations in finalResults as well
            // but if I'm not mistaken only the highest ones should be shown
            if (finalResult.isEmpty()) {
                finalResult.add(newCombination);
            } else {
                Combination latestCombination = finalResult.getLast();
                if (newCombination.getAverageMatching() > latestCombination.getAverageMatching()) {
                    finalResult.clear();
                    finalResult.add(newCombination);
                    log.info("new item with higher matching has been added to the list. {}", newCombination);
                } else if (newCombination.getAverageMatching().equals(latestCombination.getAverageMatching())) {
                    finalResult.add(newCombination);
                    log.info("new item with the same higher matching has been added to the list. {}", newCombination);
                }
            }
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
