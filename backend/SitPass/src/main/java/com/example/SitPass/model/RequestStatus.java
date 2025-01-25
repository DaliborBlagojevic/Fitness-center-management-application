package com.example.SitPass.model;

import java.util.Arrays;

public enum RequestStatus {

    PENDING("Pending"),ACCEPTED("Accepted"),REJECTED("Rejected");



    private final String displayName;
    RequestStatus(String displayName){
        this.displayName = displayName;
    }
    public String getDisplayName(){
        return displayName;
    }
    public static RequestStatus fromDisplayName(String displayName) {
        return Arrays.stream(RequestStatus.values())
                .filter(enumValue -> enumValue.getDisplayName().equals(displayName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Enum value doesn't exist for displayName: " + displayName));

    }

}
