package exercise;

import java.util.Comparator;
import java.util.Map;
import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;

// BEGIN
public class Sorter {

    public static List<String> takeOldestMans(List<Map<String, String>> users) {
        return users.stream()
                .filter(x -> x.containsValue("male"))
                .sorted((o1, o2) -> LocalDate.parse(o1.get("birthday")).compareTo(LocalDate.parse(o2.get("birthday"))))
                .map(x -> x.get("name"))
                .collect(Collectors.toList());
    }
}
// END
