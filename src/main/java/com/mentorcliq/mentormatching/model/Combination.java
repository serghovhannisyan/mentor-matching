package com.mentorcliq.mentormatching.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for keeping reference of possible pairs within one set
 * And calculation of average matching for all nested pairs
 */
public class Combination implements Comparable<Combination> {

    private Double averageMatching;
    private final List<Pair> pairs;

    public Combination(List<Pair> pairs) {
        this.pairs = new ArrayList<>(pairs);
        calculateAverage();
    }

    private void calculateAverage() {
        pairs.stream().mapToInt(Pair::getMatchingPercentage).average().ifPresent(value -> averageMatching = value);
    }

    public Double getAverageMatching() {
        return averageMatching;
    }

    public List<Pair> getPairs() {
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
