import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;

public class Philosopher implements Runnable {
    private final String name; 
    private final Semaphore left_fork;
    private final Semaphore right_fork;
    private final Lock lock;

    public Philosopher(int i, Lock lock, Semaphore[] forks) {
        name = "Philosopher" + Integer.toString(i);
        left_fork = forks[i];
        right_fork = forks[(i+1) % 5];
        this.lock = lock;
    } 

    public void print(String msg) {
        System.out.println(this.name + " " + msg + "\n");
    }

    public void think() {
        print("beginning to think");

        try {Thread.sleep((long)(Math.random()*1000));} //sleep for 0-10 secs
        catch (InterruptedException e) { } //do something with exception?
        
        print("done thinking");
    };
    public void take() {
        print("trying to take forks");

        lock.lock();
        try { left_fork.acquire(); }
        catch (InterruptedException e) { }
        try { right_fork.acquire(); }
        catch (InterruptedException e) { }
        lock.unlock();

        print("obtained forks");
    };
    public void eat() {
        print("beginning to eat");
        
        try {Thread.sleep((long)(Math.random()*1000));}
        catch (InterruptedException e) { }
        
        print("done eating");
    };
    public void drop() {
        print("dropping forks");

        left_fork.release();
        right_fork.release();

        print("dropped forks");
    };

    @Override
    public void run() {
        while (true) {
            think();
            take();
            eat();
            drop();
        }
    }

}