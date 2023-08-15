package org.javarevision.constructors;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import static org.javarevision.utils.RandomUtils.generateRandomStringValue;

@Builder
@Data
public class Employee {

    private String name;
    private int age;
    private int salary;

//    public Employee(String name, int age, int salary) {
//        this.name = name;
//        this.age = age;
//        this.salary = salary;
//    }
}
