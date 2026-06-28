package su.syelkonya.concurrency.poisonpill;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

//6) Producer-consumer с poison pill. 2 продюсера кладут задачи (String) в очередь, 3 консьюмера разбирают.
// Останови систему корректно (без потерь и без вечного ожидания).
// Консюмеры не знают сколько задач должны разобрать.
@Slf4j
public class Consumer implements Runnable {

    private final BlockingQueue<String> queue;
    static final String POISON_PILL = "__STOP__";

    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true){
                String work = queue.take();
                if(work.equals(POISON_PILL)) {
                    log.info("Consumer {} eat posionPill", this);
                    break;
                }
                Thread.sleep(1_000);
                log.info("Work on {} by {}", work, this);
            }
        } catch (InterruptedException _) {
            Thread.currentThread().interrupt();
        }
    }
}
