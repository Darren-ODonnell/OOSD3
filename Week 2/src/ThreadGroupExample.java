public class ThreadGroupExample implements Runnable{

    public static void main(String[] args) {
        ThreadGroupExample threadGroup = new ThreadGroupExample();
        ThreadGroup tg1 = new ThreadGroup("Parent ThreadGroup");

        Thread t1 = new Thread(tg1, threadGroup, "One");
        t1.start();
        Thread t2 = new Thread(tg1, threadGroup, "Two");
        t2.start();
        Thread t3 = new Thread(tg1, threadGroup, "Three");
        t3.start();

        System.out.println("Thread Group Name is: " + tg1.getName());
        tg1.list();

    }
    public void run(){
        System.out.println(Thread.currentThread().getName());
    }

}
