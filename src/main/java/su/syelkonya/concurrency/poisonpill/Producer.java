package su.syelkonya.concurrency.poisonpill;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

import static su.syelkonya.concurrency.poisonpill.Consumer.POISON_PILL;

@Slf4j
public class Producer implements Runnable {
    private final BlockingQueue<String> queue;
    private final CountDownLatch countDownLatch;

    public Producer(BlockingQueue<String> queue, CountDownLatch countDownLatch) {
        this.queue = queue;
        this.countDownLatch = countDownLatch;
    }


    @Override
    public void run() {
        try {
            for (int i = 0; i < 3; i++) {
                queue.put("task_" + i);
                log.info("add task_{}  to {}", i, this);
            }
        } catch (InterruptedException _) {
            Thread.currentThread().interrupt();
        } finally {
            countDownLatch.countDown();
        }
    }
}
