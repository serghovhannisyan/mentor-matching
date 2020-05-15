package com.mentorcliq.mentormatching.service.csv;

import com.mentorcliq.mentormatching.model.Employee;

import java.io.InputStream;
import java.util.List;

public interface CSVParser {

    /**
     * Parser for csv files
     * @param inputStream
     * @return list of parsed objects
     */
    <T extends Employee> List<T> parse(InputStream inputStream, Class<T> type);
}
