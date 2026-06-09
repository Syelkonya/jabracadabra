package su.syelkonya;

import java.time.LocalDate;
import java.util.*;

//11. Статистика посещений сайта: класс SiteStats с методами visit(LocalDate date, String userId),
// uniqueVisitors(date) (сколько разных пользователей за день), wasUserOn(date, userId).
public class SiteStats {

    Map<LocalDate, Set<String>> dateUserMap = new HashMap<>();

    public void visit(LocalDate date, String userId) {
        Set<String> userSet = dateUserMap.getOrDefault(date, new HashSet<>());
        userSet.add(userId);
        dateUserMap.put(date, userSet);
    }

    public int uniqueVisitors(LocalDate date){
        return dateUserMap.get(date).size();
    }

    public boolean wasUserOn(LocalDate date, String userId){
        return dateUserMap.get(date).contains(userId);
    }

}
