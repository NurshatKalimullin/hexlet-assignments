package exercise;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Map.Entry;

// BEGIN
public class App {

    public static List<Map<String, String>> findWhere(List<Map<String, String>> books, Map<String, String> dictionary){
        List<Map<String, String>> result = new ArrayList<>();
        for (Map<String, String> book : books) {
            for (Map.Entry<String, String> word : dictionary.entrySet()) {
                String keyFromDictionary = word.getKey();
                String valueFromDictionary = word.getValue();
                String valueFromBook = book.get(keyFromDictionary);
                if (valueFromBook.equals(valueFromDictionary) && !result.contains(book)) {
                    result.add(book);
                } else if (!valueFromBook.equals(valueFromDictionary)){
                    result.remove(book);
                    break;
                }
            }
        }
        return result;
    }
}
// BEGIN

//END
