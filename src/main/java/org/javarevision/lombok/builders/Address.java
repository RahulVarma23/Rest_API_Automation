package org.javarevision.lombok.builders;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {
    String street;
    int pin;
    String city;
    String shipDate;
}
