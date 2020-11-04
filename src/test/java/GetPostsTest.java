import io.restassured.http.ContentType;
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

    // GET EXAMPLES

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

    // POST EXAMPLES

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
        Post postObject = new Post("hello_2", "world_2", 2);

        given()
            .contentType(ContentType.JSON)
            .body(postObject)
        .when()
            .post("https://jsonplaceholder.typicode.com/posts")
        .then()
            .body("title", is("hello_2"));
    }
}

class Post {
    private String title;
    private String body;
    private int userId;

    public Post(String title, String body, int userId) {
        this.title = title;
        this.body = body;
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
