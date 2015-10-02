/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import static com.jayway.restassured.RestAssured.basePath;
import static com.jayway.restassured.RestAssured.baseURI;
import static com.jayway.restassured.RestAssured.defaultParser;
import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.parsing.Parser;
import javax.ws.rs.core.MediaType;
import static org.hamcrest.Matchers.equalTo;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Marc
 */
public class RestTest {

    public RestTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        baseURI = "http://localhost:8080/ExamPrep/";
        defaultParser = Parser.JSON;
        basePath = "/api";
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void testGetUsers() {
        given().
                //contentType(MediaType.APPLICATION_JSON).
        when().
                get("/users").
        then().
                statusCode(200);
    }
    
    @Test
    public void testGetUser() {
        given().
                //contentType(MediaType.APPLICATION_JSON).
        when().
                get("/users/1").
        then().
                statusCode(200).and()
                .body("userName", equalTo("sofus"));
    }
    
    @Test
    public void testGetProjects() {
        given().
                //contentType(MediaType.APPLICATION_JSON).
        when().
                get("/projects").
        then().
                statusCode(200);
    }
    
    @Test
    public void testGetProject() {
        given().
                contentType(MediaType.APPLICATION_JSON).
        when().
                get("/projects/1").
        then().
                statusCode(200).and()
                .body("projectName", equalTo("ERP system"));
    }
    
    @Test
    public void testCreateProject(){
    given().
                body("{ \"name\":\"testProject\", \"description\":\"This is a project created for test purposes.\"}").
                contentType(MediaType.APPLICATION_JSON).
        when().
                post("/projects").
        then().
                statusCode(200).and()
                .body("name", equalTo("testProject")).and()
                .body("description", equalTo("This is a project created for test purposes."));
        
    }
    
    
    @Test
    public void testCreateUser(){
    given().
                body("{ \"name\":\"testUser\", \"email\":\"testUser@hotmail.com\"}").
                contentType(MediaType.APPLICATION_JSON).
        when().
                post("/users").
        then().
                statusCode(200).and()
                .body("name", equalTo("testUser")).and()
                .body("email", equalTo("testUser@hotmail.com"));
        
    }
    
    @Test
    public void testDeleteUser(){
        given().
                contentType(MediaType.APPLICATION_JSON).
        when().
                delete("/users/3").
        then().
                statusCode(200).body("name", equalTo("testUser"));
    }
    
    @Test
    public void testDeleteProject(){
        given().
                contentType(MediaType.APPLICATION_JSON).
        when().
                delete("/projects/2").
        then().
                statusCode(200).body("name", equalTo("testProject"));
    }
    
    @Test
    public void testUserToProject(){
        given().
                body("{ \"name\":\"testUser2\", \"email\":\"testUser@hotmail.com\"}").
                contentType(MediaType.APPLICATION_JSON).
        when().
               post("/users").
        then().
                statusCode(200).and().body("name", equalTo("testUser2"));
        ////
        given().
                body("{ \"name\":\"testProject2\", \"description\":\"This is a project created for test purposes.\"}").
                contentType(MediaType.APPLICATION_JSON).
        when().
                post("/projects").
        then().
                statusCode(200).and()
                .body("name", equalTo("testProject2")).and()
                .body("description", equalTo("This is a project created for test purposes."));
        ////
        given().
                contentType(MediaType.APPLICATION_JSON).
        when().
               put("/project/2/3").
        then().
                statusCode(200);
        
    }
    
    
}
