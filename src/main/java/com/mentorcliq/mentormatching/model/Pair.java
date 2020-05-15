package com.mentorcliq.mentormatching.model;

/**
 * Class for keeping two employees together as a pair
 * And matching percentage between them
 */
public class Pair<T extends Employee> {

    private final T first;
    private final T second;
    private Match match;

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                ", match=" + match +
                '}';
    }
}
