package exercise;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public class Tag {

    private String tagName;
    private Map<String, String> attributes;

    Tag(String name, Map<String, String> attributes) {
        this.tagName = name;
        this.attributes = attributes;
    }

    public String stringifyAttributes() {
        return attributes.keySet().stream()
                .map(key -> " " + key + "=\"" + attributes.get(key) + "\"")
                .collect(Collectors.joining());
        //TODO:
    }

    public String getName() {
        return tagName;
    }

}
// END
