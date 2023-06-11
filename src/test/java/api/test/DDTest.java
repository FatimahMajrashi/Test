package api.test;

import api.endpoints.UsersEndpoints;
import api.payload.User;
import api.utilities.api.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DDTest {
 @Test(priority = 1 ,dataProvider ="Data",dataProviderClass = DataProviders.class)
 public void testPostUser(String userID , String username ,String fname ,String lname ,String useremail, String pwd, String Ph)
 {
    User userPayload = new User();
     userPayload.setId(Integer.parseInt((userID)));
     userPayload.setUsername(username);
     userPayload.setFirstName(fname);
     userPayload.setLastName(lname);
     userPayload.setEmail(useremail);
     userPayload.setPassword(pwd);
     userPayload.setPhone(Ph);
     Response response= UsersEndpoints.CreateUser(userPayload);
     Assert.assertEquals(response.getStatusCode(),200);
 }
 @Test(priority = 2 ,dataProvider ="UserNames",dataProviderClass = DataProviders.class)
 public void testDeleteUserByName(String username)
 {
    Response response =UsersEndpoints.DeleteUser(username);
    Assert.assertEquals(response.getStatusCode(),200);
 }
}
