package org.javarevision.lombok.builders;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Student {
    String name;
    int age;
    List<Address> address;
    String department;
    List<String> names;
}
