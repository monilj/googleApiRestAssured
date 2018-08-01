package ContractTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


public class GoogleApi extends BaseClassForGoogleApi {
//    Properties properties = new Properties();
//     String placeid=null;
//    @BeforeTest
//    public String getBaseUri() throws IOException {
//        FileInputStream fileInputStream = new FileInputStream("/Users/monilj/Downloads/Devlopment/TescoRepos/ContractTestOld/src/main/resources/env.properties");
//        properties.load(fileInputStream);
//        String baseUri =properties.getProperty("googleApiHost");
//        return baseUri;
//    }

    @Test
    public void getPlaceAPI() throws IOException {
        RestAssured.baseURI = getBaseUri();
        given().
                param("location", "-33.8670522,151.1957362").
                param("radius", "1500").
                param("key", "AIzaSyCfuy-yc3FOJVfKUcktNpN1_LJtAusEbj0").
                when().
                get("/maps/api/place/nearbysearch/json").
                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                body("results[0].name", equalTo("Sydney")).and().
                body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM"));

    }
    @Test
    public void createPlaceAPI() throws IOException {
        RestAssured.baseURI = getBaseUri();
        Response res= given().
                queryParam("key", "AIzaSyCfuy-yc3FOJVfKUcktNpN1_LJtAusEbj0").
                body(FileUtils.readFromClassPath("PlaceDetail.json")).
                when().
                post("/maps/api/place/add/json").
                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                body("status", equalTo("OK")).extract().response();
        String responseString=res.asString();
        System.out.println(responseString);
        JsonPath js= new JsonPath(responseString);
        placeid=js.get("place_id");
        System.out.println(placeid);
    }
    @Test
    public void deletePlaceApi()
    {

        given().
                queryParam("key","AIzaSyDIQgAh0B4p0SdyYkyW8tlG-y0yJMfss5Y").

                body("{"+
                        "\"place_id\": \""+placeid+"\""+
                        "}").
                when().
                post("/maps/api/place/delete/json").
                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                body("status",equalTo("OK"));





    }
}
