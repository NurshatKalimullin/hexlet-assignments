package exercise;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    // BEGIN
    // Создаём очередь с именем (топиком) "queue" по аналогии с Producer
    @Bean
    Queue queue() {
        return new Queue("queue", false);
    }

    // END
}
