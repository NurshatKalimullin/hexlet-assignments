package exercise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.util.List;
import java.util.ArrayList;


@Component
public class QueueListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueueListener.class);

    // Для наглядности в список будут добавляться все сообщения,
    // которые получены из очереди
    // Так их можно будет легко просмотреть
    private List<String> messages = new ArrayList<>();

    public List<String> getAllMessages() {
        return messages;
    }

    // BEGIN
    // Аннотация, которая позволяет прослушивать очередь с указанным именем
    // Когда в очереди появляется сообщение, вызывается отмеченный метод
    @RabbitListener(queues = "queue")
    public void processQueue(String message) {
        // В простейшем случае просто выводим полученное сообщение
        // В общем случае этот сервис может делать с сообщением все, что угодно.
        // Например, можно использовать данные пользователя из сообщения
        // и создавать аккаунт или отправлять пользователю письмо или смс
        // Получатель никак не зависит от отправителя, отправитель сообщения даже не знает,
        // кто и как будет использовать полученное сообщение
        messages.add(message);
        LOGGER.info("Message is added!");
    }
    // END
}
