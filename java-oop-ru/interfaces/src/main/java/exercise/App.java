package exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {

    public static List<String> buildAppartmentsList(List<Home> apartments, int n) {
        if (apartments.isEmpty()) {
            return new ArrayList<>();
        }
        return apartments.stream().sorted(Home::compareTo)
                .map(Object::toString).limit(n).collect(Collectors.toList());
    }
}
// END
