package exercise;

import java.util.Arrays;

// BEGIN
public class App {

    public static String[][] enlargeArrayImage(String[][] image) {
        int lines = image.length;
        int elements = image[1].length;
        final int MULTIPLIER = 2;
        String[][] result = new String[lines * MULTIPLIER][elements * MULTIPLIER];
        int firstLinesCounter = 0;
        int firstElementsCounter = 0;
        int secondLinesCounter = 0;
        int secondElementsCounter = 0;
        while (secondLinesCounter < lines * MULTIPLIER) {
            while (secondElementsCounter < elements * MULTIPLIER) {
                result[secondLinesCounter][secondElementsCounter] = image[firstLinesCounter][firstElementsCounter];
                secondElementsCounter = secondElementsCounter + 1;
                result[secondLinesCounter][secondElementsCounter] = image[firstLinesCounter][firstElementsCounter];
                secondElementsCounter = secondElementsCounter + 1;
                firstElementsCounter = firstElementsCounter + 1;
            }
            firstElementsCounter = 0;
            secondElementsCounter = 0;
            secondLinesCounter = secondLinesCounter + 1;
            while (secondElementsCounter < elements * MULTIPLIER) {
                result[secondLinesCounter][secondElementsCounter] = image[firstLinesCounter][firstElementsCounter];
                secondElementsCounter = secondElementsCounter + 1;
                result[secondLinesCounter][secondElementsCounter] = image[firstLinesCounter][firstElementsCounter];
                secondElementsCounter = secondElementsCounter + 1;
                firstElementsCounter = firstElementsCounter + 1;
            }
            firstElementsCounter = 0;
            secondElementsCounter = 0;
            secondLinesCounter = secondLinesCounter + 1;
            firstLinesCounter = firstLinesCounter + 1;
        }
        return result;
    }
}
// END
