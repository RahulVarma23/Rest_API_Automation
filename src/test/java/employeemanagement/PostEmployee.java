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

public class PostEmployee {

    private static final String BASE_URI = "https://dummy.restapiexample.com/api/v1";
    RequestSpecification requestSpecification;
    Response response;
    JsonPath jsonPath;

    @Test
    public void createEmployee() {
//        Employee employee = new Employee("ansjdjl" , 20, 1000);
//
//        Map<Object, Object> map = new HashMap<>();
//        map.put("name" , "somthiong");
//        map.put("age" , 20);
//        map.put("Salary" , 30);

        Employee employee = Employee.builder()
                .name(generateRandomStringValue(5))
                .age(generateRandomIntValue(2))
                .salary(generateRandomIntValue(5))
                .build();

        employee.setName("%^&*%$##");

         requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath("/create")
                .setBody(employee)
                .setContentType(ContentType.JSON)
                .build();

         response = RestAssured.given().spec(requestSpecification).log().all().when().post().then().log().all().extract().response();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);

        jsonPath = response.jsonPath();
        jsonPath.getInt("data.age");
        jsonPath.getInt("data.salary");
        jsonPath.getString("data.name");

        Assertions.assertThat(jsonPath.getInt("data.age")).isEqualTo(employee.getAge());
        Assertions.assertThat(jsonPath.getInt("data.salary")).isEqualTo(employee.getSalary());
        Assertions.assertThat(jsonPath.getString("data.name")).isEqualTo(employee.getName());

        int id = jsonPath.getInt("data.id");

        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath("/employee/"+id)
                .build();

        response = RestAssured.given().spec(requestSpecification).log().all().when().get().then().log().all().extract().response();

        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);

        jsonPath = response.jsonPath();
        int actualIdFromResponse =  jsonPath.getInt("data.id");
        Assertions.assertThat(actualIdFromResponse).isEqualTo(id);
    }
}
