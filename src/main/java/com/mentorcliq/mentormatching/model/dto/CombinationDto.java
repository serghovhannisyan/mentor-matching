package com.mentorcliq.mentormatching.model.dto;

import java.util.List;

public class CombinationDto {

    private Double averageMatching;
    private List<PairDto> pairs;

    public CombinationDto() {
    }

    public CombinationDto(List<PairDto> pairs, Double averageMatching) {
        this.pairs = pairs;
        this.averageMatching = averageMatching;
    }

    public Double getAverageMatching() {
        return averageMatching;
    }

    public void setAverageMatching(Double averageMatching) {
        this.averageMatching = averageMatching;
    }

    public List<PairDto> getPairs() {
        return pairs;
    }

    public void setPairs(List<PairDto> pairs) {
        this.pairs = pairs;
    }
}
