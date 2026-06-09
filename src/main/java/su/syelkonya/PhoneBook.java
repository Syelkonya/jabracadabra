package su.syelkonya;

import java.util.*;

public class PhoneBook {

    HashMap<String, Set<String>> phoneBookMap = new HashMap<>();

    public void addNumber(String name, String number) {
        Set<String> numbers = phoneBookMap.getOrDefault(name, new HashSet<>());
        numbers.add(number);
        phoneBookMap.put(name, numbers);
    }

    public List<String> getNumbers(String name) {
        if (phoneBookMap.get(name) == null) return new ArrayList<>();
        return new ArrayList<>(phoneBookMap.get(name));
    }

    public void removeNumber(String name, String number) {
        Set<String> hashset = phoneBookMap.getOrDefault(name, new HashSet<>());
        hashset.remove(number);
        if (hashset.isEmpty())
            phoneBookMap.remove(name);
    }

}
