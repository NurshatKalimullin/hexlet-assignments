package exercise;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
// BEGIN

// END


class FileKVTest {

    private static Path filepath = Paths.get("src/test/resources/file").toAbsolutePath().normalize();

    @BeforeEach
    public void beforeEach() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new HashMap<String, String>());
        Files.writeString(filepath, content, StandardOpenOption.CREATE);
    }

    // BEGIN
    @Test
    void testFileKV() {
        KeyValueStorage storage = new FileKV("src/test/resources/file", Map.of("key", "value"));
        Map<String, String> expected = Map.of("key", "value");

        assertThat(storage.toMap()).isEqualTo(expected);

        assertThat(storage.get("key", "no value")).isEqualTo("value");

        storage.set("foo", "bar");
        assertThat(storage.get("foo", "no value")).isEqualTo("bar");

        storage.unset("foo");
        assertThat(storage.get("foo", "no value")).isEqualTo("no value");
        assertThat(storage.toMap()).isEqualTo(Map.of("key", "value"));
    }
    // END
}
