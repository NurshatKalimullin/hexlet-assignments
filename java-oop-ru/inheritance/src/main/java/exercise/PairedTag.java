package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {

    private String tagName;
    private Map<String, String> attributes;
    private String tagBody;
    private List<Tag> children;


    public PairedTag(String tagName, Map<String, String> attributes, String tagBody, List<Tag> children) {
        super(tagName, attributes);
        this.tagName = tagName;
        this.attributes = attributes;
        this.tagBody = tagBody;
        this.children = children;
    }


    @Override
    public String toString(){
        String attributes = stringifyAttributes();
        String name = getName();
        String value = children.stream()
                .map(element -> element.toString())
                .collect(Collectors.joining());
        return String.format("<%s%s>%s%s</%s>", name, attributes, tagBody, value, name);
    }
}

// END
