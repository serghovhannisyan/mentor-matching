package com.mentorcliq.mentormatching.service;

import com.mentorcliq.mentormatching.exception.InvalidFileException;
import com.mentorcliq.mentormatching.model.Combination;
import com.mentorcliq.mentormatching.model.Employee;
import com.mentorcliq.mentormatching.model.dto.EmployeeCSVImportResponseDto;
import com.mentorcliq.mentormatching.util.DtoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.LinkedHashSet;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final EmployeeMatchingService employeeMatchingService;
    private final CSVParser<Employee> csvParser;

    public EmployeeServiceImpl(EmployeeMatchingService employeeMatchingService,
                               CSVParser<Employee> csvParser) {
        this.employeeMatchingService = employeeMatchingService;
        this.csvParser = csvParser;
    }

    /**
     * Reads uploaded csv file, generates list of highest combinations
     *
     * @param file
     * @return highest matching set of provided employees
     */
    @Override
    public EmployeeCSVImportResponseDto readFromCSV(MultipartFile file) {
        try {
            List<Employee> employeeList = csvParser.parse(file.getInputStream());

            List<Combination> items = employeeMatchingService.computeAllCombinations(new LinkedHashSet<>(employeeList));
            log.info("Computation finished. There are {} set(s). Result: {}", items.size(), items);

            return new DtoMapper().mapToResponseDto(items);
        } catch (IOException e) {
            log.error("Unable to read file. {}", e.getMessage());
            throw new InvalidFileException("Unable to read from the stream");
        }
    }
}
