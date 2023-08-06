package org.javarevision.lombok.builders;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Student {
    private String name;
    private int age;
    private List<Address> address;
    private String department;
    private List<String> names;
}
