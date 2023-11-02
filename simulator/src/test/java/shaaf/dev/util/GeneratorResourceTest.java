package shaaf.dev.util;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.wildfly.common.Assert;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;


@QuarkusTest
public class GeneratorResourceTest {

    private String incidentEP = "incidents/1";
    private String responderEP = "responders/1";

    // Hit incidents with 1 value meaning should return only 1 incident.
    @Test
    public void testCreateIncident(){
        get(incidentEP).then().statusCode(200).body("size()", is(1));

        List<HashMap<String, ?>> incidents = doGetRequest(incidentEP).jsonPath().get();
        Assert.assertFalse(incidents.isEmpty());
        Assert.assertFalse(incidents.get(0).values().contains(null));

    }

    // Hit responders to generate only 1 responder and check nulls and empty
    @Test
    public void testCreateResponder(){
        get(responderEP).then().statusCode(200).body("size()", is(1));

        List<HashMap<String, ?>> responders = doGetRequest(responderEP).jsonPath().get();
        Assert.assertFalse(responders.isEmpty());
        Assert.assertFalse(responders.get(0).values().contains(null));
    }

    // helper method to make the get request

    private static Response doGetRequest(String ep) {
        return
                given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
                        when().get(ep).
                        then().contentType(ContentType.JSON).extract().response();
    }


}