import org.junit.Test;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;

public class GetPostsTest {

    @Test
    public void shouldGetSpecificPost() {
        when()
            .get("https://jsonplaceholder.typicode.com/posts/1")
        .then()
            .body("id", is(1));
    }
}
