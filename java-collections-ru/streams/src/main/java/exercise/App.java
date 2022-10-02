package exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;



// BEGIN
public class App {

    private static final List<String> domains = new ArrayList<>(Arrays.asList("gmail.com", "yandex.ru", "hotmail.com"));

    public static long getCountOfFreeEmails(List<String> emails){
        return emails.stream()
                .map(email -> email.split("@"))
                .map(e -> e[1])
                .filter(currentDomain -> domains.contains(currentDomain))
                .count();
    }
}
// END
