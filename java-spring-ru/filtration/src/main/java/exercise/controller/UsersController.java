package exercise.controller;
import exercise.model.User;
import exercise.model.QUser;
import exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;

// Зависимости для самостоятельной работы
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import com.querydsl.core.types.Predicate;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    // BEGIN
    @GetMapping(path = "")
    public Iterable<User> getUsers(@RequestParam(required = false) String firstName,
                                   @RequestParam(required = false) String lastName) {
        System.out.println("Start request");
        if (firstName == null && lastName == null) {
            return userRepository.findAll();
        } else if (firstName == null) {
            return userRepository.findAll(
                    QUser.user.lastName.containsIgnoreCase(lastName));
        } else if (lastName == null) {
            return userRepository.findAll(
                    QUser.user.firstName.containsIgnoreCase(firstName));
        } else {
            return userRepository.findAll(
                    QUser.user.firstName.containsIgnoreCase(firstName)
                            .and(
                                    QUser.user.lastName.containsIgnoreCase(lastName)
                            )
            );
        }
    }


    // END
}

