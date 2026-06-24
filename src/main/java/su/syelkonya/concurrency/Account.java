package su.syelkonya.concurrency;

import java.math.BigDecimal;

//5) Перевод без дедлока. Класс Account с полем long id и балансом; метод transfer(from, to, amount),
// вызываемый встречно из многих потоков (from/to меняются местами).
// Реализуй без дедлока, списание и зачисление атомарны.
public class Account {

    long id;
    BigDecimal balance;

    static void transfer(Account from, Account to, BigDecimal amount) {
        if (from.id == to.id) return;
        Account first = from.id < to.id ? from : to;
        Account second = from.id < to.id ? to : from;

        synchronized (first) {
            synchronized (second) {
                from.balance = from.balance.subtract(amount);
                to.balance = to.balance.add(amount);
            }
        }

    }


}
