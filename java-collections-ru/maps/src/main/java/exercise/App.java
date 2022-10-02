package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class App {

    public static Map<String, Integer> getWordCount(String sentence){
        Map<String, Integer> dictionary = new HashMap<>();
        if (sentence.length() == 0) {
            return dictionary;
        }
        String[] arraySentence = sentence.split(" ");
        for (String word : arraySentence) {
            int counter = dictionary.getOrDefault(word, 0);
            counter += 1;
            dictionary.put(word, counter);
        }
        return dictionary;
    }

    public static String toString(Map<String, Integer> dictionary){
        String result = "{";
        if (dictionary.isEmpty()) {
            result = result + "}";
        } else {
            for (Map.Entry<String, Integer> word: dictionary.entrySet()) {
                result = result + "\n  " + word.getKey() + ": " + word.getValue();
            }
            result = result + "\n}";
        }
        return result;
    }
}
//END
