package com.thoughtworks.capability.gtb.restfulapidesign.model;

import lombok.Data;

@Data
public class Student {
    private int id;
    private String name;
    private Gender gender;
    private String note;
    private static int idCounter = 1;

    public Student(final String name, final Gender gender, final String note) {
        this.id = idCounter;
        idCounter++;
        this.name = name;
        this.gender = gender;
        this.note = note;
    }
}
