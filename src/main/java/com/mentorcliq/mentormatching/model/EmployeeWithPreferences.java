package com.mentorcliq.mentormatching.model;

import com.opencsv.bean.CsvBindByName;

public class EmployeeWithPreferences extends Employee {

    @CsvBindByName
    private String location;
    @CsvBindByName(column = "same location preference")
    private String pref;
    private Preference preference;

    public EmployeeWithPreferences() {}

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Preference getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = Preference.valueOf(preference);
    }

    public void setPref(String pref) {
        this.pref = pref;
        this.preference = Preference.find(pref);
    }
}
