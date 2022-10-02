package exercise;

import java.util.Arrays;
import java.util.ArrayList;

// BEGIN
public class App {

    public static boolean scrabble(String letters, String word){
        String lettersInLowerCase = letters.toLowerCase().replaceAll("\\s", "");
        String wordInLowerCase = word.toLowerCase().replaceAll("\\s", "");

        String[] arrayLetters = lettersInLowerCase.split("");
        String[] arrayWord = wordInLowerCase.split("");

        ArrayList<String> arrayListLetters = new ArrayList<>(
                Arrays.asList(arrayLetters));
        ArrayList<String> arrayListWord = new ArrayList<>(
                Arrays.asList(arrayWord));
        for (String letter : arrayListWord) {
            if (!arrayListLetters.contains(letter)) {
                return false;
            } else {
                arrayListLetters.remove(letter);
            }
        }
        return true;
    }
}
//END
