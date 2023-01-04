package exercise.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import exercise.CityNotFoundException;
import exercise.model.City;
import exercise.repository.CityRepository;
import exercise.service.WeatherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;


@RestController
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private WeatherService weatherService;

    // BEGIN
    @GetMapping(path = "/cities/{id}")
    public Map<String,Object> getCityById(@PathVariable long id) throws JsonProcessingException {

        String city = cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException("City not found")).getName();

        return weatherService.getWeather(city);
    }


    @GetMapping(path = "/search")
    public List<Map<String, String>> getCityByName(@RequestParam(required = false) String name) {

        Iterable<City> cities;

        if (name == null) {
            cities = cityRepository.findAllByOrderByName();
        } else {
            cities = cityRepository.findByNameStartingWithIgnoreCase(name);
        }

        Iterator<City> iter;
        iter = cities.iterator();

        List<Map<String, String>> response = new ArrayList<>();

        while (iter.hasNext()) {
            String city = iter.next().getName();
            response.add(Map.of("temperature", (String) weatherService.getWeather(city)
                    .getOrDefault("temperature", null), "name", city));
        }
        return response;
    }
    // END
}

