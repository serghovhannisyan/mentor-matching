package com.mentorcliq.mentormatching;

import com.mentorcliq.mentormatching.model.Employee;
import com.mentorcliq.mentormatching.service.CSVParser;
import com.mentorcliq.mentormatching.service.EmployeeCSVParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayInputStream;
import java.util.List;

@SpringBootTest
public class EmployeeCSVParserTest {

    private final CSVParser<Employee> csvParser = new EmployeeCSVParser();

    private final String data = "Name,Email,Division,Age,Timezone\n" +
            "Serg1,serg1@test.com,Division1,20,3\n" +
            "Serg2,serg2@test.com,Division1,30,4\n" +
            "Serg3,serg3@test.com,Division2,25,4\n" +
            "Serg4,serg4@test.com,Division3,35,5\n" +
            "";

    @Test
    public void testParse() {
        List<Employee> parsedList = csvParser.parse(new ByteArrayInputStream(data.getBytes()));
        Assertions.assertEquals(4, parsedList.size());
    }

    @Test
    public void testParseValues() {
        List<Employee> parsedList = csvParser.parse(new ByteArrayInputStream(data.getBytes()));
        Assertions.assertEquals("Serg1", parsedList.get(0).getName());
        Assertions.assertEquals("serg2@test.com", parsedList.get(1).getEmail());
        Assertions.assertEquals("Division2", parsedList.get(2).getDivision());
        Assertions.assertEquals(35, parsedList.get(3).getAge());
        Assertions.assertEquals(5, parsedList.get(3).getTimezone());
    }
}
