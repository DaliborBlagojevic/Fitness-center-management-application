package com.example.SitPass.model;

import java.util.Arrays;

public enum DayOfWeek {
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    SUNDAY("Sunday");

    private final String displayName;

    DayOfWeek(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static DayOfWeek fromDisplayName(String displayName) {
        return Arrays.stream(DayOfWeek.values())
                .filter(enumValue -> enumValue.getDisplayName().equals(displayName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Enum value doesn't exist for displayName: " + displayName));
    }
}
