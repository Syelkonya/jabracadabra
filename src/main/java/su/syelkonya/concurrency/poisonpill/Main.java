package su.syelkonya.concurrency.poisonpill;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class Main {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();
        CountDownLatch countDownLatch = new CountDownLatch(2);

        ExecutorService producers = Executors.newFixedThreadPool(2);
        producers.submit(new Producer(blockingQueue, countDownLatch));
        producers.submit(new Producer(blockingQueue, countDownLatch));

        ExecutorService consumers = Executors.newFixedThreadPool(3);
        consumers.submit(new Consumer(blockingQueue));
        consumers.submit(new Consumer(blockingQueue));
        consumers.submit(new Consumer(blockingQueue));

        countDownLatch.await();
        log.info("оба продюсера закончили → кладём таблетки");

        // Кладём по одной таблетке на каждого консьюмера
        for (int i = 0; i < 3; i++) {
            blockingQueue.put(Consumer.POISON_PILL);
        }

        producers.shutdown();
        consumers.shutdown();
        consumers.awaitTermination(30, TimeUnit.SECONDS);
        log.info("все завершились");

    }

}
