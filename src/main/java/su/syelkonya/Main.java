package su.syelkonya;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    void main(){
        log.info("hello");


        PhoneBook phoneBook = new PhoneBook();

        phoneBook.addNumber("Иван", "+79991234567");
        phoneBook.addNumber("Иван", "+79997654321");
        phoneBook.addNumber("Иван", "+79991234567");
        phoneBook.addNumber("Мария", "+79990000000");

        log.info("Map после добавления всех {}", phoneBook.phoneBookMap);

        log.info("Иван: {}", phoneBook.getNumbers("Иван"));
        log.info("Мария: {}", phoneBook.getNumbers("Мария"));
        log.info("Несуществующий: {}", phoneBook.getNumbers("Петр"));

        phoneBook.removeNumber("Иван", "+79991234567");
        log.info("Иван после удаления: {}", phoneBook.getNumbers("Иван"));

        phoneBook.removeNumber("Мария", "+79990000000");
        log.info("Мария после удаления: {}", phoneBook.getNumbers("Мария"));
        log.info("Map после удаления Марии: {}", phoneBook.phoneBookMap);

        phoneBook.removeNumber("Петр", "+79990000000");
        log.info("Удаление несуществующего: ок");
    }





}
