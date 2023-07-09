package org.javarevision.enums;

import java.util.Arrays;

public class TestEnums {

    public static void main(String[] args) {
        System.out.println(Colour.ORANGE.getValue());
        Arrays.stream(Colour.values()).forEach(colour -> System.out.println(colour.getValue()));
    }
}
