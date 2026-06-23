package su.syelkonya.concurrency;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;

public class CollectionUtils {

    //    Атомарный максимум. submit(long) из N потоков хранит максимум, getMax() отдаёт без блокировок.
    AtomicLong atomicLong = new AtomicLong(Long.MIN_VALUE);

    void submit(long value) {
        atomicLong.updateAndGet(l -> Math.max(l, value));
    }

    long getMax() {
        return atomicLong.get();
    }


    //    2) Идемпотентная инициализация. getResource() создаёт дорогой объект ровно один раз
//    даже при гонке десятков потоков, дальше отдаёт готовый без блокировок.
    private volatile Object weightObject;

    Object getResource() {
        Object local = weightObject;
        if (local == null) {
            synchronized (this) {
                local = weightObject;
                if (local == null) {
                    weightObject = new Object();
                    local = weightObject;
                }
            }
        }
        return local;
    }



}
