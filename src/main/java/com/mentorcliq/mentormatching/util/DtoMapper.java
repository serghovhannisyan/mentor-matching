package com.mentorcliq.mentormatching.util;

import com.mentorcliq.mentormatching.model.Combination;
import com.mentorcliq.mentormatching.model.Employee;
import com.mentorcliq.mentormatching.model.Pair;
import com.mentorcliq.mentormatching.model.dto.CombinationDto;
import com.mentorcliq.mentormatching.model.dto.EmployeeCSVImportResponseDto;
import com.mentorcliq.mentormatching.model.dto.PairDto;

import java.util.List;
import java.util.stream.Collectors;

public class DtoMapper<T extends Employee> {

    /**
     * Maps list of combinations into response dto
     *
     * @param combinations
     * @return mapped response or empty combination object
     */
    public EmployeeCSVImportResponseDto mapToResponseDto(List<Combination<T>> combinations) {
        EmployeeCSVImportResponseDto response = new EmployeeCSVImportResponseDto();
        if (combinations == null) {
            return response;
        }

        response.setCombinations(mapToDto(combinations));

        return response;
    }

    private List<CombinationDto> mapToDto(List<Combination<T>> combinations) {
        return combinations.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private CombinationDto mapToDto(Combination<T> combination) {
        CombinationDto combinationDto = new CombinationDto();
        if (combination == null) {
            return combinationDto;
        }
        List<PairDto> pairDtoList = combination.getPairs()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

        combinationDto.setPairs(pairDtoList);
        combinationDto.setAverageMatching(combination.getAverageMatching());

        return combinationDto;
    }

    private PairDto mapToDto(Pair<T> pair) {
        PairDto pairDto = new PairDto();
        if (pair != null) {
            pairDto.setFirstEmpName(pair.getFirst().getName());
            pairDto.setSecondEmpName(pair.getSecond().getName());
            pairDto.setMatchingPercentage(pair.getMatch().getMatchingPercentage());
        }
        return pairDto;
    }
}
