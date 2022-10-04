package exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
public class App {

    public static void save(Path path, Car car){
        String data = Car.serialize(car);
        try {
            Files.write(path, data.getBytes(), StandardOpenOption.WRITE, StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Car extract(Path path) {
        String data = null;
        BufferedReader reader = null;
        try {
            InputStream in = Files.newInputStream(path);
            reader = new BufferedReader(new InputStreamReader(in));
            data = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Car.unserialize(data);
    }

}
// END
