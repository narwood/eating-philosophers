public class Main {
   public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Runnable p = new Philosopher(i + 1);
            Thread t = new Thread(p);
            t.start();
        }
   }
}
