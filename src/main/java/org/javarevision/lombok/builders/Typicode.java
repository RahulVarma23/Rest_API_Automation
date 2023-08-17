package org.javarevision.lombok.builders;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.javarevision.utils.RandomUtils;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Typicode {

    private int userId;
    private int id;
    private String title;
    private String body;


    public static TypicodeBuilder getDefaultTypicodeBuilder() {
        return new TypicodeBuilder()
                .userId(RandomUtils.generateRandomIntValue(2))
                .id(RandomUtils.generateRandomIntValue(2))
                .title(RandomUtils.generateRandomStringValue(5))
                .body(RandomUtils.generateRandomStringValue(10));
    }
}
