package employeemanagement;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.javarevision.lombok.builders.Users;
import org.testng.annotations.Test;

public class PatchUser {

    private static final String BASE_URI = "https://reqres.in";

    RequestSpecification specification;
    Response response;

    @Test
    public void updateUser() {
        //Users users = Users.defaultUserBuilder().build();
        Users users = Users.builder().name("Rahul").job("IT professional").build();

        specification = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath("/api/users/2")
                .setContentType(ContentType.JSON)
                .build();

        response = RestAssured.given().spec(specification).body(users).log().all().when().patch().then().log().all().extract().response();

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        Assertions.assertThat(response.jsonPath().getString("name")).isEqualTo(users.getName());
        Assertions.assertThat(response.jsonPath().getString("job")).isEqualTo(users.getJob());

        response = RestAssured.given().spec(specification).log().all().when().delete().then().log().all().extract().response();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_NO_CONTENT);

        response = RestAssured.given().spec(specification).log().all().when().get().then().log().all().extract().response();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
    }
}
