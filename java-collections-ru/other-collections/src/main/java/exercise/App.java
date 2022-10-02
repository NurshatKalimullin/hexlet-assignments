package exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

// BEGIN
public class App {

    public static Map<String, String> genDiff(Map<String, Object> map1, Map<String, Object> map2) {

        for (Map.Entry<String, Object> entryMap1 : map1.entrySet()) {
            if (!map2.containsKey(entryMap1.getKey())) {
                entryMap1.setValue("deleted");
            } else {
                if (map2.containsValue(entryMap1.getValue())) {
                    entryMap1.setValue("unchanged");
                } else {
                    entryMap1.setValue("changed");
                }
            }
        }
        for (Map.Entry<String, Object> entryMap2 : map2.entrySet()) {
            if (!map1.containsKey(entryMap2.getKey())) {
                entryMap2.setValue("added");
            } else {
                if (map2.containsValue(entryMap2.getValue())) {
                    entryMap2.setValue("unchanged");
                } else {
                    entryMap2.setValue("changed");
                }
            }
        }
        map2.forEach((key, value) ->
                map1.merge(key, value, (v1, v2) -> v1));
        Set<String> ts = new TreeSet<>(map1.keySet());
        Map<String, String> linkedHashMap = new LinkedHashMap<>();
        for (String key : ts) {
            linkedHashMap.put(key, String.valueOf(map1.get(key)));
        }
        return linkedHashMap;
    }
}

//END
