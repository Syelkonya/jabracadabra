package su.syelkonya.concurrency.array;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        this.executor = Executors.newVirtualThreadPerTaskExecutor();
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
                } catch (InterruptedException _) {
                    Thread.currentThread().interrupt();
                } catch (BrokenBarrierException e) {
                    log.error("Barier exception ", e);
                }
            });
        }
    }

    public void shutdown() {
        executor.shutdown();
    }
}
