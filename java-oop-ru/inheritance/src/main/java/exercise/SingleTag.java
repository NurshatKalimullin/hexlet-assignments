package exercise;

import java.util.Map;

// BEGIN
public class SingleTag extends Tag {

    private String tagName;
    private Map<String, String> attributes;


    public SingleTag(String tagName, Map<String, String> attributes) {
        super(tagName, attributes);
        this.tagName = tagName;
        this.attributes = attributes;
    }


    @Override
    public String toString(){
        String attributes = stringifyAttributes();
        String name = getName();
        return String.format("<%s%s>", name, attributes);
    }

}
// END
