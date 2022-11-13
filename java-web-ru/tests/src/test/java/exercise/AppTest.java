package exercise;

import kong.unirest.Body;
import kong.unirest.JsonNode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import io.javalin.Javalin;
import io.ebean.DB;

import exercise.domain.User;
import exercise.domain.query.QUser;
import io.ebean.Database;

import java.util.Optional;

class AppTest {

    private static Javalin app;
    private static String baseUrl;

    // BEGIN
    @BeforeAll
    public static void beforeAll() {
        app = App.getApp();
        app.start(0);
        int port = app.port();
        baseUrl = "http://localhost:" + port;

    }

    @AfterAll
    public static void afterAll() {
        app.stop();
    }

    // END

    @BeforeEach
    void beforeEach() {
        Database db = DB.getDefault();
        db.truncate("user");
        User existingUser = new User("Wendell", "Legros", "a@a.com", "123456");
        existingUser.save();
    }

    @Test
    void testUsers() {

        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users")
            .asString();
        String content = response.getBody();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getBody()).contains("Wendell Legros");
    }

    @Test
    void testNewUser() {

        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users/new")
            .asString();

        assertThat(response.getStatus()).isEqualTo(200);
    }

    // BEGIN
    @Test
    void testCreateNewUser() {
        User user = new User("Aleksandr", "Beloff", "albel@hotmail.com", "12344321");

        HttpResponse<JsonNode> responsePost = Unirest
                .post(baseUrl + "/users")
                .header("Content-Type", "application/json")
                .field("firstName", user.getFirstName())
                .field("lastName", user.getLastName())
                .field("password", user.getPassword())
                .field("email", user.getEmail())
                .asJson();

        assertThat(responsePost.getStatus()).isEqualTo(302);

        User actualUser = new QUser()
                .lastName.equalTo(user.getLastName())
                .findOne();

        assertThat(actualUser).isNotNull();
        assertThat(actualUser.getFirstName()).isEqualTo(user.getFirstName());
        assertThat(actualUser.getLastName()).isEqualTo(user.getLastName());
        assertThat(actualUser.getEmail()).isEqualTo(user.getEmail());
        assertThat(actualUser.getPassword()).isEqualTo(user.getPassword());
    }


    @Test
    void testCreateUserWithEmptyFirstName() {
        User user = new User("", "Beloff", "albel@hotmail.com", "12344321");

        HttpResponse<String > responsePost = Unirest
                .post(baseUrl + "/users")
                .header("Content-Type", "application/json")
                .field("firstName", user.getFirstName())
                .field("lastName", user.getLastName())
                .field("password", user.getPassword())
                .field("email", user.getEmail())
                .asString();

        assertThat(responsePost.getStatus()).isEqualTo(422);
        assertThat(responsePost.getBody()).contains("First Name can not be empty");

        User actualUser = new QUser()
                .lastName.equalTo(user.getEmail())
                .findOne();
        assertThat(actualUser).isNull();
    }


    @Test
    void testCreateUserWithEmptyLastName() {
        User user = new User("Alex", "", "albel@hotmail.com", "12344321");

        HttpResponse<String > responsePost = Unirest
                .post(baseUrl + "/users")
                .header("Content-Type", "application/json")
                .field("firstName", user.getFirstName())
                .field("lastName", user.getLastName())
                .field("password", user.getPassword())
                .field("email", user.getEmail())
                .asString();

        assertThat(responsePost.getStatus()).isEqualTo(422);
        assertThat(responsePost.getBody()).contains("Last Name can not be empty");

        User actualUser = new QUser()
                .lastName.equalTo(user.getEmail())
                .findOne();
        assertThat(actualUser).isNull();
    }


    @Test
    void testCreateUserWithIncorrectEmail() {
        User user = new User("Alex", "Beloff", "hotmail.com", "12344321");

        HttpResponse<String > responsePost = Unirest
                .post(baseUrl + "/users")
                .header("Content-Type", "application/json")
                .field("firstName", user.getFirstName())
                .field("lastName", user.getLastName())
                .field("password", user.getPassword())
                .field("email", user.getEmail())
                .asString();

        assertThat(responsePost.getStatus()).isEqualTo(422);
        assertThat(responsePost.getBody()).contains("email must be valid");

        User actualUser = new QUser()
                .lastName.equalTo(user.getEmail())
                .findOne();
        assertThat(actualUser).isNull();
    }


    @Test
    void testCreateUserWithIncorrectPassword() {
        User user = new User("Alex", "Beloff", "hotmail.com", "321");

        HttpResponse<String > responsePost = Unirest
                .post(baseUrl + "/users")
                .header("Content-Type", "application/json")
                .field("firstName", user.getFirstName())
                .field("lastName", user.getLastName())
                .field("password", user.getPassword())
                .field("email", user.getEmail())
                .asString();

        assertThat(responsePost.getStatus()).isEqualTo(422);
        assertThat(responsePost.getBody()).contains("Password must contain at least 4 symbols");

        User actualUser = new QUser()
                .lastName.equalTo(user.getEmail())
                .findOne();
        assertThat(actualUser).isNull();
    }


    // END
}
