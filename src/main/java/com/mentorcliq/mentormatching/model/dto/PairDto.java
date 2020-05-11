package com.mentorcliq.mentormatching.model.dto;

public class PairDto {

    private String firstEmpName;
    private String secondEmpName;
    private int matchingPercentage;

    public PairDto() {
    }

    public PairDto(String firstEmpName, String secondEmpName, int matchingPercentage) {
        this.firstEmpName = firstEmpName;
        this.secondEmpName = secondEmpName;
        this.matchingPercentage = matchingPercentage;
    }

    public String getFirstEmpName() {
        return firstEmpName;
    }

    public void setFirstEmpName(String firstEmpName) {
        this.firstEmpName = firstEmpName;
    }

    public String getSecondEmpName() {
        return secondEmpName;
    }

    public void setSecondEmpName(String secondEmpName) {
        this.secondEmpName = secondEmpName;
    }

    public int getMatchingPercentage() {
        return matchingPercentage;
    }

    public void setMatchingPercentage(int matchingPercentage) {
        this.matchingPercentage = matchingPercentage;
    }
}
