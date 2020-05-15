package com.mentorcliq.mentormatching.model;

public class Match {

    private int matchingPercentage;
    private boolean matched;

    public int getMatchingPercentage() {
        return matchingPercentage;
    }

    public void setMatchingPercentage(int matchingPercentage) {
        this.matchingPercentage = matchingPercentage;
    }

    public boolean isMatched() {
        return matched;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }
}
