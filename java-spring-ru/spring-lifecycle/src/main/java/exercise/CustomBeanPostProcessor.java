// Класс для самостоятельной работы

package exercise;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
// BEGIN

@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {


    @Override
    // Метод вызывается перед инициализацией бина
    // Принимает сам бин и имя бина
    // Должен вернуть бин
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        System.out.println("Called PostProcessBeforeInitialization for bean: " + beanName);
        return bean;
    }

    @Override
    // Метод вызывается после инициализацией бина
    // Должен вернуть бин
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        System.out.println("Called PostProcessAfterInitialization for bean: " + beanName);
        return bean;
    }


}
// END
