package com.mentorcliq.mentormatching.model;

import com.opencsv.bean.CsvBindByName;

import java.util.Objects;

public class Employee {

    @CsvBindByName
    private String name;
    @CsvBindByName(required = true)
    private String email;
    @CsvBindByName(required = true)
    private String division;
    @CsvBindByName(required = true)
    private int age;
    @CsvBindByName(required = true)
    private int timezone;

    public Employee() {

    }

    private Employee(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.division = builder.division;
        this.age = builder.age;
        this.timezone = builder.timezone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return email.equals(employee.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", division='" + division + '\'' +
                ", age=" + age +
                ", timezone=" + timezone +
                '}';
    }

    public static class Builder {
        private String name;
        private String email;
        private String division;
        private int age;
        private int timezone;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setDivision(String division) {
            this.division = division;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setTimezone(int timezone) {
            this.timezone = timezone;
            return this;
        }

        public Employee build() {
            return new Employee(this);
        }
    }
}
