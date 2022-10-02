package exercise;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.ArrayList;

// BEGIN
public class SorterTest {

    @Test
    void testSorter() {
        // BEGIN
        //case #1
        List<Map<String, String>> users1 = List.of(
                Map.of("name", "Vladimir Nikolaev", "birthday", "1990-12-27", "gender", "male"),
                Map.of("name", "Andrey Petrov", "birthday", "1989-11-23", "gender", "male"),
                Map.of("name", "Anna Sidorova", "birthday", "1996-09-09", "gender", "female"),
                Map.of("name", "John Smith", "birthday", "1989-03-11", "gender", "male"),
                Map.of("name", "Vanessa Vulf", "birthday", "1985-11-16", "gender", "female"),
                Map.of("name", "Alice Lucas", "birthday", "1986-01-01", "gender", "female"),
                Map.of("name", "Elsa Oscar", "birthday", "1970-03-10", "gender", "female")
        );
        List<String> expected1 = new ArrayList<>(Arrays.asList("John Smith", "Andrey Petrov", "Vladimir Nikolaev"));
        List<String> actual1 = Sorter.takeOldestMans(users1);
        assertThat(actual1).isEqualTo(expected1);


        List<Map<String, String>> users2 = List.of(
                Map.of("name", "Vladimir Nikolaev", "birthday", "1990-12-27", "gender", "female"),
                Map.of("name", "Andrey Petrov", "birthday", "1989-11-23", "gender", "female"),
                Map.of("name", "Anna Sidorova", "birthday", "1996-09-09", "gender", "female"),
                Map.of("name", "John Smith", "birthday", "1989-03-11", "gender", "female"),
                Map.of("name", "Vanessa Vulf", "birthday", "1985-11-16", "gender", "female"),
                Map.of("name", "Alice Lucas", "birthday", "1986-01-01", "gender", "female"),
                Map.of("name", "Elsa Oscar", "birthday", "1970-03-10", "gender", "female")
        );
        List<String> expected2 = new ArrayList<>();
        List<String> actual2 = Sorter.takeOldestMans(users2);
        assertThat(actual2).isEqualTo(expected2);
        // END
    }
}
// END


