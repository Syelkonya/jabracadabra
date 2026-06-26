package su.syelkonya.concurrency.stack;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Main {

    void main() throws InterruptedException {
        Stack<Integer> stack = new Stack<>();

        // Заполняем стек заранее
        for (int i = 0; i < 100; i++) {
            stack.push(i);
        }

        ExecutorService executor = Executors.newFixedThreadPool(10);

        // 5 потоков одновременно делают push
        for (int i = 0; i < 5; i++) {
            final int threadId = i;
            executor.submit(() -> {
                for (int j = 0; j < 10; j++) {
                    stack.push(threadId * 100 + j);
                }
            });
        }

        // 5 потоков одновременно делают pop
        for (int i = 0; i < 5; i++) {
            executor.submit(() -> {
                for (int j = 0; j < 10; j++) {
                    stack.pop();
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);

        log.info("Готово — если были гонки данных увидишь NPE или неверные значения выше");
    }
}