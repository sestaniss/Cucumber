package API;

import APIStepDefinitions.GenerateTokenSteps;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardCodedExamples {

    String baseURI =  RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2ODQ5NzE0NDksImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY4NTAxNDY0OSwidXNlcklkIjoiNTIzMyJ9.PnTqENEGvkx5xHuR4577dTlc4jtH8pom8Teg0JfVL0w";

    @Test
    public void bgetCreatedEmployee(){
        RequestSpecification prepareRequest = given().
                header("Content-Type", "application/json").
                header("Authorization", token).
                queryParam("employee_id", employee_id);

        //hitting the endpoint
        Response response = prepareRequest.when().get("/getOneEmployee.php");
        response.prettyPrint();

        //verify the response
        response.then().assertThat().statusCode(200);

       String tempEmpId = response.jsonPath().get("employee.employee_id");

       //we have 2 emp id, one is global and second is local
        Assert.assertEquals(employee_id, tempEmpId);
    }
    static  String employee_id;
    @Test
    public void acreateEmployee(){
        //prepare the request
        RequestSpecification preparedRequest = given().header("Content-Type", "application/json")
                .header("Authorization", GenerateTokenSteps.token).body("{\n" +
                        "    \"emp_firstname\": \"Sam\",\n" +
                        "    \"emp_lastname\": \"E\",\n" +
                        "    \"emp_middle_name\": \"mr\",\n" +
                        "    \"emp_gender\": \"M\",\n" +
                        "    \"emp_birthday\": \"2012-05-20\",\n" +
                        "    \"emp_status\": \"Confirmed\",\n" +
                        "    \"emp_job_title\": \"Engineer\"\n" +
                        "}");



        //hitting the endpoint
       Response response = preparedRequest.when().post("/createEmployee.php");

        //it will print the output in the console
        response.prettyPrint();

        //verifying the assertions
        response.then().assertThat().statusCode(201);

        //we are capturing employee id from the response
      employee_id = response.jsonPath().get("Employee.employee_id");
        System.out.println(employee_id);

        response.then().assertThat().body("Employee.emp_firstname", equalTo("Sam"));
        response.then().assertThat().body("Employee.emp_lastname", equalTo("E"));

        //verify the response headers
        response.then().assertThat().header("Content-Type", "application/json");




        System.out.println("My test case is passed");

    }

//in homeowrk, create a method to get all employee status and job title

    @Test
    public void cupdateEmployee(){
        RequestSpecification preparedRequest = given().header("Content-Type", "application/json")
                .header("Authorization", token).body("{\n" +
                        "  \"employee_id\": \""+employee_id+"\",\n" +
                        "  \"emp_firstname\": \"Sam\",\n" +
                        "  \"emp_lastname\": \"Estaniss\",\n" +
                        "  \"emp_middle_name\": \"M\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"1990-12-16\",\n" +
                        "  \"emp_status\": \"Working\",\n" +
                        "  \"emp_job_title\": \"Engineer\"\n" +
                        "}\n");
        //hitting the endpoint
        Response response = preparedRequest.when().put("/updateEmployee.php");
        response.then().assertThat().statusCode(200);

        //for verification
        response.then().assertThat().body("Message", equalTo("Employee record Updated"));


    }
    @Test
    public void dgetUpdatedEmployee(){
        RequestSpecification prepareRequest = given().
                header("Content-Type", "application/json").
                header("Authorization", token).
                queryParam("employee_id", employee_id);
        Response response = prepareRequest.when().get("/getOneEmployee.php");
        response.prettyPrint();

        response.then().assertThat().statusCode(200);
        //if you want to verify the body of the response.
        //you can do that using hamcrest matchers

    }





}
