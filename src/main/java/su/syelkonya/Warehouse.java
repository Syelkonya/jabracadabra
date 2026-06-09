package su.syelkonya;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.TreeMap;

/**
 * Склад: класс Warehouse с методами receive(name, qty), ship(name, qty) (вернуть false, если на складе не хватает),
 * printStock() - остатки в алфавитном порядке.
 */
@Slf4j
public class Warehouse {

    Map<String, Integer> quantityMap = new TreeMap<>();

    public void receive(String name, int quantity) {
        quantityMap.put(name, quantityMap.getOrDefault(name, 0) + quantity);
    }

    public boolean ship(String name, int quantity) {
        int actualQuantity = quantityMap.getOrDefault(name, 0);
        if (actualQuantity < quantity) {
            return false;
        } else {
            quantityMap.put(name, actualQuantity - quantity);
            return true;
        }
    }

    public void printStock(){
      quantityMap.forEach((key, value) -> log.info("{} : {}", key, value));
    }
}
