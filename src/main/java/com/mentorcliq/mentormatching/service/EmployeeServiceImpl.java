package com.mentorcliq.mentormatching.service;

import com.mentorcliq.mentormatching.command.HighestCombinationsMatchingAlgorithm;
import com.mentorcliq.mentormatching.exception.InvalidFileException;
import com.mentorcliq.mentormatching.model.Combination;
import com.mentorcliq.mentormatching.model.Employee;
import com.mentorcliq.mentormatching.model.EmployeeWithPreferences;
import com.mentorcliq.mentormatching.model.dto.EmployeeCSVImportResponseDto;
import com.mentorcliq.mentormatching.service.csv.CSVParser;
import com.mentorcliq.mentormatching.util.DtoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.invoke.MethodHandles;
import java.util.LinkedHashSet;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final HighestCombinationsMatchingAlgorithm<Employee> employeeMatchingAlgorithm;
    private final HighestCombinationsMatchingAlgorithm<EmployeeWithPreferences> employeeWithPreferencesMatchingAlgorithm;
    private final CSVParser csvParser;

    public EmployeeServiceImpl(HighestCombinationsMatchingAlgorithm<Employee> employeeMatchingAlgorithm,
                               HighestCombinationsMatchingAlgorithm<EmployeeWithPreferences> employeeWithPreferencesMatchingAlgorithm,
                               CSVParser csvParser) {
        this.employeeMatchingAlgorithm = employeeMatchingAlgorithm;
        this.employeeWithPreferencesMatchingAlgorithm = employeeWithPreferencesMatchingAlgorithm;
        this.csvParser = csvParser;
    }

    /**
     * Reads uploaded csv file, generates list of highest combinations
     *
     * @param file
     * @return highest matching set of provided employees
     */
    @Override
    public EmployeeCSVImportResponseDto upload(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String header = reader.readLine();
            if (header.toLowerCase().contains("location") && header.toLowerCase().contains("same location preference")) {
                List<EmployeeWithPreferences> items = csvParser.parse(file.getInputStream(), EmployeeWithPreferences.class);
                List<Combination<EmployeeWithPreferences>> result = employeeWithPreferencesMatchingAlgorithm.execute(new LinkedHashSet<>(items));
                return new DtoMapper<EmployeeWithPreferences>().mapToResponseDto(result);
            } else {
                List<Employee> items = csvParser.parse(file.getInputStream(), Employee.class);
                List<Combination<Employee>> result = employeeMatchingAlgorithm.execute(new LinkedHashSet<>(items));
                return new DtoMapper<>().mapToResponseDto(result);
            }
        } catch (IOException e) {
            log.error("Unable to read file. {}", e.getMessage());
            throw new InvalidFileException("Unable to read from the stream");
        }
    }
}
