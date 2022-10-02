package exercise;

import java.util.stream.Collectors;
import java.util.stream.Stream;

// BEGIN
public class App {

    public static String getForwardedVariables(String configuration) {
        return Stream.of(configuration.split("[,\"]"))
                .filter(e -> e.startsWith("X_FORWARDED_"))
                .filter(e -> !e.contains("=/ "))
                .map(o -> o.replace("X_FORWARDED_", ""))
                .map(String::new)
                .collect(Collectors.joining(","));

    }
}
//END
