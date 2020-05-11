package com.mentorcliq.mentormatching.util;

import com.mentorcliq.mentormatching.model.Combination;
import com.mentorcliq.mentormatching.model.Pair;
import com.mentorcliq.mentormatching.model.dto.CombinationDto;
import com.mentorcliq.mentormatching.model.dto.EmployeeCSVImportResponseDto;
import com.mentorcliq.mentormatching.model.dto.PairDto;

import java.util.List;
import java.util.stream.Collectors;

public class DtoMapper {

    /**
     * Maps list of combinations into response dto
     *
     * @param combinations
     * @return mapped response or empty combination object
     */
    public EmployeeCSVImportResponseDto mapToResponseDto(List<Combination> combinations) {
        EmployeeCSVImportResponseDto response = new EmployeeCSVImportResponseDto();
        if (combinations == null) {
            return response;
        }

        response.setCombinations(mapToDto(combinations));

        return response;
    }

    private List<CombinationDto> mapToDto(List<Combination> combinations) {
        return combinations.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private CombinationDto mapToDto(Combination combination) {
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

    private PairDto mapToDto(Pair pair) {
        PairDto pairDto = new PairDto();
        if (pair != null) {
            pairDto.setFirstEmpName(pair.getFirst().getName());
            pairDto.setSecondEmpName(pair.getSecond().getName());
            pairDto.setMatchingPercentage(pair.getMatchingPercentage());
        }
        return pairDto;
    }
}
