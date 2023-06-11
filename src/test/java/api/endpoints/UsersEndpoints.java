package api.endpoints;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.*;
import static io.restassured.response.Response.*;

    //UserEndPoint.java
    //Created to perform create ,read ,update ,delete requests user API.

public class UsersEndpoints {
    //To Create User Data we need user payload
    //will take body as payload

    public static Response CreateUser(User payload) //git payload
    {
        Response response = //storing the response in variable
          given()
             .contentType(ContentType.JSON)
             .accept(ContentType.JSON)
             .body(payload)
           .when()
              .post(Routes.PostUrl); //sending the request + refrains the url from route class
           return response; //return the response
    }
    //-----------------------------------------------------------------------------

    public static Response ReadUser(String username) //here we don't need payload because only read
    {
        Response response = given()
                .pathParam("username",username) //the path and value
                        .when()
                        .get(Routes.GetUrl);
        return response; //return the response
    }
    //-----------------------------------------------------------------------------
    public static Response UpdateUser(String username, User payload)
    {
    Response response = //storing the response in variable
            given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .pathParam("username",username)
                    .body(payload)
                    .when()
                    .put(Routes.UpdateUrl); //sending the request + refrains the url from route class
           return response; //return the response
   }
    //-----------------------------------------------------------------------------
    public static Response DeleteUser(String username) //here we don't need payload because only read
    {
        Response response = given()
                .pathParam("username",username) //the path and value
                .when()
                .delete(Routes.DeleteUrl);
        return response; //return the response
    }
}
