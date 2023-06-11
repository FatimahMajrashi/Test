package api.endpoints;

public class Routes {     //Added Only the urls
    public static String BaseUrl ="https://petstore.swagger.io/v2";
    //User Model:
    public static String PostUrl =   BaseUrl+"/user";
    public static String GetUrl =    BaseUrl+"/user/{username}";
    public static String UpdateUrl = BaseUrl+"/user/{username}";
    public static String DeleteUrl = BaseUrl+"/user/{username}";

}
