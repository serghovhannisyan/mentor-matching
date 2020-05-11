package com.mentorcliq.mentormatching;

import com.mentorcliq.mentormatching.matcher.EmployeeAgeMatcher;
import com.mentorcliq.mentormatching.matcher.EmployeeDivisionMatcher;
import com.mentorcliq.mentormatching.matcher.EmployeeMatchingStrategy;
import com.mentorcliq.mentormatching.matcher.EmployeeTimezoneMatcher;
import com.mentorcliq.mentormatching.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmployeeMatchingStrategyTest {

    private final EmployeeMatchingStrategy employeeAgeMatchingStrategy = new EmployeeAgeMatcher(30, 5);
    private final EmployeeMatchingStrategy employeeDivisionMatchingStrategy = new EmployeeDivisionMatcher(30);
    private final EmployeeMatchingStrategy employeeTimezoneMatchingStrategy = new EmployeeTimezoneMatcher(40);

    @Test
    public void testAgeMatchingCalculationPositive() {
        Employee emp1 = new Employee();
        emp1.setAge(35);
        Employee emp2 = new Employee();
        emp2.setAge(30);
        int matching = employeeAgeMatchingStrategy.calculateMatch(emp1, emp2);
        Assertions.assertEquals(30, matching);
    }

    @Test
    public void testAgeMatchingCalculationNegative() {
        Employee emp1 = new Employee();
        emp1.setAge(35);
        Employee emp2 = new Employee();
        emp2.setAge(29);
        int matching = employeeAgeMatchingStrategy.calculateMatch(emp1, emp2);
        Assertions.assertEquals(0, matching);
    }

    @Test
    public void testDivisionMatchingCalculationPositive() {
        Employee emp1 = new Employee();
        emp1.setDivision("division1");
        Employee emp2 = new Employee();
        emp2.setDivision("division1");
        int matching = employeeDivisionMatchingStrategy.calculateMatch(emp1, emp2);
        Assertions.assertEquals(30, matching);
    }

    @Test
    public void testDivisionMatchingCalculationNegative() {
        Employee emp1 = new Employee();
        emp1.setDivision("division1");
        Employee emp2 = new Employee();
        emp2.setDivision("division2");
        int matching = employeeDivisionMatchingStrategy.calculateMatch(emp1, emp2);
        Assertions.assertEquals(0, matching);
    }

    @Test
    public void testTimezoneMatchingCalculationPositive() {
        Employee emp1 = new Employee();
        emp1.setTimezone(3);
        Employee emp2 = new Employee();
        emp2.setTimezone(3);
        int matching = employeeTimezoneMatchingStrategy.calculateMatch(emp1, emp2);
        Assertions.assertEquals(40, matching);
    }

    @Test
    public void testTimezoneMatchingCalculationNegative() {
        Employee emp1 = new Employee();
        emp1.setTimezone(3);
        Employee emp2 = new Employee();
        emp2.setTimezone(-4);
        int matching = employeeTimezoneMatchingStrategy.calculateMatch(emp1, emp2);
        Assertions.assertEquals(0, matching);
    }
}
