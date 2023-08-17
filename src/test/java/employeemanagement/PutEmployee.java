package employeemanagement;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.javarevision.constructors.Employee;
import org.testng.annotations.Test;

import static org.javarevision.utils.RandomUtils.generateRandomIntValue;
import static org.javarevision.utils.RandomUtils.generateRandomStringValue;

public class PutEmployee {

    private static final String BASE_URI = "https://dummy.restapiexample.com/api/v1";
    RequestSpecification requestSpecification;
    Response response;
    JsonPath jsonPath;

    @Test
    public void updateEmployeeDetails() {
        String name = generateRandomStringValue(51);

        Employee employee = Employee.getDefaultEmployeeBuilder().name(name).build();
//
//        Employee e1 = Employee.getDefaultNameBuilder().name(name).build();

        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath("/update/1")
                .setBody(employee)
                .setContentType(ContentType.JSON)
                .build();

         response = RestAssured.given().spec(requestSpecification).log().all().when().put().then().log().all().extract().response();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);

        JsonPath jsonPath = response.jsonPath();

        jsonPath.getString("data.name");

        Assertions.assertThat(jsonPath.getString("data.name")).isEqualTo(name);
    }
}
