package com.ebay.jimo.tacocloud.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data // Generate all of the following missing getter and setter methods, constructor that accepts all final properties as arguments
@RequiredArgsConstructor
public class Ingredient {
    private final String id;
    private final String name;
    private final Type type;

    public static enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
