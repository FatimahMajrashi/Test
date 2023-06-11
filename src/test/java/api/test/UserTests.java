package api.test;

import api.endpoints.UsersEndpoints;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests {
    Faker faker;  //By using This we will be able to create data
    User userPayload ;
    @BeforeClass
    public void SetUpData()
    {
     faker = new Faker(); //new object
     userPayload= new User(); //same data pass to pojo class and payload have data
     //-------------------------------------------------------
     userPayload.setId(faker.idNumber().hashCode());// Generating ID & passing the same ID into payload calling set ID method
     userPayload.setUsername(faker.name().username());
     userPayload.setFirstName(faker.name().firstName());
     userPayload.setLastName(faker.name().lastName());
     userPayload.setEmail(faker.internet().safeEmailAddress());
     userPayload.setPassword(faker.internet().password(5,10));
     userPayload.setPhone(faker.phoneNumber().cellPhone());
    }
    @Test(priority = 1)
    public void testPostUser() //calling the endpoint
    {
        Response response= UsersEndpoints.CreateUser(userPayload); //we can call the method by class name by passing required inputs
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Test(priority = 2)
    public void testGetUserByName() //calling the endpoint
    {
        Response response= UsersEndpoints.ReadUser(this.userPayload.getUsername());
        response.then().log().all();
        //response.statusCode();
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Test(priority = 3)
    public void testUpdateUserByName() //calling the endpoint
    {
        Response response= UsersEndpoints.UpdateUser(this.userPayload.getUsername(),userPayload);
        response.then().log().body();
        //response.statusCode();
        Assert.assertEquals(response.getStatusCode(),200);
        //Checking data after update
        Response responseAfterUpdate= UsersEndpoints.ReadUser(this.userPayload.getUsername());
        response.then().log().body();
        Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);
    }
    @Test(priority = 4)
    public void testDeleteUserByName() //calling the endpoint
    {
        Response response= UsersEndpoints.DeleteUser(this.userPayload.getUsername());
        Assert.assertEquals(response.getStatusCode(),200);
    }
}
