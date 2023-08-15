package employeemanagement;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.*;
import java.util.concurrent.TimeUnit;
import static io.restassured.RestAssured.*;


public class GetEmployee {

    //request specification

    private static final String BASE_URI = "https://dummy.restapiexample.com/api/v1";
    RequestSpecification requestSpecification;

    @BeforeMethod
    public void setUp() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath("/employees")
                .build();
    }

    @Test
    public void getAllEmployees() {
        Response response = given().spec(requestSpecification).log().all().when().get().then().log().all().extract().response();

        //validate status code
        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);

        //get response time
        response.getTimeIn(TimeUnit.SECONDS);

        JsonPath jsonPath = response.jsonPath();

        //validate message
        String actualMessage = jsonPath.getString("message");
        Assertions.assertThat(actualMessage).isEqualTo("Successfully! All records has been fetched.");

        //print 1st employee name
        System.out.println(jsonPath.getString("data.employee_name[0]"));
        System.out.println(jsonPath.getInt("data.employee_salary[2]"));

        List<String> list1 = jsonPath.getList("data.employee_name");
        List<Integer> list2 = jsonPath.getList("data.id");

        System.out.println(list1);
        System.out.println(list2);

       list1.stream().filter(name->name.startsWith("C")).forEach(System.out::println);
        list2.stream().filter(id->id%2==0).forEach(System.out::println);

//        for(String name : list1){
//            if(name.startsWith("C")){
//                System.out.println(name);
//            }
//        }
        Map<Integer, String > map = new HashMap<>();
        for(int i =0;i<list1.size();i++){
            map.put(list2.get(i), list1.get(i));
        }
        System.out.println(map);
        Set<Integer> set =map.keySet();

        for(int key: set){
            if(key>10){
                System.out.println(key+"->"+map.get(key));
            }
        }
        Assertions.assertThat(list2).isSorted();
    }

    @Test
    public void shouldValidateEmployeeNames() {
        Response response = given().spec(requestSpecification).log().all().when().get().then().log().all().extract().response();

        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);

        JsonPath jsonPath = response.jsonPath();
        List<Integer> listOfAge = jsonPath.getList("data.employee_age");
        List<String> listOfNames = jsonPath.getList("data.employee_name");

        System.out.println(listOfAge);
        System.out.println(listOfNames);

        for(int i=0;i<listOfAge.size();i++) {
            if(listOfAge.get(i)>50){
                System.out.println(listOfAge.get(i)+ "-->"+ listOfNames.get(i));
            }
        }
    }

    @Test
    public void shouldValidateJsonSchemaForEmployeeService() {
        Response response = given().spec(requestSpecification).log().all().when().get().then().extract().response();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);
        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/employeeSchema.json"));
    }

    @Test
    public void shouldGetEmployeeById() {
        String id = "1";
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath("/employee/"+id)
                .build();
        Response response = given().spec(requestSpecification).log().all().when().get().then().log().all().extract().response();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);

        JsonPath jsonPath = response.jsonPath();
        int ans=  jsonPath.getInt("data.id");
        Assertions.assertThat(ans).isEqualTo(Integer.parseInt(id));
    }
}
