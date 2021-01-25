public class Multithreading extends Thread{

    public void run(){
        System.out.println("Thread is running");
    }
    public Multithreading() {
    }

    public Multithreading(Runnable target) {
        super(target);
    }

    public Multithreading(ThreadGroup group, Runnable target) {
        super(group, target);
    }

    public Multithreading(String name) {
        super(name);
    }

    public Multithreading(ThreadGroup group, String name) {
        super(group, name);
    }

    public Multithreading(Runnable target, String name) {
        super(target, name);
    }

    public Multithreading(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
    }

    public Multithreading(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
    }

    public static void main(String[] args) {
        Multithreading multithreading = new Multithreading();
        multithreading.start();
    }
}

