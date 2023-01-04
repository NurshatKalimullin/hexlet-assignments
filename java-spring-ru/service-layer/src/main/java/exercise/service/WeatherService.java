package exercise.service;

import com.fasterxml.jackson.core.type.TypeReference;
import exercise.HttpClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import exercise.CityNotFoundException;
import exercise.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;


@Service
public class WeatherService {

    @Autowired
    CityRepository cityRepository;

    // Клиент
    HttpClient client;

    // При создании класса сервиса клиент передаётся снаружи
    // В теории это позволит заменить клиент без изменения самого сервиса
    WeatherService(HttpClient client) {
        this.client = client;
    }

    // BEGIN
    public Map<String,Object> getWeather(String city) {
        HttpClient client = new HttpClient();

        ObjectMapper mapper = new ObjectMapper();

        // Выполняем GET запрос и получаем ответ
        try {
            return mapper.readValue(client.get(String.format("http://weather/api/v2/cities/%s", city)),
                    new TypeReference<Map<String,Object>>(){});
        } catch (Exception e) {
            throw new CityNotFoundException(String.format("%s not found", city));
        }
    }
    // END
}
