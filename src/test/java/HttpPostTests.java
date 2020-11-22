import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class HttpPostTests {

    @Test
    // Using Java String
    public void shouldBeAbleToPostUsingString() {
        String payload = "{\"title\":\"hello_1\",\"body\":\"world_1\",\"userId\": 1}";

        given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post("https://jsonplaceholder.typicode.com/posts")
        .then()
            .body("title", is("hello_1"));
    }

    @Test
    // Using Java Object
    public void shouldBeAbleToPostUsingObject() {
        PostObject postObject = new PostObject("hello_2", "world_2", 2);

        given()
            .contentType(ContentType.JSON)
            .body(postObject)
        .when()
            .post("https://jsonplaceholder.typicode.com/posts")
        .then()
            .body("title", is("hello_2"));
    }

    @Test
    // Matching json schema
    public void shouldBeAbleToValidateAgainJsonSchema() {
        PostObject postObject = new PostObject("hello_2", "world_2", 2);

        String response = given()
            .contentType(ContentType.JSON)
            .body(postObject)
        .when()
            .post("https://jsonplaceholder.typicode.com/posts")
        .then()
            .extract().asString();

        assertThat(response, matchesJsonSchemaInClasspath("PostObjectSchema.json"));
    }
}
