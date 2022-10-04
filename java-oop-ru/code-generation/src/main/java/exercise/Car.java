package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Value;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

// BEGIN
@Value
// END
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN
    public static String serialize(Car car) {
        final ObjectMapper mapper = new ObjectMapper();
        String carJSON = null;
        try {
            carJSON = mapper.writeValueAsString(car);
        } catch (JsonProcessingException e) {
            System.out.println(e);
        }
        return carJSON;
    }

    public static Car unserialize(String carJSON) {
        ObjectMapper objectMapper = new ObjectMapper();
        Car car = null;
        try {
            car = objectMapper.readValue(carJSON, Car.class);
        } catch (IOException e) {
            System.out.println(e);
        }
        return car;
    }
    // END
}
