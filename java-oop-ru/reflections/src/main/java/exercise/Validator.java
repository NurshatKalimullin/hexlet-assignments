package exercise;

import java.lang.reflect.Field;
import java.util.*;

// BEGIN
public class Validator {

    public static List<String> validate(Address address) {
        List<String> errorFields = new ArrayList<>();
        Field[] fields = address.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                NotNull notNull = field.getAnnotation(NotNull.class);
                field.setAccessible(true);
                if (notNull != null && Objects.equals((String) field.get(address), null)){
                    errorFields.add(field.getName());
                }
            }
        } catch (IllegalAccessException e) {
            System.out.println(e);
        }
        return errorFields;
    }


    public static Map<String, List<String>> advancedValidate(Address address) {
        Field[] fields = address.getClass().getDeclaredFields();
        Map<String, List<String>> errors = new HashMap<>();
        try {
            for (Field field : fields) {
                NotNull notNull = field.getAnnotation(NotNull.class);
                MinLength minLength = field.getAnnotation(MinLength.class);
                field.setAccessible(true);
                if (notNull != null && Objects.equals((String) field.get(address), null)){
                    errors.put(field.getName(), List.of("can not be null"));
                }
                if (minLength != null && !Objects.equals((String) field.get(address), null)){
                    int minValue = minLength.minLength();
                    String value = (String) field.get(address);
                    if (value.length() < minValue) {
                        errors.put(field.getName(), List.of("length less than " + minValue));
                    }
                }

            }
        } catch (IllegalAccessException e) {
            System.out.println(e);
        }
        return errors;
    }
}
// END
