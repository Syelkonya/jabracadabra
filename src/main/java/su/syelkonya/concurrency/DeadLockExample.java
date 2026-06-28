package su.syelkonya.concurrency;

public class DeadLockExample {

    public static final Object lockA = new Object();
    public static final Object lockB = new Object();


    void main() throws InterruptedException {

        Thread thread1 = new Thread(() -> {
            synchronized (lockA) {
                System.out.println("Thread 1: lock lockA");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException _) {
                }

                System.out.println("Thread 1 waiting lockB");
                synchronized (lockB) {
                    System.out.println("Thread 1 lock lockB");
                }

            }
        }
        );

        Thread thread2 = new Thread(() -> {
            synchronized (lockB) {
                System.out.println("Thread 2: lock lockB");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException _) {
                }

                System.out.println("Thread 2 waiting lockA");
                synchronized (lockA) {
                    System.out.println("Thread 1 lock lockA");
                }
            }
        }
        );


        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

    }

}
