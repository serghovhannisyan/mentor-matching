package com.mentorcliq.mentormatching.model.dto;

import java.util.List;

public class EmployeeCSVImportResponseDto {

    private List<CombinationDto> combinations;

    public List<CombinationDto> getCombinations() {
        return combinations;
    }

    public void setCombinations(List<CombinationDto> combinations) {
        this.combinations = combinations;
    }
}
