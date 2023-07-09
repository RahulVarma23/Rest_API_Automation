package org.javarevision.enums;

public enum Colour {


    ORANGE("Orange"),
    GREEN("Green");


    String colour;
    Colour(String colour) {
        this.colour = colour;
    }

    public String getValue() {
      return colour;
    }
}
