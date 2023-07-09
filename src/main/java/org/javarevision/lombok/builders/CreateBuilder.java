package org.javarevision.lombok.builders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import static org.javarevision.utils.RandomUtils.generateRandomIntValue;
import static org.javarevision.utils.RandomUtils.generateRandomStringValue;

public class CreateBuilder {

    public static void main(String[] args) throws JsonProcessingException {
        Address address1 = Address.builder()
                .street(generateRandomStringValue(6))
                .pin(generateRandomIntValue(6))
                .city(generateRandomStringValue(6))
                .build();

        Address address2 = Address.builder()
                .street(generateRandomStringValue(6))
                .pin(generateRandomIntValue(6))
                .city(generateRandomStringValue(6))
                .build();

        Student s = Student.builder()
                .name("S"+generateRandomStringValue(10))
                .age(generateRandomIntValue(3))
                .address(List.of(address1, address2))
                .names(List.of("Rahul" , "Ankita"))
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(s);
        System.out.println(jsonBody);
    }
}
