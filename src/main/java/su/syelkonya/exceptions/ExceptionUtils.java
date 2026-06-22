package su.syelkonya.exceptions;

import java.util.Optional;

public class ExceptionUtils {

//    1) Метод safeParse(String s), который возвращает Optional вместо исключения.
//    (Integer.parseInt() но без исключения)
    public Optional<Integer> safeParse(String s){
        if (s == null) return Optional.empty();
        try {
           return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException _) {
            return Optional.empty();
        }
    }

//    Свое исключение ValidationException extends RuntimeException с конструктором (message, cause).
//    Реализовать метод parseAge(String) ловит NumberFormatException и пробрасывает ValidationException с причиной.
    public Optional<Integer> parseAge(String s){
        if (s == null) return Optional.empty();
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            throw new ValidationException(String.format("Couldn't parse string: '%s'", s), e);
        }
    }

}
