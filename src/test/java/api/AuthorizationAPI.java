package api;

import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import schemas.UserDTO;

public class AuthorizationAPI extends ApiBase {

    Faker faker = new Faker();

    UserDTO dto;

    public Response registerNewUser(UserDTO user) {
        String endPoint = "/Account/v1/User";
        return postRequest(endPoint, 201, user);
    }

    public Response login(UserDTO user) {
        String endPoint = "/Account/v1/Login";
        return postRequest(endPoint, 200, user);
    }

    public String generateToken(UserDTO user) {
        String endPoint = "/Account/v1/GenerateToken";
        Response response = postRequest(endPoint, 200, user);

        JsonPath jsonPath = response.jsonPath();
        return "Bearer " + jsonPath.getString("token");
    }

    public boolean isAuthorized(UserDTO user) {
        String endPoint = "/Account/v1/Authorized";
        return postRequest(endPoint, 200, user).asString().equalsIgnoreCase("true");
    }

    public Response isDeleted(UserDTO user) {
        String endPoint = "/Account/v1/Authorized";
        return postRequest(endPoint, 404, user);
    }

    public UserDTO generateNewRandomUser() {
        UserDTO register = new UserDTO();
        register.setUserName(faker.internet().uuid());
        // password hard coded due to the faker password generation bug https://github.com/faker-ruby/faker/issues/2512
        register.setPassword("NewTest567&");
        return register;
    }

    public UserDTO existedUserCredentials() {
        dto = new UserDTO();
        dto.setUserName("hvoronkova");
        dto.setPassword("Newtest567!");
        return dto;
    }

    public UserDTO loginExistedUser() {
        String endPoint = "/Account/v1/Login";
        return postRequest(endPoint, 200, existedUserCredentials()).as(UserDTO.class);
    }
}