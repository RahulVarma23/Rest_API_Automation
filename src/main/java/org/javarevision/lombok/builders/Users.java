package org.javarevision.lombok.builders;

import lombok.Builder;
import lombok.Data;
import org.javarevision.utils.RandomUtils;

import static org.javarevision.utils.RandomUtils.generateRandomStringValue;

@Data
@Builder
public class Users {

    private String name;
    private String job;

    public static UsersBuilder defaultUserBuilder() {
        return new UsersBuilder().name(generateRandomStringValue(5)).job(generateRandomStringValue(6));
    }
}
