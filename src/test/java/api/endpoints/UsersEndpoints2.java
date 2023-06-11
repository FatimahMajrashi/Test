package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.ResourceBundle;
import static io.restassured.RestAssured.given;

//UserEndPoint.java
    //Created to perform create ,read ,update ,delete requests user API.

public class UsersEndpoints2 {

    static ResourceBundle getURL() // static to directly access
    {
        ResourceBundle routes = ResourceBundle.getBundle("routes"); //load properties file
        return routes;
    }
    public static Response CreateUser(User payload) //git payload
    {
        String PostUrl= getURL().getString("PostUrl");
        Response response = //storing the response in variable
          given()
             .contentType(ContentType.JSON)
             .accept(ContentType.JSON)
             .body(payload)
           .when()
              .post(PostUrl); //sending the request + refrains the url from route class
           return response; //return the response
    }
    //-----------------------------------------------------------------------------

    public static Response ReadUser(String username) //here we don't need payload because only read
    {
        String GetUrl= getURL().getString("GetUrl");
        Response response = given()
                .pathParam("username",username) //the path and value
                        .when()
                        .get(GetUrl);
        return response; //return the response
    }
    //-----------------------------------------------------------------------------
    public static Response UpdateUser(String username, User payload)
    {
        String UpdateUrl= getURL().getString("UpdateUrl");
    Response response = //storing the response in variable
            given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .pathParam("username",username)
                    .body(payload)
                    .when()
                    .put(UpdateUrl); //sending the request + refrains the url from route class
           return response; //return the response
   }
    //-----------------------------------------------------------------------------
    public static Response DeleteUser(String username) //here we don't need payload because only read
    {
        String DeleteUrl= getURL().getString("DeleteUrl");
        Response response = given()
                .pathParam("username",username) //the path and value
                .when()
                .delete(DeleteUrl);
        return response; //return the response
    }
}
