package exercise;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
@RestController
public class WelcomeController {

    @GetMapping("/")
    public String welcomeUser() {
        return "Welcome to Spring!";
    }

    @GetMapping("/hello")
    public String greetingUser(@RequestParam(value = "name", defaultValue = "anonimus") String name) {
        if (name.equals("anonimus")) {
            return "Hello, World!";
        }
        return String.format("Hello, %s!", name);
    }
}
// END
