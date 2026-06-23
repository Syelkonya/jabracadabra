package su.syelkonya.concurrency;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;

@Slf4j
public class CallLimiter {


    //    3) Ограничитель одновременных вызовов. Внутри секции не больше N потоков, остальные ждут;
//    лимит освобождается даже при исключении.
    private final Semaphore semaphore;

    public CallLimiter(int threads) {
        this.semaphore = new Semaphore(threads);
    }

    void callLimit() {
        try {
            semaphore.acquire();
            try {
                //work
            } finally {
                semaphore.release();
            }
        } catch (InterruptedException e) {
            log.info("Не получилось захватить поток");
            Thread.currentThread().interrupt();
        }
    }

}
