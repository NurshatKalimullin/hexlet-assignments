package exercise;

import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] numbers) {

        // Создаём экземпляр потока
        MinThread threadMin = new MinThread(numbers);
        MaxThread threadMax = new MaxThread(numbers);
        // Запускаем поток при помощи метода start()
        // После этого начинает выполняться метод run() потока
        threadMin.start();
        threadMax.start();

        try {
            threadMin.join();
            LOGGER.log(Level.INFO, "Thread " + threadMin + " has stopped.");

            threadMax.join();
            LOGGER.log(Level.INFO, "Thread " + threadMax + " has stopped.");
        } catch (InterruptedException e) {
            System.out.println("Потоки были прерваны");
        }

        int min = threadMin.getMinNumber();
        int max = threadMax.getMaxNumber();

        return Map.of("min", min, "max", max);

    }
    // END
}
