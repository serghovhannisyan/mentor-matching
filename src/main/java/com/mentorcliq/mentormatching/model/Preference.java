package com.mentorcliq.mentormatching.model;

public enum Preference {

    YES("Yes"), NO("No"), NO_PREFERENCE("No Preference");

    private final String value;

    Preference(String s) {
        this.value = s;
    }

    public static Preference find(String s) {
        for (Preference p : values()) {
            if (p.value.equals(s)) {
                return p;
            }
        }

        throw new IllegalArgumentException("Invalid enum");
    }
}
