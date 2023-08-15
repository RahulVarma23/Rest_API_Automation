package org.javarevision.constructors;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import static org.javarevision.utils.RandomUtils.generateRandomIntValue;
import static org.javarevision.utils.RandomUtils.generateRandomStringValue;

@Data
@Builder
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Employee {

    private String name;
    private int age;
    private int salary;

    @Builder
    public static EmployeeBuilder getDefaultEmployeeBuilder() {
        return new EmployeeBuilder()
                .name(generateRandomStringValue(6))
                .age(generateRandomIntValue(2))
                .salary(generateRandomIntValue(5));
    }

    @Builder
    public static EmployeeBuilder getDefaultSalaryBuilder() {
        return new EmployeeBuilder().salary(generateRandomIntValue(5));
    }

    @Builder
    public static EmployeeBuilder getDefaultNameBuilder() {
        return new EmployeeBuilder().name(generateRandomStringValue(5));
    }

}
