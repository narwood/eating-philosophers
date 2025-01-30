public class Philosopher implements Runnable {
    private final String name; 

    public Philosopher(int i) {
        name = "Philosopher" + Integer.toString(i);
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


