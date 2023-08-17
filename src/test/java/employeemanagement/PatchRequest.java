package employeemanagement;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.javarevision.lombok.builders.Typicode;
import org.testng.annotations.Test;

public class PatchRequest {

    String BASE_URI = "https://jsonplaceholder.typicode.com/";
    RequestSpecification requestSpecification;
    JsonPath jsonPath;
    Response response;

    @Test
    public void validatePatchRequest(){
        String title = "oriontech";

        Typicode typicode = Typicode.builder().title(title).build();

        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath("/posts/2")
                .setContentType(ContentType.JSON)
                .build();

        response = RestAssured.given().spec(requestSpecification).body(typicode).log().all().when().patch().then().log().all().extract().response();

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        jsonPath = response.jsonPath();

        Assertions.assertThat(jsonPath.getString("title")).isEqualTo(title);
    }
}
