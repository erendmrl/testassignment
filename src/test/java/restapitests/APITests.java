package restapitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import org.junit.*;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static io.restassured.path.xml.XmlPath.CompatibilityMode.HTML;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * The tests will be called from feature file, @Test annotation is just to make these tests able to run without running the all test
 */
public class APITests {

    @Test
    public void addUserShouldReturn200AndShouldContainUsername() {
        RestAssured.baseURI = "http://thedemosite.co.uk";
        Response response = given().param("username", "testuser").param("password", "testpass")
                .when().post("/addauser.php").then().contentType(ContentType.HTML).extract()
                .response();
        assertEquals(response.getStatusCode(), 200);
        XmlPath htmlPath = new XmlPath(HTML, response.getBody().asString());
        assertEquals("Add a user - FREE PHP code and SQL", htmlPath.getString("html.head.title"));
        assertTrue(response.getBody().asString().contains("testuser"));
    }

    @Test
    public void shouldReturnHttp200WhenSuccesfullLogin() {
        RestAssured.baseURI = "http://thedemosite.co.uk";
        Response response = given().param("username", "testuser").param("password", "testpass")
                .when().post("/login.php").then().contentType(ContentType.HTML).extract()
                .response();
        assertEquals(response.getStatusCode(), 200);
        XmlPath htmlPath = new XmlPath(HTML, response.getBody().asString());
        assertEquals("Login example page to test the PHP MySQL online system", htmlPath.getString("html.head.title"));
        assertTrue(response.getBody().asString().contains("**Successful Login**"));
    }

    @Test
    public void shouldReturnTitleAsExpectedWhenSuccesfullLogin() {
        RestAssured.baseURI = "http://thedemosite.co.uk";
        Response response = given().param("username", "testuser").param("password", "testpass")
                .when().post("/login.php").then().contentType(ContentType.HTML).extract()
                .response();
        XmlPath htmlPath = new XmlPath(HTML, response.getBody().asString());
        assertEquals("Login example page to test the PHP MySQL online system", htmlPath.getString("html.head.title"));
    }

    @Test
    public void ShouldContainSuccessWhenCorrectParametersLogin() {
        RestAssured.baseURI = "http://thedemosite.co.uk";
        Response response = given().param("username", "testuser").param("password", "testpass")
                .when().post("/login.php").then().contentType(ContentType.HTML).extract()
                .response();
        assertTrue(response.getBody().asString().contains("**Successful Login**"));
    }

    @Test
    public void ShouldContainFailedWhenWrongParametersLogin() {
        RestAssured.baseURI = "http://thedemosite.co.uk";
        Response response = given().param("username", "ag8kbDmlvf5S").param("password", "Fb609hnFkpgk6")
                .when().post("/login.php").then().contentType(ContentType.HTML).extract()
                .response();
        assertTrue(response.getBody().asString().contains("**Failed Login**"));
    }
}
