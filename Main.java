import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Main {
    static Semaphore[] forks = new Semaphore[5];
    static Lock lock = new ReentrantLock(true);

   public static void main(String[] args) {
        for (int i=0; i<5; i++) {
            forks[i] = new Semaphore(1);
        }
        for (int i = 0; i < 5; i++) {
            Runnable p = new Philosopher(i, lock, forks);
            Thread t = new Thread(p);
            t.start();
        }
   }
}
