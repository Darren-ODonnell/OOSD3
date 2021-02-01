public class TestDaemonThread extends Thread{

    public void run(){
        if (Thread.currentThread().isDaemon()) {
            System.out.println("Daemon thread here");
        }else
            System.out.println("User Thread here");

    }

    public static void main(String[] args) {
        TestDaemonThread thread1 = new TestDaemonThread();
        TestDaemonThread thread2 = new TestDaemonThread();
        TestDaemonThread thread3 = new TestDaemonThread();

        thread2.setDaemon(true);
        thread1.start();
        thread2.start();
        thread3.start();


    }
}
