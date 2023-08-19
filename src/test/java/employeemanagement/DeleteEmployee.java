package employeemanagement;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.javarevision.constructors.Employee;
import org.testng.annotations.Test;

import static org.javarevision.utils.RandomUtils.generateRandomStringValue;

public class DeleteEmployee {

    private static final String BASE_URI = "https://dummy.restapiexample.com/api/v1";
    RequestSpecification requestSpecification;
    Response response;
    JsonPath jsonPath;

    @Test
    public void DeleteEmployeeDetails() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath("/delete/1")
                .setContentType(ContentType.JSON)
                .build();

        response = RestAssured.given().spec(requestSpecification).log().all().when().delete().then().log().all().extract().response();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);

        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath("/employee/1")
                .build();

        response =  RestAssured.given().spec(requestSpecification).log().all().when().get().then().log().all().extract().response();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void DeleteUserDetails() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://jsonplaceholder.typicode.com/")
                .setBasePath("/posts/2")
                .setContentType(ContentType.JSON)
                .build();

        response = RestAssured.given().spec(requestSpecification).log().all().when().delete().then().log().all().extract().response();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);

        response =  RestAssured.given().spec(requestSpecification).log().all().when().get().then().log().all().extract().response();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
    }
}
