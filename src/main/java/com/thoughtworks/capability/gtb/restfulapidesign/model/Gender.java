package com.thoughtworks.capability.gtb.restfulapidesign.model;

import java.util.Arrays;
import java.util.Optional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Gender {
    MALE("male"),
    FEMALE("female");

    private String str;

    public String getStr() {
        return str;
    }

    public static Optional<Gender> getGenderByStr(String genderStr) {
        return Arrays.stream(Gender.values())
                .filter(gender -> gender.getStr().equalsIgnoreCase(genderStr))
                .findFirst();
    }
}
