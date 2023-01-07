package exercise;

import java.time.LocalDateTime;

import exercise.daytimes.Daytime;
import exercise.daytimes.Morning;
import exercise.daytimes.Day;
import exercise.daytimes.Evening;
import exercise.daytimes.Night;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// BEGIN
@Configuration
public class MyApplicationConfig {

    @Bean
    public Daytime getDaytime(){
        LocalDateTime date = LocalDateTime.now();
        int hours = date.getHour();
        if (hours >= 6 && hours < 12) {
            return new Morning();
        } else if (hours >= 12 && hours < 18) {
            return new Day();
        } else if (hours >= 18 && hours < 23) {
            return  new Evening();
        }
        return new Night();
    }

}
// END
