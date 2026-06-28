package su.syelkonya.concurrency.array;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * 7) Раздели массив на K частей, K потоков считают частичную сумму своей части,
 * на барьере сводят в общий итог, печатают;
 * для демонстрации переиспользования повтори на втором массиве.
 */
@Slf4j
public class ParallelArraySum {
    private final int k;
    private final ExecutorService executor;
    private final CyclicBarrier barrier;
    private final long[] partialSums;
    private volatile long totalSum;

    public ParallelArraySum(int k) {
        this.k = k;
        this.partialSums = new long[k];
        this.executor = Executors.newFixedThreadPool(k);
        this.barrier = new CyclicBarrier(k, () -> {
            log.info("Begin of Barrier");
            totalSum = 0;
            for (long partial : partialSums) {
                totalSum += partial;
            }
            log.info("Итог: {}", totalSum);
        });
    }

    public void calculate(int[] array) {
        int chunkSize = array.length / k;
        CountDownLatch countDownLatch = new CountDownLatch(k);

        for (int i = 0; i < k; i++) {
            final int index = i;
            final int from = i * chunkSize;
            final int to = (i == k - 1) ? array.length : from + chunkSize;

            int threadId = i;
            executor.submit(() -> {
                log.info("Tread №{} count from {} to {}", threadId, from, to);
                long sum = 0;
                for (int j = from; j < to; j++) {
                    sum += array[j];
                    log.info("Tread №{}: sumNow = {} (+{} was added)", threadId, sum, array[j]);
                }
                partialSums[index] = sum;

                try {
                    log.info("Thread №{} is waiting other", threadId);
                    barrier.await(); // ждём остальных
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    log.error("Interrupted exception ", e);
                } catch (BrokenBarrierException e) {
                    log.error("Barier exception ", e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Interrupted exception ", e);
        }
    }

    public void shutdown() {
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Interrupted exception ", e);
        }
    }
}
