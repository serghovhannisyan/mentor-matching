package com.mentorcliq.mentormatching.command;

import com.mentorcliq.mentormatching.model.Combination;
import com.mentorcliq.mentormatching.model.Employee;
import com.mentorcliq.mentormatching.model.Match;
import com.mentorcliq.mentormatching.model.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public abstract class HighestCombinationsMatchingAlgorithm<T extends Employee> {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * Generates all possible variations of given set of employees
     * And takes the highest average matching sets
     *
     * @param items
     * @return list of combinations with highest match
     */
    public List<Combination<T>> execute(Set<T> items) {
        log.info("execute() has called. number of employees {}", items.size());
        LinkedList<Combination<T>> finalResult = new LinkedList<>();
        compute(items, new LinkedList<>(), finalResult);

        return finalResult;
    }


    private void compute(Set<T> items, List<Pair<T>> currentResults, LinkedList<Combination<T>> finalResult) {
        if (items.size() < 2) {
            // once size is 0 - currentResult contains one full combinations
            Combination<T> newCombination = new Combination<>(currentResults);
            // we can keep all the combinations in finalResults as well
            // but if I'm not mistaken only the highest ones should be shown
            handleFinalResult(newCombination, finalResult);
            return;
        }

        LinkedList<T> newItems = new LinkedList<>(items);
        T first = newItems.removeFirst();

        // duplicate items in order to remove first item and make pairs with the rest
        // 1 -> (i -> 2,3,4)
        for (int i = 0; i < newItems.size(); i++) {
            T second = newItems.get(i);
            Set<T> remainingItems = new LinkedHashSet<>(newItems);
            // remove second item of pair and pass the remaining items and do the same with existing items
            // 2 -> (3,4)
            remainingItems.remove(second);

            Pair<T> pair = new Pair<>(first, second);
            pair.setMatch(getMatch(pair));

            currentResults.add(pair);
            compute(remainingItems, currentResults, finalResult);
            // once currentResults has been added to finalResult
            // we need to remove it to use next pair instead
            currentResults.remove(pair);
        }

    }

    protected abstract Match getMatch(Pair<T> pair);

    protected void handleFinalResult(Combination<T> combination, LinkedList<Combination<T>> finalResult) {
        if (finalResult.isEmpty()) {
            finalResult.add(combination);
        } else {
            Combination<T> latestCombination = finalResult.getLast();
            if (combination.getAverageMatching() > latestCombination.getAverageMatching()) {
                finalResult.clear();
                finalResult.add(combination);
                log.info("new item with higher matching has been added to the list. {}", combination);
            } else if (combination.getAverageMatching().equals(latestCombination.getAverageMatching())) {
                finalResult.add(combination);
                log.info("new item with the same higher matching has been added to the list. {}", combination);
            }
        }
    }
}
