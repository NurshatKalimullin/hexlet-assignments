package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class FileKV implements KeyValueStorage {

    private final String path;


    public FileKV(String path, Map<String,String> map) {
        this.path = path;
        String dataToSave = Utils.serialize(map);
        Utils.writeFile(path, dataToSave);
    }

    @Override
    public void set(String key, String value) {
        String storage = Utils.readFile(path);
        Map<String,String> map = Utils.unserialize(storage);
        map.put(key, value);
        String newData = Utils.serialize(map);
        Utils.writeFile(path, newData);
    }

    @Override
    public void unset(String key) {
        String storage = Utils.readFile(path);
        Map<String,String> map = Utils.unserialize(storage);
        map.remove(key);
        String newData = Utils.serialize(map);
        Utils.writeFile(path, newData);
    }

    @Override
    public String get(String key, String defaultValue) {
        String storage = Utils.readFile(path);
        Map<String,String> map = Utils.unserialize(storage);
        return map.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        String storage = Utils.readFile(path);
        Map<String,String> map = Utils.unserialize(storage);
        return new HashMap<>(map);
    }
}
// END
