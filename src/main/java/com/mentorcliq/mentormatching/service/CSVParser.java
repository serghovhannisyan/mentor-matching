package com.mentorcliq.mentormatching.service;

import java.io.InputStream;
import java.util.List;

public interface CSVParser<T> {

    /**
     * Parser for csv files
     * @param inputStream
     * @return list of parsed objects
     */
    List<T> parse(InputStream inputStream);
}
