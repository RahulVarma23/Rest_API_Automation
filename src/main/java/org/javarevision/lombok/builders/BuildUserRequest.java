package org.javarevision.lombok.builders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BuildUserRequest {

    public static void main(String[] args) throws JsonProcessingException {
        User user = User.builder()
                .id(0)
                .username("string")
                .firstName("string")
                .lastName("string")
                .email("string")
                .password("string")
                .phone("string")
                .userStatus(0)
                .date(new SimpleDateFormat("dd-MM-yyyy").format(new Date()))
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(user);
        System.out.println(jsonBody);
    }
}
