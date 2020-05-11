package com.mentorcliq.mentormatching;

import com.mentorcliq.mentormatching.command.HighestCombinationsMatchingAlgorithm;
import com.mentorcliq.mentormatching.command.MatchingAlgorithm;
import com.mentorcliq.mentormatching.matcher.EmployeeAgeMatcher;
import com.mentorcliq.mentormatching.matcher.EmployeeDivisionMatcher;
import com.mentorcliq.mentormatching.matcher.EmployeeMatchingStrategyFactory;
import com.mentorcliq.mentormatching.matcher.EmployeeTimezoneMatcher;
import com.mentorcliq.mentormatching.model.Combination;
import com.mentorcliq.mentormatching.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
public class HighestCombinationsMatchingAlgorithmTest {

    private final EmployeeMatchingStrategyFactory factory = new EmployeeMatchingStrategyFactory(
            new EmployeeAgeMatcher(30, 5),
            new EmployeeDivisionMatcher(30),
            new EmployeeTimezoneMatcher(40)
    );

    private final MatchingAlgorithm highestCombinationsMatchingAlgorithm = new HighestCombinationsMatchingAlgorithm(factory);

    private final Set<Employee> employeeSet = new LinkedHashSet<>(Arrays.asList(
            new Employee.Builder().setName("1").setEmail("serg1@test.com").setDivision("division1").setAge(25).setTimezone(2).build(),
            new Employee.Builder().setName("2").setEmail("serg2@test.com").setDivision("division2").setAge(30).setTimezone(3).build(),
            new Employee.Builder().setName("3").setEmail("serg3@test.com").setDivision("division1").setAge(22).setTimezone(2).build(),
            new Employee.Builder().setName("4").setEmail("serg4@test.com").setDivision("division3").setAge(27).setTimezone(-3).build()
    ));

    @Test
    public void testComputation() {
        List<Combination> combinations = highestCombinationsMatchingAlgorithm.execute(employeeSet);

        Assertions.assertEquals(1, combinations.size());
        Assertions.assertEquals("serg1@test.com", combinations.get(0).getPairs().get(0).getFirst().getEmail());
        Assertions.assertEquals("serg3@test.com", combinations.get(0).getPairs().get(0).getSecond().getEmail());
        Assertions.assertEquals("serg2@test.com", combinations.get(0).getPairs().get(1).getFirst().getEmail());
        Assertions.assertEquals("serg4@test.com", combinations.get(0).getPairs().get(1).getSecond().getEmail());
        Assertions.assertEquals(100, combinations.get(0).getPairs().get(0).getMatchingPercentage());
        Assertions.assertEquals(30, combinations.get(0).getPairs().get(1).getMatchingPercentage());
        Assertions.assertEquals(65, combinations.get(0).getAverageMatching());
    }
}
