package com.mentorcliq.mentormatching.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for keeping reference of possible pairs within one set
 * And calculation of average matching for all nested pairs
 */
public class Combination<T extends Employee> implements Comparable<Combination<T>> {

    private Double averageMatching;
    private final List<Pair<T>> pairs;
    private boolean matched;

    public Combination(List<Pair<T>> pairs) {
        this.pairs = new ArrayList<>(pairs);
        calculateAverage();
    }

    private void calculateAverage() {
        if (pairs.stream().filter(p -> p.getMatch().isMatched()).count() == pairs.size()) {
            matched = true;
        }
        pairs.stream().mapToInt(value -> value.getMatch().getMatchingPercentage()).average().ifPresent(value -> averageMatching = value);
    }

    public Double getAverageMatching() {
        return averageMatching;
    }

    public boolean isMatched() {
        return matched;
    }

    public List<Pair<T>> getPairs() {
        return pairs;
    }

    @Override
    public int compareTo(Combination o) {
        return o.averageMatching.compareTo(this.averageMatching);
    }

    @Override
    public String toString() {
        return "Combination{" +
                "averageMatching=" + averageMatching +
                ", pairs=" + pairs +
                '}';
    }
}
