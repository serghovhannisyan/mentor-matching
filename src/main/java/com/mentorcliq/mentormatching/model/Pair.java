package com.mentorcliq.mentormatching.model;

import com.mentorcliq.mentormatching.matcher.EmployeeMatchingStrategy;

import java.util.List;

/**
 * Class for keeping two employees together as a pair
 * And matching percentage between them
 */
public class Pair {

    private final Employee first;
    private final Employee second;
    private int matchingPercentage;

    public Pair(Employee first, Employee second) {
        this.first = first;
        this.second = second;
    }

    public Employee getFirst() {
        return first;
    }

    public Employee getSecond() {
        return second;
    }

    public int getMatchingPercentage() {
        return matchingPercentage;
    }

    public void calculateMatch(List<EmployeeMatchingStrategy> strategies) {
        strategies.forEach(strategy -> matchingPercentage += strategy.calculateMatch(first, second));
    }

    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                ", matchingPercentage=" + matchingPercentage +
                '}';
    }
}
