import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

public class GetPostsTest {

    /*
    Given() lets you:
        pass the request headers
        query and path param
        body
        cookies and headers
    When() lets you:
        get
        post
        put
    Then() lets you:
        perform assertion conditions
    */

    @Test
    public void shouldGetAllPost() {
        given()
            .get("https://jsonplaceholder.typicode.com/posts")
        .then()
            .body("id[0]", is(1));
    }

    @Test
    public void shouldGetSpecificPost() {
        given()
            .get("https://jsonplaceholder.typicode.com/posts/1")
        .then()
            .body("id", is(1))
            .body("title", containsString("aut"));
    }

    @Test
    public void shouldGetSpecificPostComment() {
        given()
            .get("https://jsonplaceholder.typicode.com/posts/1/comments")
        .then()
            .body("id[0]", is(1))
            .body("email[0]", is("Eliseo@gardner.biz"));
    }

    @Test
    // Passing in URL parameters
    public void shouldGetSpecificPostCommentPosts() {
        given()
            .queryParam("postId", 1)
            .get("https://jsonplaceholder.typicode.com/posts/1/comments")
        .then()
            .body("id[4]", is(5));
    }

    @Test
    public void shouldBeAbleToPost() {
        given()
            .body("")
        .when()
            .post("https://jsonplaceholder.typicode.com/posts")
        .then()
            .body("", is(""));
    }
}
