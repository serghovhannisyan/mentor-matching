package com.mentorcliq.mentormatching.service.csv;

import com.mentorcliq.mentormatching.exception.InvalidFileException;
import com.mentorcliq.mentormatching.model.Employee;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.invoke.MethodHandles;
import java.util.List;

@Service
public class EmployeeCSVParser implements CSVParser {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * Parses csv file from input stream
     *
     * @param inputStream
     * @return list of employees
     * @throws InvalidFileException
     */
    @Override
    public <T extends Employee> List<T> parse(InputStream inputStream, Class<T> type) {
        try (Reader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(reader)
                    .withType(type)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<T> employees = csvToBean.parse();
            log.info("CSV file successfully parsed. {}", employees);

            return employees;
        } catch (Exception ex) {
            log.error("Unable to parse csv file. {}", ex.getMessage());
            throw new InvalidFileException("Unable to parse csv file. {}", ex);
        }
    }
}
