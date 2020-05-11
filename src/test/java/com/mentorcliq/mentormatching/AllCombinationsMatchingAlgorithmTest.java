package com.mentorcliq.mentormatching;

import com.mentorcliq.mentormatching.command.AllCombinationsMatchingAlgorithm;
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
public class AllCombinationsMatchingAlgorithmTest {

    private final EmployeeMatchingStrategyFactory factory = new EmployeeMatchingStrategyFactory(
            new EmployeeAgeMatcher(30, 5),
            new EmployeeDivisionMatcher(30),
            new EmployeeTimezoneMatcher(40)
    );

    private final MatchingAlgorithm allCombinationsMatchingAlgorithm = new AllCombinationsMatchingAlgorithm(factory);

    private final Set<Employee> employeeSet = new LinkedHashSet<>(Arrays.asList(
            new Employee.Builder().setName("1").setEmail("serg1@test.com").setDivision("division1").setAge(25).setTimezone(2).build(),
            new Employee.Builder().setName("2").setEmail("serg2@test.com").setDivision("division2").setAge(30).setTimezone(3).build(),
            new Employee.Builder().setName("3").setEmail("serg3@test.com").setDivision("division1").setAge(22).setTimezone(2).build(),
            new Employee.Builder().setName("4").setEmail("serg4@test.com").setDivision("division3").setAge(27).setTimezone(-3).build()
    ));

    @Test
    public void testComputationCount() {
        List<Combination> combinations = allCombinationsMatchingAlgorithm.execute(employeeSet);
        Assertions.assertEquals(3, combinations.size());

        combinations.forEach(combination -> Assertions.assertEquals(2, combination.getPairs().size()));

        employeeSet.add(new Employee.Builder().setName("5").setEmail("serg5@test.com").setDivision("division3").setAge(27).setTimezone(-3).build());
        employeeSet.add(new Employee.Builder().setName("6").setEmail("serg6@test.com").setDivision("division3").setAge(27).setTimezone(-3).build());

        combinations = allCombinationsMatchingAlgorithm.execute(employeeSet);
        Assertions.assertEquals(15, combinations.size());

        combinations.forEach(combination -> Assertions.assertEquals(3, combination.getPairs().size()));

        employeeSet.add(new Employee.Builder().setName("7").setEmail("serg7@test.com").setDivision("division3").setAge(27).setTimezone(-3).build());
        employeeSet.add(new Employee.Builder().setName("8").setEmail("serg8@test.com").setDivision("division3").setAge(27).setTimezone(-3).build());

        combinations = allCombinationsMatchingAlgorithm.execute(employeeSet);
        Assertions.assertEquals(105, combinations.size());

        combinations.forEach(combination -> Assertions.assertEquals(4, combination.getPairs().size()));
    }

}
