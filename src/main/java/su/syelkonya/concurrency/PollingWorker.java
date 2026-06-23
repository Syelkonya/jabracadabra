package su.syelkonya.concurrency;

import lombok.extern.slf4j.Slf4j;

/**
 * 4) Graceful stop через volatile. Класс PollingWorker запускает фоновый поток,
 * который в цикле делает работу (тик каждые 100 мс),
 * и метод stop() для корректной остановки.
 */
@Slf4j
public class PollingWorker {
    private Thread thread;
    private volatile boolean running = true;

    void start() {
        thread = new Thread(() -> {
            while (running) {
                try {
                    Thread.sleep(100);
                    log.info("tic");
                } catch (InterruptedException _) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    void stop() throws InterruptedException {
        running = false;
        thread.join();
    }
}
