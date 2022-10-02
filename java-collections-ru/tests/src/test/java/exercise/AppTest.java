package exercise;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void testTake() {
        // BEGIN
        //case #1
        List<Integer> numbers1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        int count1 = 2;
        List<Integer> expected1 = new ArrayList<>(Arrays.asList(1, 2));
        List<Integer> actual1 = App.take(numbers1, count1);
        assertThat(actual1).isEqualTo(expected1);

        //case #2
        List<Integer> numbers2 = new ArrayList<>(Arrays.asList(7, 3, 10));
        int count2 = 8;
        List<Integer> expected2 = new ArrayList<>(Arrays.asList(7, 3, 10));
        List<Integer> actual2 = App.take(numbers2, count2);
        assertThat(actual2).isEqualTo(expected2);


        //case #3
        List<Integer> numbers3 = new ArrayList<>();
        int count3 = 8;
        List<Integer> actual3 = App.take(numbers3, count3);
        assertThat(actual3).isEmpty();
        // END
    }

}
